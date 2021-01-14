import json
import logging
import os
import tempfile
import time
import uuid
from builtins import print
from functools import reduce
import copy

import yaml
from tinydb import TinyDB, Query
from tinydb.middlewares import CachingMiddleware
from tinydb.storages import MemoryStorage
from toscaparser.functions import GetAttribute
from toscaparser.tosca_template import ToscaTemplate
from werkzeug.datastructures import FileStorage

from sure_tosca.models.base_model_ import Model
from sure_tosca.models.node_template import NodeTemplateModel as NodeTemplateModel
from sure_tosca.models.node_template_map import NodeTemplateMapModel
from sure_tosca.models.tosca_template import ToscaTemplateModel  as ToscaTemplateModel

from sure_tosca.service import tosca_helper

# db = TinyDB(storage=CachingMiddleware(MemoryStorage))
db_dir_path = tempfile.gettempdir()
tosca_templates_db_file_path = os.path.join(db_dir_path, "tosca_templates.json")
tosca_templates_db = TinyDB(tosca_templates_db_file_path)
# tosca_templates_db = TinyDB(storage=CachingMiddleware(MemoryStorage))

node_template_db = TinyDB(storage=CachingMiddleware(MemoryStorage))
dsl_definitions_db = TinyDB(storage=CachingMiddleware(MemoryStorage))
relationship_template_db = TinyDB(storage=CachingMiddleware(MemoryStorage))
interface_types_db = TinyDB(storage=CachingMiddleware(MemoryStorage))
logger = logging.getLogger(__name__)
if not getattr(logger, 'handler_set', None):
    logger.setLevel(logging.INFO)
h = logging.StreamHandler()
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
h.setFormatter(formatter)
logger.addHandler(h)
logger.handler_set = True

root_key = 'root_key'


def query_db(queries, db=None):
    if queries:
        query = reduce(lambda a, b: a & b, queries)
        results = db.search(query)
    else:
        results = db.all()
    if results:
        updated_results = []
        for res in results:
            if root_key in res:
                res_copy = copy.deepcopy(res)
                key = res_copy[root_key]
                res_copy.pop(root_key)
                node = {key: res_copy}
            else:
                logger.error(str(res) + ' has no ' + root_key)
            updated_results.append(node)
        return updated_results
    return None


def get_tosca_template_model_by_id(id):
    tosca_template_dict = get_tosca_template_dict_by_id(id)
    tosca_template_model = ToscaTemplateModel.from_dict(tosca_template_dict)
    # tosca_template_dict = deTOSCAfy_topology_template(tosca_template_dict)
    if tosca_template_dict:
        get_tosca_template(tosca_template_dict)
        return tosca_template_model
    return None


def get_tosca_template(tosca_template_dict):
    start = time.time()
    logger.info("Checking  ToscaTemplate validity")
    tt = ToscaTemplate(yaml_dict_tpl=copy.deepcopy(tosca_template_dict))
    end = time.time()
    elapsed = end - start
    logger.info("Time elapsed: " + str(elapsed))
    return tt


def get_tosca_template_dict_by_id(id):
    tosca_template_dict = tosca_templates_db.get(doc_id=int(id))
    return tosca_template_dict


def purge_all_tables():
    node_template_db.purge_tables()
    dsl_definitions_db.purge_tables()
    relationship_template_db.purge_tables()
    interface_types_db.purge_tables()
    node_template_db.close()
    dsl_definitions_db.close()
    relationship_template_db.close()
    interface_types_db.close()


def save(file: FileStorage):
    # try:
    # tosca_template_file_path = os.path.join(db_dir_path, file.filename)
    start = time.time()
    logger.info("Got request for tosca template. File name: "+str(file.filename))
    purge_all_tables()
    dictionary = yaml.safe_load(file.stream)
    # dictionary = yaml.load(file.stream)
    logger.info("tosca template: \n" + str(yaml.dump(dictionary)))
    # print(yaml.dump(dictionary))
    tosca_template = get_tosca_template(dictionary)
    # all_custom_def = tosca_template.nodetemplates[0].custom_def
    tosca_template_model = ToscaTemplateModel.from_dict(dictionary)
    doc_id = tosca_templates_db.insert(dictionary)
    # tosca_templates_db.close()
    logger.info("Returning doc_id: " + str(doc_id))
    return doc_id
    # except Exception as e:
    #     logger.error(str(e))
    #     return str(e), 400


