# swagger_client.DefaultApi

All URIs are relative to *https://localhost/tosca-sure/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_all_ancestor_properties**](DefaultApi.md#get_all_ancestor_properties) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/ancestors_properties | 
[**get_all_ancestor_types**](DefaultApi.md#get_all_ancestor_types) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/ancestors_types | 
[**get_ancestors_requirements**](DefaultApi.md#get_ancestors_requirements) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/ancestors_requirements | 
[**get_dsl_definitions**](DefaultApi.md#get_dsl_definitions) | **GET** /tosca_template/{id}/dsl_definitions | 
[**get_imports**](DefaultApi.md#get_imports) | **GET** /tosca_template/{id}/imports | 
[**get_node_outputs**](DefaultApi.md#get_node_outputs) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/outputs | 
[**get_node_properties**](DefaultApi.md#get_node_properties) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/properties | 
[**get_node_requirements**](DefaultApi.md#get_node_requirements) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/requirements | 
[**get_node_templates**](DefaultApi.md#get_node_templates) | **GET** /tosca_template/{id}/topology_template/node_templates | 
[**get_node_type_name**](DefaultApi.md#get_node_type_name) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/type_name | 
[**get_parent_type_name**](DefaultApi.md#get_parent_type_name) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/derived_from | 
[**get_related_nodes**](DefaultApi.md#get_related_nodes) | **GET** /tosca_template/{id}/topology_template/node_templates/{node_name}/related | 
[**get_relationship_templates**](DefaultApi.md#get_relationship_templates) | **GET** /tosca_template/{id}/relationship_templates | 
[**get_topology_template**](DefaultApi.md#get_topology_template) | **GET** /tosca_template/{id}/topology_template | 
[**get_tosca_template**](DefaultApi.md#get_tosca_template) | **GET** /tosca_template/{id} | 
[**get_types**](DefaultApi.md#get_types) | **GET** /tosca_template/{id}/types | 
[**set_node_properties**](DefaultApi.md#set_node_properties) | **PUT** /tosca_template/{id}/topology_template/node_templates/{node_name}/properties | 
[**upload_tosca_template**](DefaultApi.md#upload_tosca_template) | **POST** /tosca_template | upload a tosca template description file


# **get_all_ancestor_properties**
> list[dict(str, object)] get_all_ancestor_properties(id, node_name)



Recursively get all requirements all the way to the ROOT including the input node's

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_all_ancestor_properties(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_all_ancestor_properties: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**list[dict(str, object)]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_all_ancestor_types**
> list[str] get_all_ancestor_types(id, node_name)



Recursively get all requirements all the way to the ROOT including the input node's

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_all_ancestor_types(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_all_ancestor_types: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**list[str]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_ancestors_requirements**
> dict(str, object) get_ancestors_requirements(id, node_name)



Recursively get all requirements all the way to the ROOT including the input node's

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_ancestors_requirements(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_ancestors_requirements: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**dict(str, object)**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_dsl_definitions**
> list[dict(str, object)] get_dsl_definitions(id, anchors=anchors, derived_from=derived_from)



returns the interface types

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
anchors = ['anchors_example'] # list[str] | the anchors the definition is for (optional)
derived_from = 'derived_from_example' # str | derived from (optional)

try:
    # 
    api_response = api_instance.get_dsl_definitions(id, anchors=anchors, derived_from=derived_from)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_dsl_definitions: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **anchors** | [**list[str]**](str.md)| the anchors the definition is for | [optional] 
 **derived_from** | **str**| derived from | [optional] 

### Return type

**list[dict(str, object)]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_imports**
> list[dict(str, object)] get_imports(id)



returns the interface types

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed

try:
    # 
    api_response = api_instance.get_imports(id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_imports: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 

### Return type

**list[dict(str, object)]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_node_outputs**
> list[dict(str, object)] get_node_outputs(id, node_name)



s

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_node_outputs(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_node_outputs: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**list[dict(str, object)]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_node_properties**
> dict(str, object) get_node_properties(id, node_name)



s

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_node_properties(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_node_properties: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**dict(str, object)**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_node_requirements**
> dict(str, object) get_node_requirements(id, node_name)



Returns  the requirements for an input node as described in the template not in the node's definition 

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    api_response = api_instance.get_node_requirements(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_node_requirements: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**dict(str, object)**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_node_templates**
> list[NodeTemplateMap] get_node_templates(id, type_name=type_name, node_name=node_name, has_interfaces=has_interfaces, has_properties=has_properties, has_attributes=has_attributes, has_requirements=has_requirements, has_capabilities=has_capabilities, has_artifacts=has_artifacts)



returns nodes templates in topology

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
type_name = 'type_name_example' # str | The type (optional)
node_name = 'node_name_example' # str | the name (optional)
has_interfaces = true # bool | filter if has interfaces (optional)
has_properties = true # bool | filter if has properties (optional)
has_attributes = true # bool | filter if has attributes (optional)
has_requirements = true # bool | filter if has requirements (optional)
has_capabilities = true # bool | filter if has capabilities (optional)
has_artifacts = true # bool | filter if has artifacts (optional)

try:
    api_response = api_instance.get_node_templates(id, type_name=type_name, node_name=node_name, has_interfaces=has_interfaces, has_properties=has_properties, has_attributes=has_attributes, has_requirements=has_requirements, has_capabilities=has_capabilities, has_artifacts=has_artifacts)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_node_templates: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **type_name** | **str**| The type | [optional] 
 **node_name** | **str**| the name | [optional] 
 **has_interfaces** | **bool**| filter if has interfaces | [optional] 
 **has_properties** | **bool**| filter if has properties | [optional] 
 **has_attributes** | **bool**| filter if has attributes | [optional] 
 **has_requirements** | **bool**| filter if has requirements | [optional] 
 **has_capabilities** | **bool**| filter if has capabilities | [optional] 
 **has_artifacts** | **bool**| filter if has artifacts | [optional] 

### Return type

[**list[NodeTemplateMap]**](NodeTemplateMap.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_node_type_name**
> str get_node_type_name(id, node_name)





### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_node_type_name(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_node_type_name: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_parent_type_name**
> str get_parent_type_name(id, node_name)





### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_parent_type_name(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_parent_type_name: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_related_nodes**
> list[NodeTemplateMap] get_related_nodes(id, node_name)



s

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.get_related_nodes(id, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_related_nodes: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **node_name** | **str**| node_name | 

### Return type

[**list[NodeTemplateMap]**](NodeTemplateMap.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_relationship_templates**
> list[dict(str, object)] get_relationship_templates(id, type_name=type_name, derived_from=derived_from)



returns the interface types

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
type_name = 'type_name_example' # str | The relationship type (optional)
derived_from = 'derived_from_example' # str | derived from (optional)

try:
    # 
    api_response = api_instance.get_relationship_templates(id, type_name=type_name, derived_from=derived_from)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_relationship_templates: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **type_name** | **str**| The relationship type | [optional] 
 **derived_from** | **str**| derived from | [optional] 

### Return type

**list[dict(str, object)]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_topology_template**
> TopologyTemplate get_topology_template(id)



r

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed

try:
    api_response = api_instance.get_topology_template(id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_topology_template: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 

### Return type

[**TopologyTemplate**](TopologyTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_tosca_template**
> ToscaTemplate get_tosca_template(id)





### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed

try:
    api_response = api_instance.get_tosca_template(id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_tosca_template: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 

### Return type

[**ToscaTemplate**](ToscaTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_types**
> list[dict(str, object)] get_types(id, kind_of_type=kind_of_type, has_interfaces=has_interfaces, type_name=type_name, has_properties=has_properties, has_attributes=has_attributes, has_requirements=has_requirements, has_capabilities=has_capabilities, has_artifacts=has_artifacts, derived_from=derived_from)



returns the interface types

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
kind_of_type = 'kind_of_type_example' # str | the type we are looking for e.g. capability_types, artifact_types. etc. (optional)
has_interfaces = true # bool | filter if has interfaces (optional)
type_name = 'type_name_example' # str | The type_name (optional)
has_properties = true # bool | filter if has properties (optional)
has_attributes = true # bool | filter if has attributes (optional)
has_requirements = true # bool | filter if has requirements (optional)
has_capabilities = true # bool | filter if has capabilities (optional)
has_artifacts = true # bool | filter if has artifacts (optional)
derived_from = 'derived_from_example' # str | derived from (optional)

try:
    # 
    api_response = api_instance.get_types(id, kind_of_type=kind_of_type, has_interfaces=has_interfaces, type_name=type_name, has_properties=has_properties, has_attributes=has_attributes, has_requirements=has_requirements, has_capabilities=has_capabilities, has_artifacts=has_artifacts, derived_from=derived_from)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->get_types: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **kind_of_type** | **str**| the type we are looking for e.g. capability_types, artifact_types. etc. | [optional] 
 **has_interfaces** | **bool**| filter if has interfaces | [optional] 
 **type_name** | **str**| The type_name | [optional] 
 **has_properties** | **bool**| filter if has properties | [optional] 
 **has_attributes** | **bool**| filter if has attributes | [optional] 
 **has_requirements** | **bool**| filter if has requirements | [optional] 
 **has_capabilities** | **bool**| filter if has capabilities | [optional] 
 **has_artifacts** | **bool**| filter if has artifacts | [optional] 
 **derived_from** | **str**| derived from | [optional] 

### Return type

**list[dict(str, object)]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **set_node_properties**
> str set_node_properties(id, properties, node_name)



s

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
id = 'id_example' # str | ID of topolog template uplodaed
properties = NULL # object | 
node_name = 'node_name_example' # str | node_name

try:
    # 
    api_response = api_instance.set_node_properties(id, properties, node_name)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->set_node_properties: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of topolog template uplodaed | 
 **properties** | **object**|  | 
 **node_name** | **str**| node_name | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **upload_tosca_template**
> str upload_tosca_template(file)

upload a tosca template description file

upload and validate a tosca template description file

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
file = '/path/to/file.txt' # file | tosca Template description

try:
    # upload a tosca template description file
    api_response = api_instance.upload_tosca_template(file)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->upload_tosca_template: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **file**| tosca Template description | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

