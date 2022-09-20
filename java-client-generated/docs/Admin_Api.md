# Admin_Api

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addCategory**](Admin_Api.md#addCategory) | **POST** /admin/categories | Добавление новой категории
[**addEventToCompilation**](Admin_Api.md#addEventToCompilation) | **PATCH** /admin/compilations/{compId}/events/{eventId} | Добавить событие в подборку
[**delete**](Admin_Api.md#delete) | **DELETE** /admin/users/{userId} | Удаление пользователя
[**deleteCategory**](Admin_Api.md#deleteCategory) | **DELETE** /admin/categories/{catId} | Удаление категории
[**deleteCompilation**](Admin_Api.md#deleteCompilation) | **DELETE** /admin/compilations/{compId} | Удаление подборки
[**getEvents2**](Admin_Api.md#getEvents2) | **GET** /admin/events | Поиск событий
[**getUsers**](Admin_Api.md#getUsers) | **GET** /admin/users | Получение информации о пользователях
[**pin**](Admin_Api.md#pin) | **PATCH** /admin/compilations/{compId}/pin | Закрепить подборку на главной странице
[**publishEvent**](Admin_Api.md#publishEvent) | **PATCH** /admin/events/{eventId}/publish | Публикация события
[**registerUser**](Admin_Api.md#registerUser) | **POST** /admin/users | Добавление нового пользователя
[**rejectEvent**](Admin_Api.md#rejectEvent) | **PATCH** /admin/events/{eventId}/reject | Отклонение события
[**removeEventFromCompilation**](Admin_Api.md#removeEventFromCompilation) | **DELETE** /admin/compilations/{compId}/events/{eventId} | Удалить событие из подборки
[**saveCompilation**](Admin_Api.md#saveCompilation) | **POST** /admin/compilations | Добавление новой подборки
[**unpin**](Admin_Api.md#unpin) | **DELETE** /admin/compilations/{compId}/pin | Открепить подборку на главной странице
[**updateCategory**](Admin_Api.md#updateCategory) | **PATCH** /admin/categories | Изменение категории
[**updateEvent**](Admin_Api.md#updateEvent) | **PUT** /admin/events/{eventId} | Редактирование события

<a name="addCategory"></a>
# **addCategory**
> CategoryDto addCategory(body)

Добавление новой категории

Обратите внимание: имя категории должно быть уникальным

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
NewCategoryDto body = new NewCategoryDto(); // NewCategoryDto | данные добавляемой категории
try {
    CategoryDto result = apiInstance.addCategory(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#addCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewCategoryDto**](NewCategoryDto.md)| данные добавляемой категории |

### Return type

[**CategoryDto**](CategoryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addEventToCompilation"></a>
# **addEventToCompilation**
> addEventToCompilation(compId, eventId)

Добавить событие в подборку

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long compId = 789L; // Long | id подборки
Long eventId = 789L; // Long | id события
try {
    apiInstance.addEventToCompilation(compId, eventId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#addEventToCompilation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **compId** | **Long**| id подборки |
 **eventId** | **Long**| id события |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="delete"></a>
# **delete**
> delete(userId)

Удаление пользователя

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long userId = 789L; // Long | id пользователя
try {
    apiInstance.delete(userId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#delete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id пользователя |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteCategory"></a>
# **deleteCategory**
> deleteCategory(catId)

Удаление категории

Обратите внимание: с категорией не должно быть связано ни одного события.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long catId = 789L; // Long | id категории
try {
    apiInstance.deleteCategory(catId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#deleteCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **catId** | **Long**| id категории |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteCompilation"></a>
# **deleteCompilation**
> deleteCompilation(compId)

Удаление подборки

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long compId = 789L; // Long | id подборки
try {
    apiInstance.deleteCompilation(compId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#deleteCompilation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **compId** | **Long**| id подборки |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEvents2"></a>
# **getEvents2**
> List&lt;EventFullDto&gt; getEvents2(users, states, categories, rangeStart, rangeEnd, from, size)

Поиск событий

Эндпоинт возвращает полную информацию обо всех событиях подходящих под переданные условия

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
List<Long> users = Arrays.asList(56L); // List<Long> | список id пользователей, чьи события нужно найти
List<String> states = Arrays.asList("states_example"); // List<String> | список состояний в которых находятся искомые события
List<Long> categories = Arrays.asList(56L); // List<Long> | список id категорий в которых будет вестись поиск
String rangeStart = "rangeStart_example"; // String | дата и время не раньше которых должно произойти событие
String rangeEnd = "rangeEnd_example"; // String | дата и время не позже которых должно произойти событие
Integer from = 0; // Integer | количество событий, которые нужно пропустить для формирования текущего набора
Integer size = 10; // Integer | количество событий в наборе
try {
    List<EventFullDto> result = apiInstance.getEvents2(users, states, categories, rangeStart, rangeEnd, from, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#getEvents2");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **users** | [**List&lt;Long&gt;**](Long.md)| список id пользователей, чьи события нужно найти | [optional]
 **states** | [**List&lt;String&gt;**](String.md)| список состояний в которых находятся искомые события | [optional]
 **categories** | [**List&lt;Long&gt;**](Long.md)| список id категорий в которых будет вестись поиск | [optional]
 **rangeStart** | **String**| дата и время не раньше которых должно произойти событие | [optional]
 **rangeEnd** | **String**| дата и время не позже которых должно произойти событие | [optional]
 **from** | **Integer**| количество событий, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество событий в наборе | [optional] [default to 10]

### Return type

[**List&lt;EventFullDto&gt;**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getUsers"></a>
# **getUsers**
> List&lt;UserDto&gt; getUsers(ids, from, size)

Получение информации о пользователях

Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки), либо о конкретных (учитываются указанные идентификаторы)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
List<Long> ids = Arrays.asList(56L); // List<Long> | id пользователей
Integer from = 0; // Integer | количество элементов, которые нужно пропустить для формирования текущего набора
Integer size = 10; // Integer | количество элементов в наборе
try {
    List<UserDto> result = apiInstance.getUsers(ids, from, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#getUsers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**List&lt;Long&gt;**](Long.md)| id пользователей | [optional]
 **from** | **Integer**| количество элементов, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество элементов в наборе | [optional] [default to 10]

### Return type

[**List&lt;UserDto&gt;**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="pin"></a>
# **pin**
> pin(compId)

Закрепить подборку на главной странице

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long compId = 789L; // Long | id подборки
try {
    apiInstance.pin(compId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#pin");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **compId** | **Long**| id подборки |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publishEvent"></a>
# **publishEvent**
> EventFullDto publishEvent(eventId)

Публикация события

Обратите внимание:  - дата начала события должна быть не ранее чем за час от даты публикации. - событие должно быть в состоянии ожидания публикации

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long eventId = 789L; // Long | id события
try {
    EventFullDto result = apiInstance.publishEvent(eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#publishEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventId** | **Long**| id события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="registerUser"></a>
# **registerUser**
> UserDto registerUser(body)

Добавление нового пользователя

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
NewUserRequest body = new NewUserRequest(); // NewUserRequest | данные добавляемого пользователя
try {
    UserDto result = apiInstance.registerUser(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#registerUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewUserRequest**](NewUserRequest.md)| данные добавляемого пользователя |

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="rejectEvent"></a>
# **rejectEvent**
> EventFullDto rejectEvent(eventId)

Отклонение события

Обратите внимание: событие не должно быть опубликовано.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long eventId = 789L; // Long | id события
try {
    EventFullDto result = apiInstance.rejectEvent(eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#rejectEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventId** | **Long**| id события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="removeEventFromCompilation"></a>
# **removeEventFromCompilation**
> removeEventFromCompilation(compId, eventId)

Удалить событие из подборки

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long compId = 789L; // Long | id подборки
Long eventId = 789L; // Long | id события
try {
    apiInstance.removeEventFromCompilation(compId, eventId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#removeEventFromCompilation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **compId** | **Long**| id подборки |
 **eventId** | **Long**| id события |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="saveCompilation"></a>
# **saveCompilation**
> CompilationDto saveCompilation(body)

Добавление новой подборки

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
NewCompilationDto body = new NewCompilationDto(); // NewCompilationDto | данные новой подборки
try {
    CompilationDto result = apiInstance.saveCompilation(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#saveCompilation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewCompilationDto**](NewCompilationDto.md)| данные новой подборки |

### Return type

[**CompilationDto**](CompilationDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="unpin"></a>
# **unpin**
> unpin(compId)

Открепить подборку на главной странице

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
Long compId = 789L; // Long | id подборки
try {
    apiInstance.unpin(compId);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#unpin");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **compId** | **Long**| id подборки |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateCategory"></a>
# **updateCategory**
> CategoryDto updateCategory(body)

Изменение категории

Обратите внимание: имя категории должно быть уникальным

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
CategoryDto body = new CategoryDto(); // CategoryDto | Данные категории для изменения
try {
    CategoryDto result = apiInstance.updateCategory(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#updateCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CategoryDto**](CategoryDto.md)| Данные категории для изменения |

### Return type

[**CategoryDto**](CategoryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateEvent"></a>
# **updateEvent**
> EventFullDto updateEvent(body, eventId)

Редактирование события

Редактирование данных любого события администратором. Валидация данных не требуется.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Admin_Api;


Admin_Api apiInstance = new Admin_Api();
AdminUpdateEventRequest body = new AdminUpdateEventRequest(); // AdminUpdateEventRequest | Данные для изменения информации о событии
Long eventId = 789L; // Long | id события
try {
    EventFullDto result = apiInstance.updateEvent(body, eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Admin_Api#updateEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**AdminUpdateEventRequest**](AdminUpdateEventRequest.md)| Данные для изменения информации о событии |
 **eventId** | **Long**| id события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

