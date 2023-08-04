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
@JsonIgnoreProperties(ignoreUnknown = true) // Apply the annotation directly to the class
public class TrendingPost {
  private String id;
  private String title;
  private String short_description;
  private String thumbnail;
  private int likes;
  private com.birariro.vkestrel.service.parser.dto.TrendingPost.User user;
  private String url_slug;
  private String released_at;
  private String updated_at;
  private int comments_count;
  private List<String> tags;
  private boolean is_private;

  // 생성자, getter, setter 등 필요한 메서드를 추가합니다.
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class User {
    private String id;
    private String username;
    private UserProfile profile;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserProfile {
      private String id;
      private String thumbnail;

    }
  }
}