def get_interface_types(id, interface_type=None):
    if len(interface_types_db) <= 1:

        tosca_template_model = get_tosca_template_model_by_id(id)
        object_list = tosca_template_model.interface_types
        if object_list is None:
            object_list = {}
        tosca_template = get_tosca_template(tosca_template_model.to_dict())
        tosca_node_types = tosca_template.nodetemplates[0].type_definition.TOSCA_DEF
        all_custom_def = tosca_template.nodetemplates[0].custom_def
        object_list.update(tosca_node_types)
        object_list.update(all_custom_def)
        if object_list:
            for interface_type_name in object_list:
                if 'tosca.interfaces' in interface_type_name:
                    interface = {root_key: interface_type_name}
                    interface.update(object_list[interface_type_name])
                    interface_types_db.insert(interface)

    queries = []
    if interface_type:
        query = Query()
        queries.append(query.root_key == interface_type)

    return query_db(queries, db=interface_types_db)


def change_to_node_template_model(query_results):
    res = []
    for node_template in query_results:
        # copy.deepcopy()
        node_template_map = NodeTemplateMapModel()
        name = next(iter(node_template))
        node_template_map.name = name
        node_template = node_template[name]
        node_template_map.node_template = NodeTemplateModel.from_dict(node_template)
        res.append(node_template_map)
    return res


def get_node_templates_with_ancestor_types(id, type_name):
    query_results = []
    node_templates = get_node_templates(id)
    if node_templates:
        for node_template in node_templates:
            ancestor_types = get_all_ancestor_types(id, node_template.name)
            for ancestor_type in ancestor_types:
                if ancestor_type == type_name and node_template not in query_results:
                    query_results.append(node_template)
                    break
    return query_results


def get_node_templates(id, type_name=None, node_name=None, has_interfaces=None, has_properties=None,
                       has_attributes=None,
                       has_requirements=None, has_capabilities=None, has_artifacts=None):
    if len(node_template_db) <= 1:
        tosca_template_model = get_tosca_template_model_by_id(id)
        object_list = tosca_template_model.topology_template.node_templates
        if object_list:
            for key in object_list:
                node = {root_key: key}
                if isinstance(object_list[key], dict):
                    node.update(object_list[key])
                elif isinstance(object_list[key], Model):
                    node.update(object_list[key].to_dict())
                node_template_db.insert(node)

    queries = []
    if node_name:
        if isinstance(node_name, list):
            for n in node_name:
                query = Query()
                queries.append(query.root_key == n)
        elif isinstance(node_name, str):
            query = Query()
            queries.append(query.root_key == node_name)
    if type_name:
        query = Query()
        queries.append(query.type == type_name)
    if has_properties:
        query = Query()
        prop = None
        queries.append(query.properties != prop)
    if has_interfaces:
        query = Query()
        prop = None
        queries.append(query.interfaces != prop)
    if has_attributes:
        query = Query()
        prop = None
        queries.append(query.attributes != prop)
    if has_requirements:
        query = Query()
        prop = None
        queries.append(query.requirements != prop)
    if has_capabilities:
        query = Query()
        prop = None
        queries.append(query.capabilities != prop)
    if has_artifacts:
        query = Query()
        prop = None
        queries.append(query.artifacts != prop)

    query_results = query_db(queries, db=node_template_db)

    if type_name and not query_results:
        query_results = get_node_templates_with_ancestor_types(id,type_name)
        return query_results
    if query_results:
        return change_to_node_template_model(query_results)
    else:
        return query_results


