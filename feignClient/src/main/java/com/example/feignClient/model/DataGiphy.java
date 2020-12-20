package com.example.feignClient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "id",
    "url",
    "slug",
    "bitly_gif_url",
    "bitly_url",
    "embed_url",
    "username",
    "source",
    "title",
    "rating",
    "content_url",
    "source_tld",
    "source_post_url",
    "is_sticker",
    "import_datetime",
    "trending_datetime",
//    "images",
    "user",
    "image_original_url",
    "image_url",
    "image_mp4_url",
    "image_frames",
    "image_width",
    "image_height",
    "fixed_height_downsampled_url",
    "fixed_height_downsampled_width",
    "fixed_height_downsampled_height",
    "fixed_width_downsampled_url",
    "fixed_width_downsampled_width",
    "fixed_width_downsampled_height",
    "fixed_height_small_url",
    "fixed_height_small_still_url",
    "fixed_height_small_width",
    "fixed_height_small_height",
    "fixed_width_small_url",
    "fixed_width_small_still_url",
    "fixed_width_small_width",
    "fixed_width_small_height",
    "caption"
})
public class DataGiphy {

  @JsonProperty("type")
  private String type;
  @JsonProperty("id")
  private String id;
  @JsonProperty("url")
  private String url;
  @JsonProperty("slug")
  private String slug;
  @JsonProperty("bitly_gif_url")
  private String bitlyGifUrl;
  @JsonProperty("bitly_url")
  private String bitlyUrl;
  @JsonProperty("embed_url")
  private String embedUrl;
  @JsonProperty("username")
  private String username;
  @JsonProperty("source")
  private String source;
  @JsonProperty("title")
  private String title;
  @JsonProperty("rating")
  private String rating;
  @JsonProperty("content_url")
  private String contentUrl;
  @JsonProperty("source_tld")
  private String sourceTld;
  @JsonProperty("source_post_url")
  private String sourcePostUrl;
  @JsonProperty("is_sticker")
  private Integer isSticker;
  @JsonProperty("import_datetime")
  private String importDatetime;
  @JsonProperty("trending_datetime")
  private String trendingDatetime;
//    @JsonProperty("images")
//  private String images;
  @JsonProperty("user")
  private UserGiphy user;
  @JsonProperty("image_original_url")
  private String imageOriginalUrl;
  @JsonProperty("image_url")
  private String imageUrl;
  @JsonProperty("image_mp4_url")
  private String imageMp4Url;
  @JsonProperty("image_frames")
  private String imageFrames;
  @JsonProperty("image_width")
  private String imageWidth;
  @JsonProperty("image_height")
  private String imageHeight;
  @JsonProperty("fixed_height_downsampled_url")
  private String fixedHeightDownsampledUrl;
  @JsonProperty("fixed_height_downsampled_width")
  private String fixedHeightDownsampledWidth;
  @JsonProperty("fixed_height_downsampled_height")
  private String fixedHeightDownsampledHeight;
  @JsonProperty("fixed_width_downsampled_url")
  private String fixedWidthDownsampledUrl;
  @JsonProperty("fixed_width_downsampled_width")
  private String fixedWidthDownsampledWidth;
  @JsonProperty("fixed_width_downsampled_height")
  private String fixedWidthDownsampledHeight;
  @JsonProperty("fixed_height_small_url")
  private String fixedHeightSmallUrl;
  @JsonProperty("fixed_height_small_still_url")
  private String fixedHeightSmallStillUrl;
  @JsonProperty("fixed_height_small_width")
  private String fixedHeightSmallWidth;
  @JsonProperty("fixed_height_small_height")
  private String fixedHeightSmallHeight;
  @JsonProperty("fixed_width_small_url")
  private String fixedWidthSmallUrl;
  @JsonProperty("fixed_width_small_still_url")
  private String fixedWidthSmallStillUrl;
  @JsonProperty("fixed_width_small_width")
  private String fixedWidthSmallWidth;
  @JsonProperty("fixed_width_small_height")
  private String fixedWidthSmallHeight;
  @JsonProperty("caption")
  private String caption;
//  @JsonIgnore
//  private Map<String, Object> additionalProperties = new HashMap<>();

  //  @JsonProperty("images")
//  public Images getImages() {
//    return images;
//  }
//
//  @JsonProperty("images")
//  public void setImages(Images images) {
//    this.images = images;
//  }

//  @JsonAnySetter
//  public void setAdditionalProperty(String name, Object value) {
//    this.additionalProperties.put(name, value);
//  }

}
