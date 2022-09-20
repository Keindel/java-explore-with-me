# Public_Api

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCategories**](Public_Api.md#getCategories) | **GET** /categories | Получение категорий
[**getCategory**](Public_Api.md#getCategory) | **GET** /categories/{catId} | Получение информации о категории по её идентификатору
[**getCompilation**](Public_Api.md#getCompilation) | **GET** /compilations/{compId} | Получение подборки событий по его id
[**getCompilations**](Public_Api.md#getCompilations) | **GET** /compilations | Получение подборок событий
[**getEvent1**](Public_Api.md#getEvent1) | **GET** /events/{id} | Получение подробной информации об опубликованном событии по его идентификатору
[**getEvents1**](Public_Api.md#getEvents1) | **GET** /events | Получение событий с возможностью фильтрации

<a name="getCategories"></a>
# **getCategories**
> List&lt;CategoryDto&gt; getCategories(from, size)

Получение категорий

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Public_Api;


Public_Api apiInstance = new Public_Api();
Integer from = 0; // Integer | количество категорий, которые нужно пропустить для формирования текущего набора
Integer size = 10; // Integer | количество категорий в наборе
try {
    List<CategoryDto> result = apiInstance.getCategories(from, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Public_Api#getCategories");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from** | **Integer**| количество категорий, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество категорий в наборе | [optional] [default to 10]

### Return type

[**List&lt;CategoryDto&gt;**](CategoryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCategory"></a>
# **getCategory**
> CategoryDto getCategory(catId)

Получение информации о категории по её идентификатору

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Public_Api;


Public_Api apiInstance = new Public_Api();
Long catId = 789L; // Long | id категории
try {
    CategoryDto result = apiInstance.getCategory(catId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Public_Api#getCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **catId** | **Long**| id категории |

### Return type

[**CategoryDto**](CategoryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCompilation"></a>
# **getCompilation**
> CompilationDto getCompilation(compId)

Получение подборки событий по его id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Public_Api;


Public_Api apiInstance = new Public_Api();
Long compId = 789L; // Long | id подборки
try {
    CompilationDto result = apiInstance.getCompilation(compId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Public_Api#getCompilation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **compId** | **Long**| id подборки |

### Return type

[**CompilationDto**](CompilationDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCompilations"></a>
# **getCompilations**
> List&lt;CompilationDto&gt; getCompilations(pinned, from, size)

Получение подборок событий

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Public_Api;


Public_Api apiInstance = new Public_Api();
Boolean pinned = true; // Boolean | искать только закрепленные/не закрепленные подборки
Integer from = 0; // Integer | количество элементов, которые нужно пропустить для формирования текущего набора
Integer size = 10; // Integer | количество элементов в наборе
try {
    List<CompilationDto> result = apiInstance.getCompilations(pinned, from, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Public_Api#getCompilations");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pinned** | **Boolean**| искать только закрепленные/не закрепленные подборки | [optional]
 **from** | **Integer**| количество элементов, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество элементов в наборе | [optional] [default to 10]

### Return type

[**List&lt;CompilationDto&gt;**](CompilationDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEvent1"></a>
# **getEvent1**
> EventFullDto getEvent1(id)

Получение подробной информации об опубликованном событии по его идентификатору

Обратите внимание: - событие должно быть опубликовано - информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Public_Api;


Public_Api apiInstance = new Public_Api();
Long id = 789L; // Long | id события
try {
    EventFullDto result = apiInstance.getEvent1(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Public_Api#getEvent1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id события |

### Return type

[**EventFullDto**](EventFullDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEvents1"></a>
# **getEvents1**
> List&lt;EventShortDto&gt; getEvents1(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size)

Получение событий с возможностью фильтрации

Обратите внимание:  - это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события - текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв - если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени - информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.Public_Api;


Public_Api apiInstance = new Public_Api();
String text = "text_example"; // String | текст для поиска в содержимом аннотации и подробном описании события
List<Long> categories = Arrays.asList(56L); // List<Long> | список идентификаторов категорий в которых будет вестись поиск
Boolean paid = true; // Boolean | поиск только платных/бесплатных событий
String rangeStart = "rangeStart_example"; // String | дата и время не раньше которых должно произойти событие
String rangeEnd = "rangeEnd_example"; // String | дата и время не позже которых должно произойти событие
Boolean onlyAvailable = false; // Boolean | только события у которых не исчерпан лимит запросов на участие
String sort = "sort_example"; // String | Вариант сортировки: по дате события или по количеству просмотров
Integer from = 0; // Integer | количество событий, которые нужно пропустить для формирования текущего набора
Integer size = 10; // Integer | количество событий в наборе
try {
    List<EventShortDto> result = apiInstance.getEvents1(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Public_Api#getEvents1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **text** | **String**| текст для поиска в содержимом аннотации и подробном описании события | [optional]
 **categories** | [**List&lt;Long&gt;**](Long.md)| список идентификаторов категорий в которых будет вестись поиск | [optional]
 **paid** | **Boolean**| поиск только платных/бесплатных событий | [optional]
 **rangeStart** | **String**| дата и время не раньше которых должно произойти событие | [optional]
 **rangeEnd** | **String**| дата и время не позже которых должно произойти событие | [optional]
 **onlyAvailable** | **Boolean**| только события у которых не исчерпан лимит запросов на участие | [optional] [default to false]
 **sort** | **String**| Вариант сортировки: по дате события или по количеству просмотров | [optional] [enum: EVENT_DATE, VIEWS]
 **from** | **Integer**| количество событий, которые нужно пропустить для формирования текущего набора | [optional] [default to 0]
 **size** | **Integer**| количество событий в наборе | [optional] [default to 10]

### Return type

[**List&lt;EventShortDto&gt;**](EventShortDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

