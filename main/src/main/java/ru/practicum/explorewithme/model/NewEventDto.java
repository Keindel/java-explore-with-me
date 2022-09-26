package ru.practicum.explorewithme.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Новое событие
 */
@Schema(description = "Новое событие")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class NewEventDto   {
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
  private Boolean paid = false;

  @JsonProperty("participantLimit")
  private Integer participantLimit = 0;

  @JsonProperty("requestModeration")
  private Boolean requestModeration = true;

  @JsonProperty("title")
  private String title = null;

  public NewEventDto annotation(String annotation) {
    this.annotation = annotation;
    return this;
  }

  /**
   * Краткое описание события
   * @return annotation
   **/
  @Schema(example = "Сплав на байдарках похож на полет.", required = true, description = "Краткое описание события")
      @NotNull

  @Size(min=20,max=2000)   public String getAnnotation() {
    return annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }

  public NewEventDto category(Long category) {
    this.category = category;
    return this;
  }

  /**
   * id категории к которой относится событие
   * @return category
   **/
  @Schema(example = "2", required = true, description = "id категории к которой относится событие")
      @NotNull

    public Long getCategory() {
    return category;
  }

  public void setCategory(Long category) {
    this.category = category;
  }

  public NewEventDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Полное описание события
   * @return description
   **/
  @Schema(example = "Сплав на байдарках похож на полет. На спокойной воде — это парение. На бурной, порожистой — выполнение фигур высшего пилотажа. И то, и другое дарят чувство обновления, феерические эмоции, яркие впечатления.", required = true, description = "Полное описание события")
      @NotNull

  @Size(min=20,max=7000)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NewEventDto eventDate(String eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  /**
   * Дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"
   * @return eventDate
   **/
  @Schema(example = "2024-12-31 15:10:05", required = true, description = "Дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"")
      @NotNull

    public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public NewEventDto location(Location location) {
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

  public NewEventDto paid(Boolean paid) {
    this.paid = paid;
    return this;
  }

  /**
   * Нужно ли оплачивать участие в событии
   * @return paid
   **/
  @Schema(example = "true", description = "Нужно ли оплачивать участие в событии")
  
    public Boolean isPaid() {
    return paid;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public NewEventDto participantLimit(Integer participantLimit) {
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

  public NewEventDto requestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
    return this;
  }

  /**
   * Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.
   * @return requestModeration
   **/
  @Schema(example = "false", description = "Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.")
  
    public Boolean isRequestModeration() {
    return requestModeration;
  }

  public void setRequestModeration(Boolean requestModeration) {
    this.requestModeration = requestModeration;
  }

  public NewEventDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Заголовок события
   * @return title
   **/
  @Schema(example = "Сплав на байдарках", required = true, description = "Заголовок события")
      @NotNull

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
    NewEventDto newEventDto = (NewEventDto) o;
    return Objects.equals(this.annotation, newEventDto.annotation) &&
        Objects.equals(this.category, newEventDto.category) &&
        Objects.equals(this.description, newEventDto.description) &&
        Objects.equals(this.eventDate, newEventDto.eventDate) &&
        Objects.equals(this.location, newEventDto.location) &&
        Objects.equals(this.paid, newEventDto.paid) &&
        Objects.equals(this.participantLimit, newEventDto.participantLimit) &&
        Objects.equals(this.requestModeration, newEventDto.requestModeration) &&
        Objects.equals(this.title, newEventDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, description, eventDate, location, paid, participantLimit, requestModeration, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewEventDto {\n");
    
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
