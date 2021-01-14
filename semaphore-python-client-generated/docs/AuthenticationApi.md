# semaphore_client.AuthenticationApi

All URIs are relative to *http://localhost:3000/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**auth_login_post**](AuthenticationApi.md#auth_login_post) | **POST** /auth/login | Performs Login
[**auth_logout_post**](AuthenticationApi.md#auth_logout_post) | **POST** /auth/logout | Destroys current session
[**user_tokens_api_token_id_delete**](AuthenticationApi.md#user_tokens_api_token_id_delete) | **DELETE** /user/tokens/{api_token_id} | Expires API token
[**user_tokens_get**](AuthenticationApi.md#user_tokens_get) | **GET** /user/tokens | Fetch API tokens for user
[**user_tokens_post**](AuthenticationApi.md#user_tokens_post) | **POST** /user/tokens | Create an API token


# **auth_login_post**
> auth_login_post(login_body)

Performs Login

Upon success you will be logged in 

### Example
```python
from __future__ import print_function
import time
import semaphore_client
from semaphore_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = semaphore_client.AuthenticationApi()
login_body = semaphore_client.Login() # Login | 

try:
    # Performs Login
    api_instance.auth_login_post(login_body)
except ApiException as e:
    print("Exception when calling AuthenticationApi->auth_login_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **login_body** | [**Login**](Login.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **auth_logout_post**
> auth_logout_post()

Destroys current session

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
api_instance = semaphore_client.AuthenticationApi(semaphore_client.ApiClient(configuration))

try:
    # Destroys current session
    api_instance.auth_logout_post()
except ApiException as e:
    print("Exception when calling AuthenticationApi->auth_logout_post: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

void (empty response body)

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
api_instance = semaphore_client.AuthenticationApi(semaphore_client.ApiClient(configuration))
api_token_id = 'api_token_id_example' # str | 

try:
    # Expires API token
    api_instance.user_tokens_api_token_id_delete(api_token_id)
except ApiException as e:
    print("Exception when calling AuthenticationApi->user_tokens_api_token_id_delete: %s\n" % e)
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
api_instance = semaphore_client.AuthenticationApi(semaphore_client.ApiClient(configuration))

try:
    # Fetch API tokens for user
    api_response = api_instance.user_tokens_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AuthenticationApi->user_tokens_get: %s\n" % e)
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
api_instance = semaphore_client.AuthenticationApi(semaphore_client.ApiClient(configuration))

try:
    # Create an API token
    api_response = api_instance.user_tokens_post()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AuthenticationApi->user_tokens_post: %s\n" % e)
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

