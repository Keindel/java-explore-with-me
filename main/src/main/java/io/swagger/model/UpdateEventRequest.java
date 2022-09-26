package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Данные для изменения информации о событии
 */
@Schema(description = "Данные для изменения информации о событии")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class UpdateEventRequest   {
  @JsonProperty("annotation")
  private String annotation = null;

  @JsonProperty("category")
  private Long category = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("eventDate")
  private String eventDate = null;

  @JsonProperty("eventId")
  private Long eventId = null;

  @JsonProperty("paid")
  private Boolean paid = null;

  @JsonProperty("participantLimit")
  private Integer participantLimit = null;

  @JsonProperty("title")
  private String title = null;

  public UpdateEventRequest annotation(String annotation) {
    this.annotation = annotation;
    return this;
  }

  /**
   * Новая аннотация
   * @return annotation
   **/
  @Schema(example = "Сап прогулки по рекам и каналам – это возможность увидеть Практикбург с другого ракурса", description = "Новая аннотация")
  
  @Size(min=20,max=2000)   public String getAnnotation() {
    return annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }

  public UpdateEventRequest category(Long category) {
    this.category = category;
    return this;
  }

  /**
   * Новая категория
   * @return category
   **/
  @Schema(example = "3", description = "Новая категория")
  
    public Long getCategory() {
    return category;
  }

  public void setCategory(Long category) {
    this.category = category;
  }

  public UpdateEventRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Новое описание
   * @return description
   **/
  @Schema(example = "От английского SUP - Stand Up Paddle — \"стоя на доске с веслом\", гавайская разновидность сёрфинга, в котором серфер, стоя на доске, катается на волнах и при этом гребет веслом, а не руками, как в классическом серфинге.", description = "Новое описание")
  
  @Size(min=20,max=7000)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UpdateEventRequest eventDate(String eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  /**
   * Новые дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"
   * @return eventDate
   **/
  @Schema(example = "2023-10-11 23:10:05", description = "Новые дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"")
  
    public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public UpdateEventRequest eventId(Long eventId) {
    this.eventId = eventId;
    return this;
  }

  /**
   * Идентификатор события
   * @return eventId
   **/
  @Schema(example = "1", required = true, description = "Идентификатор события")
      @NotNull

    public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  public UpdateEventRequest paid(Boolean paid) {
    this.paid = paid;
    return this;
  }

  /**
   * Новое значение флага о платности мероприятия
   * @return paid
   **/
  @Schema(example = "true", description = "Новое значение флага о платности мероприятия")
  
    public Boolean isPaid() {
    return paid;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public UpdateEventRequest participantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
    return this;
  }

  /**
   * Новый лимит пользователей
   * @return participantLimit
   **/
  @Schema(example = "7", description = "Новый лимит пользователей")
  
    public Integer getParticipantLimit() {
    return participantLimit;
  }

  public void setParticipantLimit(Integer participantLimit) {
    this.participantLimit = participantLimit;
  }

  public UpdateEventRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Новый заголовок
   * @return title
   **/
  @Schema(example = "Сап прогулки по рекам и каналам", description = "Новый заголовок")
  
  @Size(min=3,max=120)   public String getTitle() {
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
    UpdateEventRequest updateEventRequest = (UpdateEventRequest) o;
    return Objects.equals(this.annotation, updateEventRequest.annotation) &&
        Objects.equals(this.category, updateEventRequest.category) &&
        Objects.equals(this.description, updateEventRequest.description) &&
        Objects.equals(this.eventDate, updateEventRequest.eventDate) &&
        Objects.equals(this.eventId, updateEventRequest.eventId) &&
        Objects.equals(this.paid, updateEventRequest.paid) &&
        Objects.equals(this.participantLimit, updateEventRequest.participantLimit) &&
        Objects.equals(this.title, updateEventRequest.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, description, eventDate, eventId, paid, participantLimit, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateEventRequest {\n");
    
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
    sb.append("    participantLimit: ").append(toIndentedString(participantLimit)).append("\n");
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
