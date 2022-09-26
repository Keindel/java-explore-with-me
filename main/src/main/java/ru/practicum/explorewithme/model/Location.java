package ru.practicum.explorewithme.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Широта и долгота места проведения события
 */
@Schema(description = "Широта и долгота места проведения события")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")


public class Location   {
  @JsonProperty("lat")
  private Float lat = null;

  @JsonProperty("lon")
  private Float lon = null;

  public Location lat(Float lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Широта
   * @return lat
   **/
  @Schema(example = "55.754167", description = "Широта")
  
    public Float getLat() {
    return lat;
  }

  public void setLat(Float lat) {
    this.lat = lat;
  }

  public Location lon(Float lon) {
    this.lon = lon;
    return this;
  }

  /**
   * Долгота
   * @return lon
   **/
  @Schema(example = "37.62", description = "Долгота")
  
    public Float getLon() {
    return lon;
  }

  public void setLon(Float lon) {
    this.lon = lon;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(this.lat, location.lat) &&
        Objects.equals(this.lon, location.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lat, lon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
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
