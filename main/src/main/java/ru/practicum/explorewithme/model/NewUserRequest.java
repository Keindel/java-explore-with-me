package ru.practicum.explorewithme.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Данные нового пользователя
 */
@Schema(description = "Данные нового пользователя")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class NewUserRequest   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("name")
  private String name = null;

  public NewUserRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Почтовый адрес
   * @return email
   **/
  @Schema(example = "ivan.petrov@practicummail.ru", required = true, description = "Почтовый адрес")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public NewUserRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Имя
   * @return name
   **/
  @Schema(example = "Иван Петров", required = true, description = "Имя")
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
    NewUserRequest newUserRequest = (NewUserRequest) o;
    return Objects.equals(this.email, newUserRequest.email) &&
        Objects.equals(this.name, newUserRequest.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewUserRequest {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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
