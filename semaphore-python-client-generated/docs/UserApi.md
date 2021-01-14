# semaphore_client.UserApi

All URIs are relative to *http://localhost:3000/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**user_get**](UserApi.md#user_get) | **GET** /user | Fetch logged in user
[**user_tokens_api_token_id_delete**](UserApi.md#user_tokens_api_token_id_delete) | **DELETE** /user/tokens/{api_token_id} | Expires API token
[**user_tokens_get**](UserApi.md#user_tokens_get) | **GET** /user/tokens | Fetch API tokens for user
[**user_tokens_post**](UserApi.md#user_tokens_post) | **POST** /user/tokens | Create an API token
[**users_get**](UserApi.md#users_get) | **GET** /users | Fetches all users
[**users_post**](UserApi.md#users_post) | **POST** /users | Creates a user
[**users_user_id_delete**](UserApi.md#users_user_id_delete) | **DELETE** /users/{user_id} | Deletes user
[**users_user_id_get**](UserApi.md#users_user_id_get) | **GET** /users/{user_id} | Fetches a user profile
[**users_user_id_password_post**](UserApi.md#users_user_id_password_post) | **POST** /users/{user_id}/password | Updates user password
[**users_user_id_put**](UserApi.md#users_user_id_put) | **PUT** /users/{user_id} | Updates user details


# **user_get**
> User user_get()

Fetch logged in user

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))

try:
    # Fetch logged in user
    api_response = api_instance.user_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UserApi->user_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **user_tokens_api_token_id_delete**
> user_tokens_api_token_id_delete(api_token_id)

Expires API token

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))
api_token_id = 'api_token_id_example' # str | 

try:
    # Expires API token
    api_instance.user_tokens_api_token_id_delete(api_token_id)
except ApiException as e:
    print("Exception when calling UserApi->user_tokens_api_token_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_token_id** | **str**|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **user_tokens_get**
> list[APIToken] user_tokens_get()

Fetch API tokens for user

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))

try:
    # Fetch API tokens for user
    api_response = api_instance.user_tokens_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UserApi->user_tokens_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**list[APIToken]**](APIToken.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **user_tokens_post**
> APIToken user_tokens_post()

Create an API token

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))

try:
    # Create an API token
    api_response = api_instance.user_tokens_post()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UserApi->user_tokens_post: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**APIToken**](APIToken.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **users_get**
> list[User] users_get()

Fetches all users

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))

try:
    # Fetches all users
    api_response = api_instance.users_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UserApi->users_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**list[User]**](User.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **users_post**
> User users_post(user)

Creates a user

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))
user = semaphore_client.UserRequest() # UserRequest | 

try:
    # Creates a user
    api_response = api_instance.users_post(user)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UserApi->users_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | [**UserRequest**](UserRequest.md)|  | 

### Return type

[**User**](User.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **users_user_id_delete**
> users_user_id_delete(user_id)

Deletes user

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))
user_id = 56 # int | User ID

try:
    # Deletes user
    api_instance.users_user_id_delete(user_id)
except ApiException as e:
    print("Exception when calling UserApi->users_user_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **int**| User ID | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **users_user_id_get**
> User users_user_id_get(user_id)

Fetches a user profile

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))
user_id = 56 # int | User ID

try:
    # Fetches a user profile
    api_response = api_instance.users_user_id_get(user_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UserApi->users_user_id_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **int**| User ID | 

### Return type

[**User**](User.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **users_user_id_password_post**
> users_user_id_password_post(user_id, password)

Updates user password

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))
user_id = 56 # int | User ID
password = semaphore_client.Password() # Password | 

try:
    # Updates user password
    api_instance.users_user_id_password_post(user_id, password)
except ApiException as e:
    print("Exception when calling UserApi->users_user_id_password_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **int**| User ID | 
 **password** | [**Password**](Password.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **users_user_id_put**
> users_user_id_put(user_id, user)

Updates user details

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
api_instance = semaphore_client.UserApi(semaphore_client.ApiClient(configuration))
user_id = 56 # int | User ID
user = semaphore_client.UserRequest() # UserRequest | 

try:
    # Updates user details
    api_instance.users_user_id_put(user_id, user)
except ApiException as e:
    print("Exception when calling UserApi->users_user_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **int**| User ID | 
 **user** | [**UserRequest**](UserRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

