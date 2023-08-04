package com.birariro.vkestrel.adapter;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.birariro.vkestrel.service.parser.ParserService;


public class GeekNewsPerserTest {


    @Test
    public void jsoupTest() throws IOException {
        Connection connect = Jsoup.connect("https://news.hada.io/");
        Document document = connect.get();

        Elements select = document.select("div.topic_row");

        for (Element element : select) {
            Element titleElement = element.select("div.topictitle a").stream().findFirst().get();
            String title = titleElement.select("h1").stream().findFirst().get().text();
            System.out.println("title = " + title);
            String url = titleElement.attr("abs:href");
            System.out.println("url = " + url);

            String likeElement = element.select("div.topicinfo a.u").stream().findFirst().get().text();
            int likeCount = 0;
            if(! likeElement.contains("토론")){
                likeCount = Integer.parseInt(likeElement.replace("댓글", "").replace("개", "").trim());
            }
            System.out.println("likeElement = " +likeCount);
        }

    }



}



