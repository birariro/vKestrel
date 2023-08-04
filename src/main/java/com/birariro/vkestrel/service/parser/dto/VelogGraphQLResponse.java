package com.birariro.vkestrel.service.parser.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VelogGraphQLResponse {

  private Data data;

  @Getter
  @Setter
  public static class Data {
    private List<TrendingPost> trendingPosts;

  }

}
