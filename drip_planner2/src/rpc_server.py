# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import json
import logging
import os
import os.path
from builtins import print

import yaml
from planner.basic_planner import *
from planner.planner import *
import pika
import sys
import tempfile
import time
import logging
import base64
from utils import tosca as tosca_util

logger = logging.getLogger(__name__)


# if not getattr(logger, 'handler_set', None):
# logger.setLevel(logging.INFO)
# h = logging.StreamHandler()
# formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
# h.setFormatter(formatter)
# logger.addHandler(h)
# logger.handler_set = True


def init_chanel(args):
    global rabbitmq_host
    if len(args) > 1:
        rabbitmq_host = args[1]
        queue_name = args[2]  # planner_queue
    else:
        rabbitmq_host = '127.0.0.1'

    connection = pika.BlockingConnection(pika.ConnectionParameters(host=rabbitmq_host))
    channel = connection.channel()
    channel.queue_declare(queue=queue_name)
    return channel


def start(this_channel):
    this_channel.basic_qos(prefetch_count=1)
    this_channel.basic_consume(queue=queue_name, on_message_callback=on_request)
    logger.info(" [x] Awaiting RPC requests")
    this_channel.start_consuming()


def on_request(ch, method, props, body):
    response = handle_delivery(body)

    ch.basic_publish(exchange='',
                     routing_key=props.reply_to,
                     properties=pika.BasicProperties(correlation_id=
                                                     props.correlation_id),
                     body=str(response))
    ch.basic_ack(delivery_tag=method.delivery_tag)


def handle_delivery(message):
    logger.info("Got: " + str(message))
    try:
        message = message.decode()
    except (UnicodeDecodeError, AttributeError):
        pass
    parsed_json_message = json.loads(message)
    owner = parsed_json_message['owner']
    tosca_file_name = 'tosca_template'
    tosca_template_json = parsed_json_message['toscaTemplate']

    input_current_milli_time = lambda: int(round(time.time() * 1000))

    # rabbit = DRIPLoggingHandler(host=rabbitmq_host, port=5672, user=owner)
    # logger.addHandler(rabbit)

    try:
        tosca_folder_path = os.path.join(tempfile.gettempdir(), "planner_files", str(input_current_milli_time()))
    except NameError:
        import sys
        tosca_folder_path = os.path.dirname(os.path.abspath(sys.argv[0])) + os.path.join(tempfile.gettempdir(),
                                                                                         "planner_files",
                                                                                         str(current_milli_time()))

    if not os.path.exists(tosca_folder_path):
        os.makedirs(tosca_folder_path)
    input_tosca_file_path = os.path.join(tosca_folder_path, tosca_file_name + ".yml")
    with open(input_tosca_file_path, 'w') as outfile:
        outfile.write(yaml.dump(tosca_template_json))

    planner = Planner(input_tosca_file_path)
    required_nodes = planner.resolve_requirements()
    required_nodes = planner.set_infrastructure_specifications(required_nodes)
    planner.add_required_nodes_to_template(required_nodes)
    planned_template = tosca_util.get_tosca_template_as_yml(planner.template)
    logger.info("template ----: \n" + planned_template)
    template_dict = yaml.load(planned_template)
    response = {'toscaTemplate': template_dict}
    output_current_milli_time = lambda: int(round(time.time() * 1000))
    response["creationDate"] = output_current_milli_time
    response["parameters"] = []
    if queue_name == "planner_queue":
        logger.info("Planning")
    logger.info("Returning plan")
    logger.info("Output message:" + json.dumps(response))
    return json.dumps(response)


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO)
    if sys.argv[1] == "test_local":
        tosca_file_path = "../../TOSCA/application_example.yaml"
        # planner = BasicPlanner(tosca_file_path)
        test_planner = Planner(tosca_file_path)
        test_planner_required_nodes = test_planner.resolve_requirements()
        test_planner_required_nodes = test_planner.set_infrastructure_specifications(test_planner_required_nodes)
        test_planner.add_required_nodes_to_template(test_planner_required_nodes)
        template = tosca_util.get_tosca_template_as_yml(test_planner.template)
        logger.info("template ----: \n" + template)
    else:
        logger.info("Input args: " + sys.argv[0] + ' ' + sys.argv[1] + ' ' + sys.argv[2])
        channel = init_chanel(sys.argv)
        global queue_name
        queue_name = sys.argv[2]
        start(channel)
