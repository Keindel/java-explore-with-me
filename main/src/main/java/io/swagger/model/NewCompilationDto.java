package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Подборка событий
 */
@Schema(description = "Подборка событий")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class NewCompilationDto   {
  @JsonProperty("events")
  @Valid
  private List<Long> events = null;

  @JsonProperty("pinned")
  private Boolean pinned = false;

  @JsonProperty("title")
  private String title = null;

  public NewCompilationDto events(List<Long> events) {
    this.events = events;
    return this;
  }

  public NewCompilationDto addEventsItem(Long eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<Long>();
    }
    this.events.add(eventsItem);
    return this;
  }

  /**
   * Список идентификаторов событий входящих в подборку
   * @return events
   **/
  @Schema(example = "[1,2,3]", description = "Список идентификаторов событий входящих в подборку")
  
    public List<Long> getEvents() {
    return events;
  }

  public void setEvents(List<Long> events) {
    this.events = events;
  }

  public NewCompilationDto pinned(Boolean pinned) {
    this.pinned = pinned;
    return this;
  }

  /**
   * Закреплена ли подборка на главной странице сайта
   * @return pinned
   **/
  @Schema(example = "false", description = "Закреплена ли подборка на главной странице сайта")
  
    public Boolean isPinned() {
    return pinned;
  }

  public void setPinned(Boolean pinned) {
    this.pinned = pinned;
  }

  public NewCompilationDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Заголовок подборки
   * @return title
   **/
  @Schema(example = "Летние концерты", required = true, description = "Заголовок подборки")
      @NotNull

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
    NewCompilationDto newCompilationDto = (NewCompilationDto) o;
    return Objects.equals(this.events, newCompilationDto.events) &&
        Objects.equals(this.pinned, newCompilationDto.pinned) &&
        Objects.equals(this.title, newCompilationDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(events, pinned, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewCompilationDto {\n");
    
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("    pinned: ").append(toIndentedString(pinned)).append("\n");
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
