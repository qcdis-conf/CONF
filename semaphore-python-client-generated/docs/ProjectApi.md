# semaphore_client.ProjectApi

All URIs are relative to *http://localhost:3000/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**project_project_id_delete**](ProjectApi.md#project_project_id_delete) | **DELETE** /project/{project_id} | Delete project
[**project_project_id_environment_environment_id_delete**](ProjectApi.md#project_project_id_environment_environment_id_delete) | **DELETE** /project/{project_id}/environment/{environment_id} | Removes environment
[**project_project_id_environment_environment_id_put**](ProjectApi.md#project_project_id_environment_environment_id_put) | **PUT** /project/{project_id}/environment/{environment_id} | Update environment
[**project_project_id_environment_get**](ProjectApi.md#project_project_id_environment_get) | **GET** /project/{project_id}/environment | Get environment
[**project_project_id_environment_post**](ProjectApi.md#project_project_id_environment_post) | **POST** /project/{project_id}/environment | Add environment
[**project_project_id_events_get**](ProjectApi.md#project_project_id_events_get) | **GET** /project/{project_id}/events | Get Events related to this project
[**project_project_id_get**](ProjectApi.md#project_project_id_get) | **GET** /project/{project_id} | Fetch project
[**project_project_id_inventory_get**](ProjectApi.md#project_project_id_inventory_get) | **GET** /project/{project_id}/inventory | Get inventory
[**project_project_id_inventory_inventory_id_delete**](ProjectApi.md#project_project_id_inventory_inventory_id_delete) | **DELETE** /project/{project_id}/inventory/{inventory_id} | Removes inventory
[**project_project_id_inventory_inventory_id_put**](ProjectApi.md#project_project_id_inventory_inventory_id_put) | **PUT** /project/{project_id}/inventory/{inventory_id} | Updates inventory
[**project_project_id_inventory_post**](ProjectApi.md#project_project_id_inventory_post) | **POST** /project/{project_id}/inventory | create inventory
[**project_project_id_keys_get**](ProjectApi.md#project_project_id_keys_get) | **GET** /project/{project_id}/keys | Get access keys linked to project
[**project_project_id_keys_key_id_delete**](ProjectApi.md#project_project_id_keys_key_id_delete) | **DELETE** /project/{project_id}/keys/{key_id} | Removes access key
[**project_project_id_keys_key_id_put**](ProjectApi.md#project_project_id_keys_key_id_put) | **PUT** /project/{project_id}/keys/{key_id} | Updates access key
[**project_project_id_keys_post**](ProjectApi.md#project_project_id_keys_post) | **POST** /project/{project_id}/keys | Add access key
[**project_project_id_put**](ProjectApi.md#project_project_id_put) | **PUT** /project/{project_id} | Update project
[**project_project_id_repositories_get**](ProjectApi.md#project_project_id_repositories_get) | **GET** /project/{project_id}/repositories | Get repositories
[**project_project_id_repositories_post**](ProjectApi.md#project_project_id_repositories_post) | **POST** /project/{project_id}/repositories | Add repository
[**project_project_id_repositories_repository_id_delete**](ProjectApi.md#project_project_id_repositories_repository_id_delete) | **DELETE** /project/{project_id}/repositories/{repository_id} | Removes repository
[**project_project_id_tasks_get**](ProjectApi.md#project_project_id_tasks_get) | **GET** /project/{project_id}/tasks | Get Tasks related to current project
[**project_project_id_tasks_last_get**](ProjectApi.md#project_project_id_tasks_last_get) | **GET** /project/{project_id}/tasks/last | Get last 200 Tasks related to current project
[**project_project_id_tasks_post**](ProjectApi.md#project_project_id_tasks_post) | **POST** /project/{project_id}/tasks | Starts a job
[**project_project_id_tasks_task_id_delete**](ProjectApi.md#project_project_id_tasks_task_id_delete) | **DELETE** /project/{project_id}/tasks/{task_id} | Deletes task (including output)
[**project_project_id_tasks_task_id_get**](ProjectApi.md#project_project_id_tasks_task_id_get) | **GET** /project/{project_id}/tasks/{task_id} | Get a single task
[**project_project_id_tasks_task_id_output_get**](ProjectApi.md#project_project_id_tasks_task_id_output_get) | **GET** /project/{project_id}/tasks/{task_id}/output | Get task output
[**project_project_id_templates_get**](ProjectApi.md#project_project_id_templates_get) | **GET** /project/{project_id}/templates | Get template
[**project_project_id_templates_post**](ProjectApi.md#project_project_id_templates_post) | **POST** /project/{project_id}/templates | create template
[**project_project_id_templates_template_id_delete**](ProjectApi.md#project_project_id_templates_template_id_delete) | **DELETE** /project/{project_id}/templates/{template_id} | Removes template
[**project_project_id_templates_template_id_put**](ProjectApi.md#project_project_id_templates_template_id_put) | **PUT** /project/{project_id}/templates/{template_id} | Updates template
[**project_project_id_users_get**](ProjectApi.md#project_project_id_users_get) | **GET** /project/{project_id}/users | Get users linked to project
[**project_project_id_users_post**](ProjectApi.md#project_project_id_users_post) | **POST** /project/{project_id}/users | Link user to project
[**project_project_id_users_user_id_admin_delete**](ProjectApi.md#project_project_id_users_user_id_admin_delete) | **DELETE** /project/{project_id}/users/{user_id}/admin | Revoke admin privileges
[**project_project_id_users_user_id_admin_post**](ProjectApi.md#project_project_id_users_user_id_admin_post) | **POST** /project/{project_id}/users/{user_id}/admin | Makes user admin
[**project_project_id_users_user_id_delete**](ProjectApi.md#project_project_id_users_user_id_delete) | **DELETE** /project/{project_id}/users/{user_id} | Removes user from project


# **project_project_id_delete**
> project_project_id_delete(project_id)

Delete project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID

try:
    # Delete project
    api_instance.project_project_id_delete(project_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_environment_environment_id_delete**
> project_project_id_environment_environment_id_delete(project_id, environment_id)

Removes environment

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
environment_id = 56 # int | environment ID

try:
    # Removes environment
    api_instance.project_project_id_environment_environment_id_delete(project_id, environment_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_environment_environment_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **environment_id** | **int**| environment ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_environment_environment_id_put**
> project_project_id_environment_environment_id_put(project_id, environment_id, environment)

Update environment

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
environment_id = 56 # int | environment ID
environment = semaphore_client.EnvironmentRequest() # EnvironmentRequest | 

try:
    # Update environment
    api_instance.project_project_id_environment_environment_id_put(project_id, environment_id, environment)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_environment_environment_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **environment_id** | **int**| environment ID | 
 **environment** | [**EnvironmentRequest**](EnvironmentRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_environment_get**
> list[Environment] project_project_id_environment_get(project_id, sort, order)

Get environment

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
sort = 'sort_example' # str | sorting name
order = 'order_example' # str | ordering manner

try:
    # Get environment
    api_response = api_instance.project_project_id_environment_get(project_id, sort, order)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_environment_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **sort** | **str**| sorting name | 
 **order** | **str**| ordering manner | 

### Return type

[**list[Environment]**](Environment.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_environment_post**
> project_project_id_environment_post(project_id, environment)

Add environment

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
environment = semaphore_client.EnvironmentRequest() # EnvironmentRequest | 

try:
    # Add environment
    api_instance.project_project_id_environment_post(project_id, environment)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_environment_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **environment** | [**EnvironmentRequest**](EnvironmentRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_events_get**
> list[Event] project_project_id_events_get(project_id)

Get Events related to this project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID

try:
    # Get Events related to this project
    api_response = api_instance.project_project_id_events_get(project_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_events_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 

### Return type

[**list[Event]**](Event.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_get**
> Project project_project_id_get(project_id)

Fetch project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID

try:
    # Fetch project
    api_response = api_instance.project_project_id_get(project_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 

### Return type

[**Project**](Project.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_inventory_get**
> list[Inventory] project_project_id_inventory_get(project_id, sort, order)

Get inventory

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
sort = 'sort_example' # str | sorting name
order = 'order_example' # str | ordering manner

try:
    # Get inventory
    api_response = api_instance.project_project_id_inventory_get(project_id, sort, order)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_inventory_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **sort** | **str**| sorting name | 
 **order** | **str**| ordering manner | 

### Return type

[**list[Inventory]**](Inventory.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_inventory_inventory_id_delete**
> project_project_id_inventory_inventory_id_delete(project_id, inventory_id)

Removes inventory

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
inventory_id = 56 # int | inventory ID

try:
    # Removes inventory
    api_instance.project_project_id_inventory_inventory_id_delete(project_id, inventory_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_inventory_inventory_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **inventory_id** | **int**| inventory ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_inventory_inventory_id_put**
> project_project_id_inventory_inventory_id_put(project_id, inventory_id, inventory)

Updates inventory

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
inventory_id = 56 # int | inventory ID
inventory = semaphore_client.InventoryRequest() # InventoryRequest | 

try:
    # Updates inventory
    api_instance.project_project_id_inventory_inventory_id_put(project_id, inventory_id, inventory)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_inventory_inventory_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **inventory_id** | **int**| inventory ID | 
 **inventory** | [**InventoryRequest**](InventoryRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_inventory_post**
> Inventory project_project_id_inventory_post(project_id, inventory)

create inventory

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
inventory = semaphore_client.InventoryRequest() # InventoryRequest | 

try:
    # create inventory
    api_response = api_instance.project_project_id_inventory_post(project_id, inventory)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_inventory_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **inventory** | [**InventoryRequest**](InventoryRequest.md)|  | 

### Return type

[**Inventory**](Inventory.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_keys_get**
> list[AccessKey] project_project_id_keys_get(project_id, sort, order, key_type=key_type)

Get access keys linked to project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
sort = 'sort_example' # str | sorting name
order = 'order_example' # str | ordering manner
key_type = 'key_type_example' # str | Filter by key type (optional)

try:
    # Get access keys linked to project
    api_response = api_instance.project_project_id_keys_get(project_id, sort, order, key_type=key_type)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_keys_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **sort** | **str**| sorting name | 
 **order** | **str**| ordering manner | 
 **key_type** | **str**| Filter by key type | [optional] 

### Return type

[**list[AccessKey]**](AccessKey.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_keys_key_id_delete**
> project_project_id_keys_key_id_delete(project_id, key_id)

Removes access key

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
key_id = 56 # int | key ID

try:
    # Removes access key
    api_instance.project_project_id_keys_key_id_delete(project_id, key_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_keys_key_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **key_id** | **int**| key ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_keys_key_id_put**
> project_project_id_keys_key_id_put(project_id, key_id, access_key)

Updates access key

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
key_id = 56 # int | key ID
access_key = semaphore_client.AccessKeyRequest() # AccessKeyRequest | 

try:
    # Updates access key
    api_instance.project_project_id_keys_key_id_put(project_id, key_id, access_key)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_keys_key_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **key_id** | **int**| key ID | 
 **access_key** | [**AccessKeyRequest**](AccessKeyRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_keys_post**
> project_project_id_keys_post(project_id, access_key)

Add access key

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
access_key = semaphore_client.AccessKeyRequest() # AccessKeyRequest | 

try:
    # Add access key
    api_instance.project_project_id_keys_post(project_id, access_key)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_keys_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **access_key** | [**AccessKeyRequest**](AccessKeyRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_put**
> project_project_id_put(project_id, project)

Update project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
project = semaphore_client.Project1() # Project1 | 

try:
    # Update project
    api_instance.project_project_id_put(project_id, project)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **project** | [**Project1**](Project1.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_repositories_get**
> list[Repository] project_project_id_repositories_get(project_id, sort, order)

Get repositories

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
sort = 'sort_example' # str | sorting name
order = 'order_example' # str | ordering manner

try:
    # Get repositories
    api_response = api_instance.project_project_id_repositories_get(project_id, sort, order)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_repositories_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **sort** | **str**| sorting name | 
 **order** | **str**| ordering manner | 

### Return type

[**list[Repository]**](Repository.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_repositories_post**
> project_project_id_repositories_post(project_id, repository)

Add repository

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
repository = semaphore_client.RepositoryRequest() # RepositoryRequest | 

try:
    # Add repository
    api_instance.project_project_id_repositories_post(project_id, repository)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_repositories_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **repository** | [**RepositoryRequest**](RepositoryRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_repositories_repository_id_delete**
> project_project_id_repositories_repository_id_delete(project_id, repository_id)

Removes repository

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
repository_id = 56 # int | repository ID

try:
    # Removes repository
    api_instance.project_project_id_repositories_repository_id_delete(project_id, repository_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_repositories_repository_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **repository_id** | **int**| repository ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_tasks_get**
> list[Task] project_project_id_tasks_get(project_id)

Get Tasks related to current project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID

try:
    # Get Tasks related to current project
    api_response = api_instance.project_project_id_tasks_get(project_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_tasks_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 

### Return type

[**list[Task]**](Task.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_tasks_last_get**
> list[Task] project_project_id_tasks_last_get(project_id)

Get last 200 Tasks related to current project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID

try:
    # Get last 200 Tasks related to current project
    api_response = api_instance.project_project_id_tasks_last_get(project_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_tasks_last_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 

### Return type

[**list[Task]**](Task.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_tasks_post**
> Task project_project_id_tasks_post(project_id, task)

Starts a job

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
task = semaphore_client.Task() # Task | 

try:
    # Starts a job
    api_response = api_instance.project_project_id_tasks_post(project_id, task)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_tasks_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **task** | [**Task**](Task.md)|  | 

### Return type

[**Task**](Task.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_tasks_task_id_delete**
> project_project_id_tasks_task_id_delete(project_id, task_id)

Deletes task (including output)

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
task_id = 56 # int | task ID

try:
    # Deletes task (including output)
    api_instance.project_project_id_tasks_task_id_delete(project_id, task_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_tasks_task_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **task_id** | **int**| task ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_tasks_task_id_get**
> Task project_project_id_tasks_task_id_get(project_id, task_id)

Get a single task

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
task_id = 56 # int | task ID

try:
    # Get a single task
    api_response = api_instance.project_project_id_tasks_task_id_get(project_id, task_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_tasks_task_id_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **task_id** | **int**| task ID | 

### Return type

[**Task**](Task.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_tasks_task_id_output_get**
> list[TaskOutput] project_project_id_tasks_task_id_output_get(project_id, task_id)

Get task output

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
task_id = 56 # int | task ID

try:
    # Get task output
    api_response = api_instance.project_project_id_tasks_task_id_output_get(project_id, task_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_tasks_task_id_output_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **task_id** | **int**| task ID | 

### Return type

[**list[TaskOutput]**](TaskOutput.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_templates_get**
> list[Template] project_project_id_templates_get(project_id, sort, order)

Get template

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
sort = 'sort_example' # str | sorting name
order = 'order_example' # str | ordering manner

try:
    # Get template
    api_response = api_instance.project_project_id_templates_get(project_id, sort, order)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_templates_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **sort** | **str**| sorting name | 
 **order** | **str**| ordering manner | 

### Return type

[**list[Template]**](Template.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_templates_post**
> Template project_project_id_templates_post(project_id, template)

create template

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
template = semaphore_client.TemplateRequest() # TemplateRequest | 

try:
    # create template
    api_response = api_instance.project_project_id_templates_post(project_id, template)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_templates_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **template** | [**TemplateRequest**](TemplateRequest.md)|  | 

### Return type

[**Template**](Template.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_templates_template_id_delete**
> project_project_id_templates_template_id_delete(project_id, template_id)

Removes template

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
template_id = 56 # int | template ID

try:
    # Removes template
    api_instance.project_project_id_templates_template_id_delete(project_id, template_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_templates_template_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **template_id** | **int**| template ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_templates_template_id_put**
> project_project_id_templates_template_id_put(project_id, template_id, template)

Updates template

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
template_id = 56 # int | template ID
template = semaphore_client.TemplateRequest() # TemplateRequest | 

try:
    # Updates template
    api_instance.project_project_id_templates_template_id_put(project_id, template_id, template)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_templates_template_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **template_id** | **int**| template ID | 
 **template** | [**TemplateRequest**](TemplateRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_users_get**
> list[User] project_project_id_users_get(project_id, sort, order)

Get users linked to project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
sort = 'sort_example' # str | sorting name
order = 'order_example' # str | ordering manner

try:
    # Get users linked to project
    api_response = api_instance.project_project_id_users_get(project_id, sort, order)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_users_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **sort** | **str**| sorting name | 
 **order** | **str**| ordering manner | 

### Return type

[**list[User]**](User.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_users_post**
> project_project_id_users_post(project_id, user)

Link user to project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
user = semaphore_client.User1() # User1 | 

try:
    # Link user to project
    api_instance.project_project_id_users_post(project_id, user)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_users_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **user** | [**User1**](User1.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_users_user_id_admin_delete**
> project_project_id_users_user_id_admin_delete(project_id, user_id)

Revoke admin privileges

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
user_id = 56 # int | User ID

try:
    # Revoke admin privileges
    api_instance.project_project_id_users_user_id_admin_delete(project_id, user_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_users_user_id_admin_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **user_id** | **int**| User ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_users_user_id_admin_post**
> project_project_id_users_user_id_admin_post(project_id, user_id)

Makes user admin

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
user_id = 56 # int | User ID

try:
    # Makes user admin
    api_instance.project_project_id_users_user_id_admin_post(project_id, user_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_users_user_id_admin_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **user_id** | **int**| User ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **project_project_id_users_user_id_delete**
> project_project_id_users_user_id_delete(project_id, user_id)

Removes user from project

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
api_instance = semaphore_client.ProjectApi(semaphore_client.ApiClient(configuration))
project_id = 56 # int | Project ID
user_id = 56 # int | User ID

try:
    # Removes user from project
    api_instance.project_project_id_users_user_id_delete(project_id, user_id)
except ApiException as e:
    print("Exception when calling ProjectApi->project_project_id_users_user_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **project_id** | **int**| Project ID | 
 **user_id** | **int**| User ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

