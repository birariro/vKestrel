package com.birariro.vkestrel.adapter;

import com.birariro.vkestrel.service.parser.ParserService;
import com.birariro.vkestrel.adapter.persistence.library.ScriptType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VelogPerserTest {


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
