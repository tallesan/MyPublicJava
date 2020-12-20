package com.example.feignClient.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "disclaimer",
    "license",
    "timestamp",
    "base",
    "rates"
})
public class UserResponse {

  @JsonProperty("disclaimer")
  private String disclaimer;
  @JsonProperty("license")
  private String license;
  @JsonProperty("timestamp")
  private Integer timestamp;
  @JsonProperty("base")
  private String base;
  @JsonProperty("rates")
  private Map<String, Double> rates;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
