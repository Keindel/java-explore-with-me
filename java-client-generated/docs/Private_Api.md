# Private_Api

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addEvent**](Private_Api.md#addEvent) | **POST** /users/{userId}/events | Добавление нового события
[**addParticipationRequest**](Private_Api.md#addParticipationRequest) | **POST** /users/{userId}/requests | Добавление запроса от текущего пользователя на участие в событии
[**cancelEvent**](Private_Api.md#cancelEvent) | **PATCH** /users/{userId}/events/{eventId} | Отмена события добавленного текущим пользователем.
[**cancelParticipationRequest**](Private_Api.md#cancelParticipationRequest) | **PATCH** /users/{userId}/events/{eventId}/requests/{reqId}/reject | Отклонение чужой заявки на участие в событии текущего пользователя
[**cancelRequest**](Private_Api.md#cancelRequest) | **PATCH** /users/{userId}/requests/{requestId}/cancel | Отмена своего запроса на участие в событии
[**confirmParticipationRequest**](Private_Api.md#confirmParticipationRequest) | **PATCH** /users/{userId}/events/{eventId}/requests/{reqId}/confirm | Подтверждение чужой заявки на участие в событии текущего пользователя
[**getEvent**](Private_Api.md#getEvent) | **GET** /users/{userId}/events/{eventId} | Получение полной информации о событии добавленном текущим пользователем
[**getEventParticipants**](Private_Api.md#getEventParticipants) | **GET** /users/{userId}/events/{eventId}/requests | Получение информации о запросах на участие в событии текущего пользователя
[**getEvents**](Private_Api.md#getEvents) | **GET** /users/{userId}/events | Получение событий, добавленных текущим пользователем
[**getUserRequests**](Private_Api.md#getUserRequests) | **GET** /users/{userId}/requests | Получение информации о заявках текущего пользователя на участие в чужих событиях
[**updateEvent1**](Private_Api.md#updateEvent1) | **PATCH** /users/{userId}/events | Изменение события добавленного текущим пользователем

<a name="addEvent"></a>
# **addEvent**
> EventFullDto addEvent(body, userId)

Добавление нового события

Обратите внимание: дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
NewEventDto body = new NewEventDto(); // NewEventDto | данные добавляемого события
Long userId = 789L; // Long | id текущего пользователя
try {
    EventFullDto result = apiInstance.addEvent(body, userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#addEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewEventDto**](NewEventDto.md)| данные добавляемого события |
 **userId** | **Long**| id текущего пользователя |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addParticipationRequest"></a>
# **addParticipationRequest**
> ParticipationRequestDto addParticipationRequest(userId, eventId)

Добавление запроса от текущего пользователя на участие в событии

Обратите внимание: - нельзя добавить повторный запрос - инициатор события не может добавить запрос на участие в своём событии - нельзя участвовать в неопубликованном событии - если у события достигнут лимит запросов на участие - необходимо вернуть ошибку - если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long eventId = 789L; // Long | id события
try {
    ParticipationRequestDto result = apiInstance.addParticipationRequest(userId, eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#addParticipationRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="cancelEvent"></a>
# **cancelEvent**
> EventFullDto cancelEvent(userId, eventId)

Отмена события добавленного текущим пользователем.

Обратите внимание: Отменить можно только событие в состоянии ожидания модерации.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long eventId = 789L; // Long | id отменяемого события
try {
    EventFullDto result = apiInstance.cancelEvent(userId, eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#cancelEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id отменяемого события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="cancelParticipationRequest"></a>
# **cancelParticipationRequest**
> ParticipationRequestDto cancelParticipationRequest(userId, eventId, reqId)

Отклонение чужой заявки на участие в событии текущего пользователя

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long eventId = 789L; // Long | id события текущего пользователя
Long reqId = 789L; // Long | id заявки, которую отменяет текущий пользователь
try {
    ParticipationRequestDto result = apiInstance.cancelParticipationRequest(userId, eventId, reqId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#cancelParticipationRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события текущего пользователя |
 **reqId** | **Long**| id заявки, которую отменяет текущий пользователь |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="cancelRequest"></a>
# **cancelRequest**
> ParticipationRequestDto cancelRequest(userId, requestId)

Отмена своего запроса на участие в событии

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long requestId = 789L; // Long | id запроса на участие
try {
    ParticipationRequestDto result = apiInstance.cancelRequest(userId, requestId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#cancelRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **requestId** | **Long**| id запроса на участие |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="confirmParticipationRequest"></a>
# **confirmParticipationRequest**
> ParticipationRequestDto confirmParticipationRequest(userId, eventId, reqId)

Подтверждение чужой заявки на участие в событии текущего пользователя

Обратите внимание: - если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется - нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие - если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long eventId = 789L; // Long | id события текущего пользователя
Long reqId = 789L; // Long | id заявки, которую подтверждает текущий пользователь
try {
    ParticipationRequestDto result = apiInstance.confirmParticipationRequest(userId, eventId, reqId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#confirmParticipationRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события текущего пользователя |
 **reqId** | **Long**| id заявки, которую подтверждает текущий пользователь |

### Return type

[**ParticipationRequestDto**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEvent"></a>
# **getEvent**
> EventFullDto getEvent(userId, eventId)

Получение полной информации о событии добавленном текущим пользователем

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long eventId = 789L; // Long | id события
try {
    EventFullDto result = apiInstance.getEvent(userId, eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#getEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEventParticipants"></a>
# **getEventParticipants**
> List&lt;ParticipationRequestDto&gt; getEventParticipants(userId, eventId)

Получение информации о запросах на участие в событии текущего пользователя

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Long eventId = 789L; // Long | id события
try {
    List<ParticipationRequestDto> result = apiInstance.getEventParticipants(userId, eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#getEventParticipants");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **eventId** | **Long**| id события |

### Return type

[**List&lt;ParticipationRequestDto&gt;**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEvents"></a>
# **getEvents**
> List&lt;EventShortDto&gt; getEvents(userId, from, size)

Получение событий, добавленных текущим пользователем

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
Integer from = 0; // Integer | количество элементов, которые нужно пропустить для формирования текущего набора
Integer size = 10; // Integer | количество элементов в наборе
try {
    List<EventShortDto> result = apiInstance.getEvents(userId, from, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#getEvents");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |
 **from** | **Integer**| количество элементов, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество элементов в наборе | [optional] [default to 10]

### Return type

[**List&lt;EventShortDto&gt;**](EventShortDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getUserRequests"></a>
# **getUserRequests**
> List&lt;ParticipationRequestDto&gt; getUserRequests(userId)

Получение информации о заявках текущего пользователя на участие в чужих событиях

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
Long userId = 789L; // Long | id текущего пользователя
try {
    List<ParticipationRequestDto> result = apiInstance.getUserRequests(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#getUserRequests");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| id текущего пользователя |

### Return type

[**List&lt;ParticipationRequestDto&gt;**](ParticipationRequestDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateEvent1"></a>
# **updateEvent1**
> EventFullDto updateEvent1(body, userId)

Изменение события добавленного текущим пользователем

Обратите внимание: - изменить можно только отмененные события или события в состоянии ожидания модерации - если редактируется отменённое событие, то оно автоматически переходит в состояние ожидания модерации - дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Private_Api;


Private_Api apiInstance = new Private_Api();
UpdateEventRequest body = new UpdateEventRequest(); // UpdateEventRequest | Новые данные события
Long userId = 789L; // Long | id текущего пользователя
try {
    EventFullDto result = apiInstance.updateEvent1(body, userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Private_Api#updateEvent1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateEventRequest**](UpdateEventRequest.md)| Новые данные события |
 **userId** | **Long**| id текущего пользователя |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

