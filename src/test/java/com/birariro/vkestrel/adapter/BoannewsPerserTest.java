package com.birariro.vkestrel.adapter;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.birariro.vkestrel.service.parser.ParserService;
import com.birariro.vkestrel.service.parser.SSL;
import com.birariro.vkestrel.adapter.persistence.library.ScriptType;

@SpringBootTest
public class BoannewsPerserTest {
    @Autowired
    ParserService parserService;


    @Test
    public void jsoupTest() throws IOException, NoSuchAlgorithmException, KeyManagementException {

        String baseUrl = "https://www.boannews.com";
        SSL.setSSL();
        Connection connect = Jsoup.connect(baseUrl+"/media/s_list.asp?skind=5");
        Document document = connect.get();
        //class 이름으로 얻기
        Elements newsPosts = document.select(".news_list");

        for (Element newsPost : newsPosts) {

            String title = newsPost.select(".news_txt").stream().findFirst().get().text();
            System.out.println("title = " + title);

            //테그로 찾기
            Element linkTag = newsPost.select("a").stream().findFirst().get();
            //속성 찾기
            String link = linkTag.attr("href");
            System.out.println("link = " + baseUrl+link);

        }
    }

    @Test
    public void testtest(){
        parserService.getDocuments("TestName", "https://www.boannews.com/media/s_list.asp?skind=5",ScriptType.BOANNEWS);
    }



}