def get_tosca_template_get_dsl_definitions(id, anchors, derived_from):
    # interface_types_db.purge()
    if len(dsl_definitions_db) <= 1:
        object_list = get_tosca_template_model_by_id(id).dsl_definitions
        if object_list:
            for key in object_list:
                node = {key: object_list[key]}
                interface_types_db.insert(node)

    queries = []
    if derived_from:
        query = Query()
        queries.append(query.derived_from == derived_from)
    if anchors:
        for anchor in anchors:
            query = Query()
            queries.append(query.anchor == anchor)

    return query_db(queries, db=dsl_definitions_db)


def get_relationship_templates(id, type_name=None, derived_from=None):
    if len(relationship_template_db) <= 1:
        object_list = get_tosca_template_model_by_id(id).relationship_templates
        if object_list:
            for key in object_list:
                node = {key: object_list[key]}
                relationship_template_db.insert(node)

    queries = []
    if derived_from:
        query = Query()
        queries.append(query.derived_from == derived_from)
    if type_name:
        query = Query()
        queries.append(query.type == type_name)

    return query_db(queries, db=relationship_template_db)


def node_dict_2_node_template(id, node_name):
    tosca_template_dict = get_tosca_template_dict_by_id(id)
    if tosca_template_dict is None:
        return None
    tosca_template = get_tosca_template(tosca_template_dict)
    tosca_node_types = tosca_template.nodetemplates[0].type_definition.TOSCA_DEF
    all_custom_def = tosca_template.nodetemplates[0].custom_def
    all_node_types = {}
    all_node_types.update(tosca_node_types.items())
    all_node_types.update(all_custom_def.items())

    node_template_map_dict = get_node_templates(id, node_name=node_name)[0]
    the_node = tosca_helper.node_dict_2_node_template(node_template_map_dict.name,
                                                      node_template_map_dict.node_template.to_dict(), all_node_types)
    return the_node, all_node_types, all_custom_def


def get_all_ancestors_requirements(id, node_root_key):
    try:
        the_node, all_node_types, all_custom_def = node_dict_2_node_template(id, node_root_key)
    except Exception as e:
        return None
    parent_requirements = tosca_helper.get_all_ancestors_requirements(the_node, all_node_types, all_custom_def)
    return parent_requirements


def get_all_ancestor_properties(id, node_root_key):
    try:
        the_node, all_node_types, all_custom_def = node_dict_2_node_template(id, node_root_key)
    except Exception as e:
        return None
    properties = tosca_helper.get_all_ancestors_properties(the_node, all_node_types, all_custom_def)
    properties_list = []
    for prop in properties:
        prop_dict = {prop.name: prop.value}
        properties_list.append(prop_dict)
    return properties_list


def get_all_ancestor_types(id, node_root_key):
    try:
        the_node, all_node_types, all_custom_def = node_dict_2_node_template(id, node_root_key)
    except Exception as e:
        return None
    all_ancestor_types = tosca_helper.get_all_ancestors_types(the_node, all_node_types, all_custom_def)
    return all_ancestor_types


def get_parent_type_name(id, node_root_key):
    try:
        the_node, all_node_types, all_custom_def = node_dict_2_node_template(id, node_root_key)
    except Exception as e:
        return None
    return tosca_helper.get_node_template_parent_type(the_node)


def get_node_outputs(id, node_name):
    matching_outputs = {}
    matching_output_names = []
    tosca_template_dict = get_tosca_template_dict_by_id(id)
    tosca_template = get_tosca_template(tosca_template_dict)
    outputs = tosca_template.topology_template.outputs
    if not outputs:
        return None
    for output in outputs:
        if node_name == output.value.node_template_name:
            matching_output_names.append(output.name)

    # tosca_template_dict = deTOSCAfy_topology_template(tosca_template_dict)
    tosca_template_model = ToscaTemplateModel.from_dict(tosca_template_dict)
    for matching_output_name in matching_output_names:
        matching_outputs[matching_output_name] = tosca_template_model.topology_template.outputs[matching_output_name]

    return matching_outputs


def get_node_properties(id, node_name):
    node_template_map = get_node_templates(id, node_name=node_name)[0]

    properties = node_template_map.node_template.properties
    if properties:
        return properties
    return None


def get_node_requirements(id, node_name):
    node_template_map = get_node_templates(id, node_name=node_name)[0]
    requirements = node_template_map.node_template.requirements
    if requirements:
        return requirements
    return None


