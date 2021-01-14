# semaphore_client.DefaultApi

All URIs are relative to *http://localhost:3000/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**events_get**](DefaultApi.md#events_get) | **GET** /events | Get Events related to Semaphore and projects you are part of
[**events_last_get**](DefaultApi.md#events_last_get) | **GET** /events/last | Get last 200 Events related to Semaphore and projects you are part of
[**info_get**](DefaultApi.md#info_get) | **GET** /info | Fetches information about semaphore
[**ping_get**](DefaultApi.md#ping_get) | **GET** /ping | PING test
[**upgrade_get**](DefaultApi.md#upgrade_get) | **GET** /upgrade | Check if new updates available and fetch /info
[**upgrade_post**](DefaultApi.md#upgrade_post) | **POST** /upgrade | Upgrade the server
[**ws_get**](DefaultApi.md#ws_get) | **GET** /ws | Websocket handler


# **events_get**
> list[Event] events_get()

Get Events related to Semaphore and projects you are part of

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
api_instance = semaphore_client.DefaultApi(semaphore_client.ApiClient(configuration))

try:
    # Get Events related to Semaphore and projects you are part of
    api_response = api_instance.events_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->events_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**list[Event]**](Event.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **events_last_get**
> list[Event] events_last_get()

Get last 200 Events related to Semaphore and projects you are part of

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
api_instance = semaphore_client.DefaultApi(semaphore_client.ApiClient(configuration))

try:
    # Get last 200 Events related to Semaphore and projects you are part of
    api_response = api_instance.events_last_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->events_last_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**list[Event]**](Event.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **info_get**
> InfoType info_get()

Fetches information about semaphore

you must be authenticated to use this

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
api_instance = semaphore_client.DefaultApi(semaphore_client.ApiClient(configuration))

try:
    # Fetches information about semaphore
    api_response = api_instance.info_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->info_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfoType**](InfoType.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **ping_get**
> Pong ping_get()

PING test

### Example
```python
from __future__ import print_function
import time
import semaphore_client
from semaphore_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = semaphore_client.DefaultApi()

try:
    # PING test
    api_response = api_instance.ping_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->ping_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Pong**](Pong.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: text/plain

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **upgrade_get**
> InfoType upgrade_get()

Check if new updates available and fetch /info

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
api_instance = semaphore_client.DefaultApi(semaphore_client.ApiClient(configuration))

try:
    # Check if new updates available and fetch /info
    api_response = api_instance.upgrade_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->upgrade_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfoType**](InfoType.md)

### Authorization

[bearer](../README.md#bearer), [cookie](../README.md#cookie)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain; charset=utf-8

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **upgrade_post**
> upgrade_post()

Upgrade the server

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
api_instance = semaphore_client.DefaultApi(semaphore_client.ApiClient(configuration))

try:
    # Upgrade the server
    api_instance.upgrade_post()
except ApiException as e:
    print("Exception when calling DefaultApi->upgrade_post: %s\n" % e)
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

# **ws_get**
> ws_get()

Websocket handler

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
api_instance = semaphore_client.DefaultApi(semaphore_client.ApiClient(configuration))

try:
    # Websocket handler
    api_instance.ws_get()
except ApiException as e:
    print("Exception when calling DefaultApi->ws_get: %s\n" % e)
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

