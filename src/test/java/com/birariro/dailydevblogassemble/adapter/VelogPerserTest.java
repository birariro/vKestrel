package com.birariro.dailydevblogassemble.adapter;

import com.birariro.dailydevblogassemble.adapter.parser.VelogParser;
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

@SpringBootTest
public class VelogPerserTest {
    @Autowired
    VelogParser velogParser;
    @Test
    public void velogHtml(){
        WebClient webClient = WebClient.create("https://velog.io/");
        String block = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String[] split = block.split("\"sc-lbhJGD jhNfXV\"><a class=\"sc-jgrJph dYjA-dM\"");


        List<String> collect = Arrays.stream(split).filter(item -> item.startsWith(" href=")).collect(Collectors.toList());


        List<String> collect1 = collect.stream().map(item -> item.substring(0, item.indexOf("</h4>"))).collect(Collectors.toList());

        collect1.forEach(item ->  {
            System.out.println("item = " + item);
        });
    }

    @Test
    public void jsoupTest() throws IOException {
        Connection connect = Jsoup.connect("https://velog.io/");
        Document document = connect.get();
        //class name 로 찾기

        //Elements select = document.getElementsByClass("sc-jgrJph dYjA-dM");

        Elements select = document.select("div.sc-lbhJGD a.sc-jgrJph");

        for (Element element : select) {

            //href 제거
            String attr = element.attr("abs:href");
            System.out.println("attr = " + attr);

            //테그로 찾기
            Optional<Element> h4 = element.select("h4").stream().findFirst();
            System.out.println("h4 = " + h4.get().text());

        }
    }

    @Test
    public void velogHtml2(){
        WebClient webClient = WebClient.create("https://velog.io/");
        String block = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("block = " + block);
    }

    @Test
    public void test() throws IOException {
        List<com.birariro.dailydevblogassemble.domain.library.Document> document = velogParser.getDocument("https://velog.io/");
        document.stream().forEach(System.out::println);
    }
}
