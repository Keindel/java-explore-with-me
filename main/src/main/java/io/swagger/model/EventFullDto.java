package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.CategoryDto;
import io.swagger.model.Location;
import io.swagger.model.UserShortDto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventFullDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class EventFullDto   {
  @JsonProperty("annotation")
  private String annotation = null;

  @JsonProperty("category")
  private CategoryDto category = null;

  @JsonProperty("confirmedRequests")
  private Long confirmedRequests = null;

  @JsonProperty("createdOn")
  private String createdOn = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("eventDate")
  private String eventDate = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("initiator")
  private UserShortDto initiator = null;

  @JsonProperty("location")
  private Location location = null;

  @JsonProperty("paid")
  private Boolean paid = null;

  @JsonProperty("participantLimit")
  private Integer participantLimit = 0;

  @JsonProperty("publishedOn")
  private String publishedOn = null;

  @JsonProperty("requestModeration")
  private Boolean requestModeration = true;

  /**
   * Список состояний жизненного цикла события
   */
  public enum StateEnum {
    PENDING("PENDING"),
    
    PUBLISHED("PUBLISHED"),
    
    CANCELED("CANCELED");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("state")
  private StateEnum state = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("views")
  private Long views = null;

  public EventFullDto annotation(String annotation) {
    this.annotation = annotation;
    return this;
  }

  /**
   * Краткое описание
   * @return annotation
   **/
  @Schema(example = "Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории", required = true, description = "Краткое описание")
      @NotNull

    public String getAnnotation() {
    return annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }

  public EventFullDto category(CategoryDto category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CategoryDto getCategory() {
    return category;
  }

  public void setCategory(CategoryDto category) {
    this.category = category;
  }

  public EventFullDto confirmedRequests(Long confirmedRequests) {
    this.confirmedRequests = confirmedRequests;
    return this;
  }

  /**
   * Количество одобренных заявок на участие в данном событии
   * @return confirmedRequests
   **/
  @Schema(example = "5", description = "Количество одобренных заявок на участие в данном событии")
  
    public Long getConfirmedRequests() {
    return confirmedRequests;
  }

  public void setConfirmedRequests(Long confirmedRequests) {
    this.confirmedRequests = confirmedRequests;
  }

  public EventFullDto createdOn(String createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * Дата и время создания события (в формате \"yyyy-MM-dd HH:mm:ss\")
   * @return createdOn
   **/
  @Schema(example = "2022-09-06 11:00:23", description = "Дата и время создания события (в формате \"yyyy-MM-dd HH:mm:ss\")")
  
    public String getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(String createdOn) {
    this.createdOn = createdOn;
  }

  public EventFullDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Полное описание события
   * @return description
   **/
  @Schema(example = "Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...", description = "Полное описание события")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EventFullDto eventDate(String eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  /**
   * Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")
   * @return eventDate
   **/
  @Schema(example = "2024-12-31 15:10:05", required = true, description = "Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")")
      @NotNull

    public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public EventFullDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор
   * @return id
   **/
  @Schema(example = "1", description = "Идентификатор")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EventFullDto initiator(UserShortDto initiator) {
    this.initiator = initiator;
    return this;
  }

  /**
   * Get initiator
   * @return initiator
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UserShortDto getInitiator() {
    return initiator;
  }

  public void setInitiator(UserShortDto initiator) {
    this.initiator = initiator;
  }

  public EventFullDto location(Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public EventFullDto paid(Boolean paid) {
    this.paid = paid;
    return this;
  }

  /**
   * Нужно ли оплачивать участие
   * @return paid
   **/
  @Schema(example = "true", required = true, description = "Нужно ли оплачивать участие")
      @NotNull

    public Boolean isPaid() {
    return paid;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public EventFullDto participantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
    return this;
  }

  /**
   * Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
   * @return participantLimit
   **/
  @Schema(example = "10", description = "Ограничение на количество участников. Значение 0 - означает отсутствие ограничения")
  
    public Integer getParticipantLimit() {
    return participantLimit;
  }

  public void setParticipantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
  }

  public EventFullDto publishedOn(String publishedOn) {
    this.publishedOn = publishedOn;
    return this;
  }

  /**
   * Дата и время публикации события (в формате \"yyyy-MM-dd HH:mm:ss\")
   * @return publishedOn
   **/
  @Schema(example = "2022-09-06 15:10:05", description = "Дата и время публикации события (в формате \"yyyy-MM-dd HH:mm:ss\")")
  
    public String getPublishedOn() {
    return publishedOn;
  }

  public void setPublishedOn(String publishedOn) {
    this.publishedOn = publishedOn;
  }

  public EventFullDto requestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
    return this;
  }

  /**
   * Нужна ли пре-модерация заявок на участие
   * @return requestModeration
   **/
  @Schema(example = "true", description = "Нужна ли пре-модерация заявок на участие")
  
    public Boolean isRequestModeration() {
    return requestModeration;
  }

  public void setRequestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
  }

  public EventFullDto state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * Список состояний жизненного цикла события
   * @return state
   **/
  @Schema(example = "PUBLISHED", description = "Список состояний жизненного цикла события")
  
    public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public EventFullDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Заголовок
   * @return title
   **/
  @Schema(example = "Знаменитое шоу 'Летающая кукуруза'", required = true, description = "Заголовок")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public EventFullDto views(Long views) {
    this.views = views;
    return this;
  }

  /**
   * Количество просмотрев события
   * @return views
   **/
  @Schema(example = "999", description = "Количество просмотрев события")
  
    public Long getViews() {
    return views;
  }

  public void setViews(Long views) {
    this.views = views;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventFullDto eventFullDto = (EventFullDto) o;
    return Objects.equals(this.annotation, eventFullDto.annotation) &&
        Objects.equals(this.category, eventFullDto.category) &&
        Objects.equals(this.confirmedRequests, eventFullDto.confirmedRequests) &&
        Objects.equals(this.createdOn, eventFullDto.createdOn) &&
        Objects.equals(this.description, eventFullDto.description) &&
        Objects.equals(this.eventDate, eventFullDto.eventDate) &&
        Objects.equals(this.id, eventFullDto.id) &&
        Objects.equals(this.initiator, eventFullDto.initiator) &&
        Objects.equals(this.location, eventFullDto.location) &&
        Objects.equals(this.paid, eventFullDto.paid) &&
        Objects.equals(this.participantLimit, eventFullDto.participantLimit) &&
        Objects.equals(this.publishedOn, eventFullDto.publishedOn) &&
        Objects.equals(this.requestModeration, eventFullDto.requestModeration) &&
        Objects.equals(this.state, eventFullDto.state) &&
        Objects.equals(this.title, eventFullDto.title) &&
        Objects.equals(this.views, eventFullDto.views);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, confirmedRequests, createdOn, description, eventDate, id, initiator, location, paid, participantLimit, publishedOn, requestModeration, state, title, views);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventFullDto {\n");
    
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    confirmedRequests: ").append(toIndentedString(confirmedRequests)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    initiator: ").append(toIndentedString(initiator)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
    sb.append("    participantLimit: ").append(toIndentedString(participantLimit)).append("\n");
    sb.append("    publishedOn: ").append(toIndentedString(publishedOn)).append("\n");
    sb.append("    requestModeration: ").append(toIndentedString(requestModeration)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    views: ").append(toIndentedString(views)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
