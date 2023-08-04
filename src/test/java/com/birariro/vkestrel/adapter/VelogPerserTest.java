package com.birariro.vkestrel.adapter;

import com.birariro.vkestrel.service.parser.dto.TrendingPost;
import com.birariro.vkestrel.service.parser.dto.VelogGraphQLResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class VelogPerserTest {

    @Test
    public void graphqlCallTest(){
        // WebClient를 생성합니다.
        WebClient.Builder webClientBuilder = WebClient.builder();

        // GraphQL API의 URL
        String apiUrl = "https://v2cdn.velog.io/graphql";

        // GraphQL 쿼리
        String query = "query TrendingPosts($limit: Int, $offset: Int, $timeframe: String) {  trendingPosts(limit: $limit, offset: $offset, timeframe: $timeframe) {    id    title    short_description    thumbnail    likes    user {      id      username      profile {        id        thumbnail        __typename      }      __typename    }    url_slug    released_at    updated_at    comments_count    tags    is_private    __typename  }}";

        // GraphQL Variables
        String variables = "{ \"limit\": 2, \"timeframe\": \"month\" }";

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
            return;
        }
        List<TrendingPost> trendingPosts = trendingPostsResponse.getData().getTrendingPosts();
        for (TrendingPost post : trendingPosts) {
            System.out.println("Post Title: " + post.getTitle());
            String userName = post.getUser().getUsername();
            String url_slug = post.getUrl_slug();
            String uri = String.format("https://velog.io/@%s/%s", userName, url_slug);
            System.out.println("Post URL: " +uri);
        }


    }


    @Test
    public void jsoupTest() throws IOException {

        Connection connect = Jsoup.connect("https://velog.io/");
        Document document = connect.get();
        //class name 로 찾기

        //Elements select = document.getElementsByClass("sc-jgrJph dYjA-dM");

        Elements select = document.select("div.sc-lbhJGD a.sc-jgrJph");


        for (Element element : select) {

            String href = element.attr("abs:href");

            Optional<Element> title = element.select("h4").stream().findFirst();
            System.out.println("title = " + title.get().text());
            System.out.println("href = " + href);
            System.out.println("author = ");

        }

    }
    @Test
    public void jsoupTest2() throws IOException {
        Connection connect = Jsoup.connect("https://velog.io/");
        Document document = connect.get();
        //class name 로 찾기

        //Elements select = document.getElementsByClass("sc-jgrJph dYjA-dM");

        Elements select = document.select("div.sc-iNGGcK a.sc-gSQFLo");

        for (Element element : select) {

            //href 제거
            String attr = element.attr("abs:href");
            System.out.println("attr = " + attr);

            //테그로 찾기
            Optional<Element> h4 = element.select("h4").stream().findFirst();
            System.out.println("h4 = " + h4.get().text());

        }
    }


}
