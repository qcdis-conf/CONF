# semaphore_client.ProjectsApi

All URIs are relative to *http://localhost:3000/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**projects_get**](ProjectsApi.md#projects_get) | **GET** /projects | Get projects
[**projects_post**](ProjectsApi.md#projects_post) | **POST** /projects | Create a new project


# **projects_get**
> list[Project] projects_get()

Get projects

### Example
```python
from __future__ import print_function
import time
import semaphore_client
from semaphore_client.rest import ApiException
from pprint import pprint

# Configure API key authorization: bearer
configuration = semaphore_client.Configuration()
configuration.api_key['Authorization'] = 'YOUR_API_KEY'
# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['Authorization'] = 'Bearer'
# Configure API key authorization: cookie
configuration = semaphore_client.Configuration()
configuration.api_key['Cookie'] = 'YOUR_API_KEY'
# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['Cookie'] = 'Bearer'

# create an instance of the API class
api_instance = semaphore_client.ProjectsApi(semaphore_client.ApiClient(configuration))

try:
    # Get projects
    api_response = api_instance.projects_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectsApi->projects_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**list[Project]**](Project.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **projects_post**
> projects_post(project)

Create a new project

### Example
```python
from __future__ import print_function
import time
import semaphore_client
from semaphore_client.rest import ApiException
from pprint import pprint

# Configure API key authorization: bearer
configuration = semaphore_client.Configuration()
configuration.api_key['Authorization'] = 'YOUR_API_KEY'
# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['Authorization'] = 'Bearer'
# Configure API key authorization: cookie
configuration = semaphore_client.Configuration()
configuration.api_key['Cookie'] = 'YOUR_API_KEY'
# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['Cookie'] = 'Bearer'

# create an instance of the API class
api_instance = semaphore_client.ProjectsApi(semaphore_client.ApiClient(configuration))
project = semaphore_client.ProjectRequest() # ProjectRequest | 

try:
    # Create a new project
    api_instance.projects_post(project)
except ApiException as e:
    print("Exception when calling ProjectsApi->projects_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project** | [**ProjectRequest**](ProjectRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

