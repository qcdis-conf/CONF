# coding: utf-8

"""
    Polemarch

     ### Polemarch is ansible based service for orchestration infrastructure.  * [Documentation](http://polemarch.readthedocs.io/) * [Issue Tracker](https://gitlab.com/vstconsulting/polemarch/issues) * [Source Code](https://gitlab.com/vstconsulting/polemarch)    # noqa: E501

    OpenAPI spec version: v2
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


import pprint
import re  # noqa: F401

import six


class ProjectHistory(object):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """

    """
    Attributes:
      swagger_types (dict): The key is attribute name
                            and the value is attribute type.
      attribute_map (dict): The key is attribute name
                            and the value is json key in definition.
    """
    swagger_types = {
        'id': 'int',
        'start_time': 'datetime',
        'executor': 'int',
        'initiator': 'int',
        'initiator_type': 'str',
        'revision': 'str',
        'inventory': 'int',
        'kind': 'str',
        'mode': 'str',
        'options': 'str',
        'status': 'str',
        'stop_time': 'datetime'
    }

    attribute_map = {
        'id': 'id',
        'start_time': 'start_time',
        'executor': 'executor',
        'initiator': 'initiator',
        'initiator_type': 'initiator_type',
        'revision': 'revision',
        'inventory': 'inventory',
        'kind': 'kind',
        'mode': 'mode',
        'options': 'options',
        'status': 'status',
        'stop_time': 'stop_time'
    }

    def __init__(self, id=None, start_time=None, executor=None, initiator=None, initiator_type=None, revision=None, inventory=None, kind=None, mode=None, options=None, status=None, stop_time=None):  # noqa: E501
        """ProjectHistory - a model defined in Swagger"""  # noqa: E501

        self._id = None
        self._start_time = None
        self._executor = None
        self._initiator = None
        self._initiator_type = None
        self._revision = None
        self._inventory = None
        self._kind = None
        self._mode = None
        self._options = None
        self._status = None
        self._stop_time = None
        self.discriminator = None

        if id is not None:
            self.id = id
        if start_time is not None:
            self.start_time = start_time
        if executor is not None:
            self.executor = executor
        if initiator is not None:
            self.initiator = initiator
        if initiator_type is not None:
            self.initiator_type = initiator_type
        if revision is not None:
            self.revision = revision
        if inventory is not None:
            self.inventory = inventory
        if kind is not None:
            self.kind = kind
        self.mode = mode
        if options is not None:
            self.options = options
        if status is not None:
            self.status = status
        if stop_time is not None:
            self.stop_time = stop_time

    @property
    def id(self):
        """Gets the id of this ProjectHistory.  # noqa: E501


        :return: The id of this ProjectHistory.  # noqa: E501
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this ProjectHistory.


        :param id: The id of this ProjectHistory.  # noqa: E501
        :type: int
        """

        self._id = id

    @property
    def start_time(self):
        """Gets the start_time of this ProjectHistory.  # noqa: E501


        :return: The start_time of this ProjectHistory.  # noqa: E501
        :rtype: datetime
        """
        return self._start_time

    @start_time.setter
    def start_time(self, start_time):
        """Sets the start_time of this ProjectHistory.


        :param start_time: The start_time of this ProjectHistory.  # noqa: E501
        :type: datetime
        """

        self._start_time = start_time

    @property
    def executor(self):
        """Gets the executor of this ProjectHistory.  # noqa: E501


        :return: The executor of this ProjectHistory.  # noqa: E501
        :rtype: int
        """
        return self._executor

    @executor.setter
    def executor(self, executor):
        """Sets the executor of this ProjectHistory.


        :param executor: The executor of this ProjectHistory.  # noqa: E501
        :type: int
        """

        self._executor = executor

    @property
    def initiator(self):
        """Gets the initiator of this ProjectHistory.  # noqa: E501


        :return: The initiator of this ProjectHistory.  # noqa: E501
        :rtype: int
        """
        return self._initiator

    @initiator.setter
    def initiator(self, initiator):
        """Sets the initiator of this ProjectHistory.


        :param initiator: The initiator of this ProjectHistory.  # noqa: E501
        :type: int
        """
        if initiator is not None and initiator > 2147483647:  # noqa: E501
            raise ValueError("Invalid value for `initiator`, must be a value less than or equal to `2147483647`")  # noqa: E501
        if initiator is not None and initiator < -2147483648:  # noqa: E501
            raise ValueError("Invalid value for `initiator`, must be a value greater than or equal to `-2147483648`")  # noqa: E501

        self._initiator = initiator

    @property
    def initiator_type(self):
        """Gets the initiator_type of this ProjectHistory.  # noqa: E501


        :return: The initiator_type of this ProjectHistory.  # noqa: E501
        :rtype: str
        """
        return self._initiator_type

    @initiator_type.setter
    def initiator_type(self, initiator_type):
        """Sets the initiator_type of this ProjectHistory.


        :param initiator_type: The initiator_type of this ProjectHistory.  # noqa: E501
        :type: str
        """
        if initiator_type is not None and len(initiator_type) > 50:
            raise ValueError("Invalid value for `initiator_type`, length must be less than or equal to `50`")  # noqa: E501
        if initiator_type is not None and len(initiator_type) < 1:
            raise ValueError("Invalid value for `initiator_type`, length must be greater than or equal to `1`")  # noqa: E501

        self._initiator_type = initiator_type

    @property
    def revision(self):
        """Gets the revision of this ProjectHistory.  # noqa: E501


        :return: The revision of this ProjectHistory.  # noqa: E501
        :rtype: str
        """
        return self._revision

    @revision.setter
    def revision(self, revision):
        """Sets the revision of this ProjectHistory.


        :param revision: The revision of this ProjectHistory.  # noqa: E501
        :type: str
        """
        if revision is not None and len(revision) > 256:
            raise ValueError("Invalid value for `revision`, length must be less than or equal to `256`")  # noqa: E501

        self._revision = revision

    @property
    def inventory(self):
        """Gets the inventory of this ProjectHistory.  # noqa: E501


        :return: The inventory of this ProjectHistory.  # noqa: E501
        :rtype: int
        """
        return self._inventory

    @inventory.setter
    def inventory(self, inventory):
        """Sets the inventory of this ProjectHistory.


        :param inventory: The inventory of this ProjectHistory.  # noqa: E501
        :type: int
        """

        self._inventory = inventory

    @property
    def kind(self):
        """Gets the kind of this ProjectHistory.  # noqa: E501


        :return: The kind of this ProjectHistory.  # noqa: E501
        :rtype: str
        """
        return self._kind

    @kind.setter
    def kind(self, kind):
        """Sets the kind of this ProjectHistory.


        :param kind: The kind of this ProjectHistory.  # noqa: E501
        :type: str
        """
        if kind is not None and len(kind) > 50:
            raise ValueError("Invalid value for `kind`, length must be less than or equal to `50`")  # noqa: E501
        if kind is not None and len(kind) < 1:
            raise ValueError("Invalid value for `kind`, length must be greater than or equal to `1`")  # noqa: E501

        self._kind = kind

    @property
    def mode(self):
        """Gets the mode of this ProjectHistory.  # noqa: E501


        :return: The mode of this ProjectHistory.  # noqa: E501
        :rtype: str
        """
        return self._mode

    @mode.setter
    def mode(self, mode):
        """Sets the mode of this ProjectHistory.


        :param mode: The mode of this ProjectHistory.  # noqa: E501
        :type: str
        """
        if mode is None:
            raise ValueError("Invalid value for `mode`, must not be `None`")  # noqa: E501
        if mode is not None and len(mode) > 256:
            raise ValueError("Invalid value for `mode`, length must be less than or equal to `256`")  # noqa: E501
        if mode is not None and len(mode) < 1:
            raise ValueError("Invalid value for `mode`, length must be greater than or equal to `1`")  # noqa: E501

        self._mode = mode

    @property
    def options(self):
        """Gets the options of this ProjectHistory.  # noqa: E501


        :return: The options of this ProjectHistory.  # noqa: E501
        :rtype: str
        """
        return self._options

    @options.setter
    def options(self, options):
        """Sets the options of this ProjectHistory.


        :param options: The options of this ProjectHistory.  # noqa: E501
        :type: str
        """

        self._options = options

    @property
    def status(self):
        """Gets the status of this ProjectHistory.  # noqa: E501


        :return: The status of this ProjectHistory.  # noqa: E501
        :rtype: str
        """
        return self._status

    @status.setter
    def status(self, status):
        """Sets the status of this ProjectHistory.


        :param status: The status of this ProjectHistory.  # noqa: E501
        :type: str
        """
        allowed_values = ["DELAY", "RUN", "OK", "ERROR", "OFFLINE", "INTERRUPTED"]  # noqa: E501
        if status not in allowed_values:
            raise ValueError(
                "Invalid value for `status` ({0}), must be one of {1}"  # noqa: E501
                .format(status, allowed_values)
            )

        self._status = status

    @property
    def stop_time(self):
        """Gets the stop_time of this ProjectHistory.  # noqa: E501


        :return: The stop_time of this ProjectHistory.  # noqa: E501
        :rtype: datetime
        """
        return self._stop_time

    @stop_time.setter
    def stop_time(self, stop_time):
        """Sets the stop_time of this ProjectHistory.


        :param stop_time: The stop_time of this ProjectHistory.  # noqa: E501
        :type: datetime
        """

        self._stop_time = stop_time

    def to_dict(self):
        """Returns the model properties as a dict"""
        result = {}

        for attr, _ in six.iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value
        if issubclass(ProjectHistory, dict):
            for key, value in self.items():
                result[key] = value

        return result

    def to_str(self):
        """Returns the string representation of the model"""
        return pprint.pformat(self.to_dict())

    def __repr__(self):
        """For `print` and `pprint`"""
        return self.to_str()

    def __eq__(self, other):
        """Returns true if both objects are equal"""
        if not isinstance(other, ProjectHistory):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other