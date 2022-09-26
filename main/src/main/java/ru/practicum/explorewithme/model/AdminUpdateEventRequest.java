package ru.practicum.explorewithme.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Информация для редактирования события администратором. Все поля необязательные. Значение полей не валидируется.
 */
@Schema(description = "Информация для редактирования события администратором. Все поля необязательные. Значение полей не валидируется.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class AdminUpdateEventRequest   {
  @JsonProperty("annotation")
  private String annotation = null;

  @JsonProperty("category")
  private Long category = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("eventDate")
  private String eventDate = null;

  @JsonProperty("location")
  private Location location = null;

  @JsonProperty("paid")
  private Boolean paid = null;

  @JsonProperty("participantLimit")
  private Integer participantLimit = null;

  @JsonProperty("requestModeration")
  private Boolean requestModeration = null;

  @JsonProperty("title")
  private String title = null;

  public AdminUpdateEventRequest annotation(String annotation) {
    this.annotation = annotation;
    return this;
  }

  /**
   * Краткое описание события
   * @return annotation
   **/
  @Schema(example = "Новое краткое описание", description = "Краткое описание события")
  
    public String getAnnotation() {
    return annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }

  public AdminUpdateEventRequest category(Long category) {
    this.category = category;
    return this;
  }

  /**
   * id категории к которой относится событие
   * @return category
   **/
  @Schema(description = "id категории к которой относится событие")
  
    public Long getCategory() {
    return category;
  }

  public void setCategory(Long category) {
    this.category = category;
  }

  public AdminUpdateEventRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Полное описание события
   * @return description
   **/
  @Schema(example = "Новое полное описание", description = "Полное описание события")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AdminUpdateEventRequest eventDate(String eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  /**
   * Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")
   * @return eventDate
   **/
  @Schema(example = "2025-01-01 09:08:07", description = "Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")")
  
    public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public AdminUpdateEventRequest location(Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   **/
  @Schema(description = "")
  
    @Valid
    public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public AdminUpdateEventRequest paid(Boolean paid) {
    this.paid = paid;
    return this;
  }

  /**
   * Нужно ли оплачивать участие в событии
   * @return paid
   **/
  @Schema(example = "false", description = "Нужно ли оплачивать участие в событии")
  
    public Boolean isPaid() {
    return paid;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public AdminUpdateEventRequest participantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
    return this;
  }

  /**
   * Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
   * @return participantLimit
   **/
  @Schema(example = "33", description = "Ограничение на количество участников. Значение 0 - означает отсутствие ограничения")
  
    public Integer getParticipantLimit() {
    return participantLimit;
  }

  public void setParticipantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
  }

  public AdminUpdateEventRequest requestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
    return this;
  }

  /**
   * Нужна ли пре-модерация заявок на участие
   * @return requestModeration
   **/
  @Schema(example = "false", description = "Нужна ли пре-модерация заявок на участие")
  
    public Boolean isRequestModeration() {
    return requestModeration;
  }

  public void setRequestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
  }

  public AdminUpdateEventRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Заголовок события
   * @return title
   **/
  @Schema(example = "Новый заголовок", description = "Заголовок события")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminUpdateEventRequest adminUpdateEventRequest = (AdminUpdateEventRequest) o;
    return Objects.equals(this.annotation, adminUpdateEventRequest.annotation) &&
        Objects.equals(this.category, adminUpdateEventRequest.category) &&
        Objects.equals(this.description, adminUpdateEventRequest.description) &&
        Objects.equals(this.eventDate, adminUpdateEventRequest.eventDate) &&
        Objects.equals(this.location, adminUpdateEventRequest.location) &&
        Objects.equals(this.paid, adminUpdateEventRequest.paid) &&
        Objects.equals(this.participantLimit, adminUpdateEventRequest.participantLimit) &&
        Objects.equals(this.requestModeration, adminUpdateEventRequest.requestModeration) &&
        Objects.equals(this.title, adminUpdateEventRequest.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, description, eventDate, location, paid, participantLimit, requestModeration, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminUpdateEventRequest {\n");
    
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
    sb.append("    participantLimit: ").append(toIndentedString(participantLimit)).append("\n");
    sb.append("    requestModeration: ").append(toIndentedString(requestModeration)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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