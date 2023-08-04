package com.birariro.vkestrel.service.parser;

import com.birariro.vkestrel.adapter.persistence.library.Document;
import com.birariro.vkestrel.service.parser.dto.TrendingPost;
import com.birariro.vkestrel.service.parser.dto.VelogGraphQLResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
class VelogParser {

    @Value("${setting.parser.max-size:10}")
    private int maxSize;

    public List<Document> getDocument(String url)  {


        List<Document> collect = new ArrayList<>();

        // WebClient를 생성합니다.
        WebClient.Builder webClientBuilder = WebClient.builder();

        // GraphQL API의 URL
        String apiUrl = "https://v2cdn.velog.io/graphql";

        // GraphQL 쿼리
        String query = "query TrendingPosts($limit: Int, $offset: Int, $timeframe: String) {  trendingPosts(limit: $limit, offset: $offset, timeframe: $timeframe) {    id    title    short_description    thumbnail    likes    user {      id      username      profile {        id        thumbnail        __typename      }      __typename    }    url_slug    released_at    updated_at    comments_count    tags    is_private    __typename  }}";

        // GraphQL Variables
        String variables = "{ \"limit\": 10, \"timeframe\": \"month\" }";

        String responseJson = webClientBuilder
            .baseUrl(apiUrl)
            .build()
            .post()
            .header("Content-Type", "application/json")
            .bodyValue("{\"query\":\"" + query + "\",\"variables\":" + variables + "}")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        // JSON을 자바 객체로 파싱합니다.
        ObjectMapper objectMapper = new ObjectMapper();
        VelogGraphQLResponse trendingPostsResponse = null;
        try {
            trendingPostsResponse = objectMapper.readValue(responseJson, VelogGraphQLResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 파싱된 자바 객체에서 원하는 데이터를 추출하여 사용합니다.
        if (trendingPostsResponse == null) {
            return collect;
        }

        List<TrendingPost> trendingPosts = trendingPostsResponse.getData().getTrendingPosts();

        for (TrendingPost post : trendingPosts) {

            String title = post.getTitle();
            String userName = post.getUser().getUsername();
            String url_slug = post.getUrl_slug();
            String uri = String.format("https://velog.io/@%s/%s", userName, url_slug);


            Document _document = new Document(title, uri, userName);
            collect.add(_document);

            if(collect.size() >= maxSize) break;
        }

        return collect;
    }

}
