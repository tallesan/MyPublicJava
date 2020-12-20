
package com.example.feignClient.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "avatar_url",
    "banner_image",
    "banner_url",
    "profile_url",
    "username",
    "display_name",
    "description",
    "is_verified",
    "website_url",
    "instagram_url"
})
public class UserGiphy {

  @JsonProperty("avatar_url")
  private String avatarUrl;
  @JsonProperty("banner_image")
  private String bannerImage;
  @JsonProperty("banner_url")
  private String bannerUrl;
  @JsonProperty("profile_url")
  private String profileUrl;
  @JsonProperty("username")
  private String username;
  @JsonProperty("display_name")
  private String displayName;
  @JsonProperty("description")
  private String description;
  @JsonProperty("is_verified")
  private Boolean isVerified;
  @JsonProperty("website_url")
  private String websiteUrl;
  @JsonProperty("instagram_url")
  private String instagramUrl;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