def get_node_attributes(id, node_name):
    node_template_map = get_node_templates(id, node_name=node_name)[0]
    attributes = node_template_map.node_template.attributes
    if attributes:
        return attributes
    return None


def get_related_nodes(id, node_name):
    tosca_template_dict = get_tosca_template_dict_by_id(id)
    tosca_template = get_tosca_template(tosca_template_dict)
    related_node_names = []
    for node_template in tosca_template.topology_template.nodetemplates:
        if node_template.name == node_name:
            related_nodes = node_template.related_nodes
            for related_node in related_nodes:
                related_node_names.append(related_node.name)
    related_nodes = []
    for name in related_node_names:
        related_node = get_node_templates(id, node_name=name)
        related_nodes = related_nodes + related_node
    logger.info('Node: '+node_name +' has related nodes: '+str(related_nodes))
    return related_nodes


def get_node_type_name(id, node_name):
    node_template_map = get_node_templates(id, node_name=node_name)[0]
    type_name = node_template_map.node_template.type
    if type_name:
        return type_name
    return None


def update(id, tosca_template_dict):
    tosca_template = get_tosca_template(tosca_template_dict)
    tosca_template_model = ToscaTemplateModel.from_dict(tosca_template_dict)
    doc_id = tosca_templates_db.update(tosca_template_dict, doc_ids=[int(id)])
    return doc_id


def set_node_properties(id, properties, node_name):
    properties = properties['properties']
    tosca_template_model = get_tosca_template_model_by_id(id)
    node_template_model = tosca_template_model.topology_template.node_templates[node_name]
    if node_template_model:
        if node_template_model.properties:
            node_template_model.properties.update(properties)
        else:
            node_template_model.properties = properties

        tosca_template_model.topology_template.node_templates[node_name] = node_template_model
        return update(id, tosca_template_model.to_dict())
    return None


def get_types(id, kind_of_type, has_interfaces, type_name, has_properties, has_attributes, has_requirements,
              has_capabilities, has_artifacts, derived_from):
    if kind_of_type == 'interface_types':
        return get_interface_types(id, interface_type=type_name)
    return None


def get_default_entry_value(entry):
    if 'default' in entry and 'required' in entry and entry['required']:
        return entry['default']
    return None


def get_all_interface_types(id, interface_type, parent_interfaces=None):
    if parent_interfaces is None:
        parent_interfaces = []
    interface = get_interface_types(id, interface_type=interface_type)[0]
    parent_interfaces.append(interface)
    if 'derived_from' in interface[interface_type]:
        return get_all_interface_types(id, interface[interface_type]['derived_from'], parent_interfaces)
    else:
        return parent_interfaces


def merge_interfaces(id, interface_type):
    all_interfaces = get_all_interface_types(id, interface_type=interface_type)
    if all_interfaces is None:
        return None

    all_inputs = {}
    all_operations = {}
    for interface in all_interfaces:
        interface = interface[next(iter(interface))]
        if 'inputs' in interface:
            all_inputs.update(interface['inputs'])
        for op in interface:
            if op != 'description' and op != 'derived_from' and op != 'inputs':
                all_operations[op] = interface[op]

    the_interface = {interface_type: all_operations}
    the_interface[interface_type]['inputs'] = all_inputs
    return the_interface


def get_default_interface(id, interface_type, instance_name, operation_name):
    the_interface = merge_interfaces(id, interface_type)
    if the_interface is None:
        return None

    interface = the_interface[next(iter(the_interface))]
    if operation_name not in interface:
        raise Exception(
            'Operation: ' + operation_name + ' not in interface: ' + interface_type + ' definition: ' + str(
                the_interface))

    inputs = interface['inputs']
    instance_inputs_list = []
    for key in inputs:
        default_value = get_default_entry_value(inputs[key])
        default_entry = {key: default_value}
        instance_inputs_list.append(default_entry)

    instance_inputs = {'inputs': instance_inputs_list}
    operation = {operation_name: instance_inputs}
    instance = {instance_name: operation}
    return instance
