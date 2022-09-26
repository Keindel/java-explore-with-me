package ru.practicum.explorewithme.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Краткая информация о событии
 */
@Schema(description = "Краткая информация о событии")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class EventShortDto   {
  @JsonProperty("annotation")
  private String annotation = null;

  @JsonProperty("category")
  private CategoryDto category = null;

  @JsonProperty("confirmedRequests")
  private Long confirmedRequests = null;

  @JsonProperty("eventDate")
  private String eventDate = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("initiator")
  private UserShortDto initiator = null;

  @JsonProperty("paid")
  private Boolean paid = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("views")
  private Long views = null;

  public EventShortDto annotation(String annotation) {
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

  public EventShortDto category(CategoryDto category) {
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

  public EventShortDto confirmedRequests(Long confirmedRequests) {
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

  public EventShortDto eventDate(String eventDate) {
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

  public EventShortDto id(Long id) {
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

  public EventShortDto initiator(UserShortDto initiator) {
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

  public EventShortDto paid(Boolean paid) {
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

  public EventShortDto title(String title) {
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

  public EventShortDto views(Long views) {
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
    EventShortDto eventShortDto = (EventShortDto) o;
    return Objects.equals(this.annotation, eventShortDto.annotation) &&
        Objects.equals(this.category, eventShortDto.category) &&
        Objects.equals(this.confirmedRequests, eventShortDto.confirmedRequests) &&
        Objects.equals(this.eventDate, eventShortDto.eventDate) &&
        Objects.equals(this.id, eventShortDto.id) &&
        Objects.equals(this.initiator, eventShortDto.initiator) &&
        Objects.equals(this.paid, eventShortDto.paid) &&
        Objects.equals(this.title, eventShortDto.title) &&
        Objects.equals(this.views, eventShortDto.views);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation, category, confirmedRequests, eventDate, id, initiator, paid, title, views);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventShortDto {\n");
    
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    confirmedRequests: ").append(toIndentedString(confirmedRequests)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    initiator: ").append(toIndentedString(initiator)).append("\n");
    sb.append("    paid: ").append(toIndentedString(paid)).append("\n");
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
