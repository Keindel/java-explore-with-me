package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Пользователь
 */
@Schema(description = "Пользователь")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class UserDto   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  public UserDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Почтовый адрес
   * @return email
   **/
  @Schema(example = "petrov.i@practicummail.ru", required = true, description = "Почтовый адрес")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор
   * @return id
   **/
  @Schema(example = "1", accessMode = Schema.AccessMode.READ_ONLY, description = "Идентификатор")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Имя
   * @return name
   **/
  @Schema(example = "Петров Иван", required = true, description = "Имя")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDto userDto = (UserDto) o;
    return Objects.equals(this.email, userDto.email) &&
        Objects.equals(this.id, userDto.id) &&
        Objects.equals(this.name, userDto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDto {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
