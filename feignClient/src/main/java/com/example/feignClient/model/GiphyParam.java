package com.example.feignClient.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class GiphyParam {
  @Value("${paramImg.apiKey}")
  String apiKey;
}
