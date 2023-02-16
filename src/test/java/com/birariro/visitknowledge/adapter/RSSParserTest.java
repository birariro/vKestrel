package com.birariro.visitknowledge.adapter;

import com.birariro.visitknowledge.adapter.parser.RSSParser;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.UrlType;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class RSSParserTest {

    @Autowired
    RSSParser rssParser;
    @Autowired
    LibraryRepository libraryRepository;


    @Test
    public void rssParserTest(){

        List<Library> all = libraryRepository.findAll().stream().filter(item -> item.getType() == UrlType.RSS)
                .filter(item -> !item.getName().equals("카카오"))
                .filter(item -> !item.getName().equals("딜리셔스"))
                .filter(item -> !item.getName().equals("카카오스타일"))
                .filter(item -> !item.getName().equals("가비아"))
                .filter(item -> !item.getName().equals("기억에 남는 블로그가 되길"))
                .filter(item -> !item.getName().equals("당근마켓"))
                .filter(item -> !item.getName().equals("뱅크샐러드"))
                .filter(item -> !item.getName().equals("테이블링"))
                .filter(item -> !item.getName().equals("무신사"))
                .filter(item -> !item.getName().equals("42Seoul"))
                .filter(item -> !item.getName().equals("44bits"))
                .filter(item -> !item.getName().equals("스포카"))
                .filter(item -> !item.getName().equals("리디"))
                .filter(item -> !item.getName().equals("헤이딜러"))
                .filter(item -> !item.getName().equals("라인"))
                .filter(item -> !item.getName().equals("리모트몬스터"))
                .filter(item -> !item.getName().equals("넷마블 기술 블로그"))
                .filter(item -> !item.getName().equals("왓챠"))
                .filter(item -> !item.getName().equals("네이버플레이스"))
                .filter(item -> !item.getName().equals("지마켓"))
                .filter(item -> !item.getName().equals("NHN Cloud"))
                .filter(item -> !item.getName().equals("데일리호텔"))
                .filter(item -> !item.getName().equals("토스"))
                .filter(item -> !item.getName().equals("원티드랩"))
                .filter(item -> !item.getName().equals("카카오엔터프라이즈"))
                .filter(item -> !item.getName().equals("여기어때"))
                .filter(item -> !item.getName().equals("기억보단 기록을"))
                .filter(item -> !item.getName().equals("우아한형제들"))
                .filter(item -> !item.getName().equals("29cm"))
                .collect(Collectors.toList());
        for (Library library : all) {

            try {
                System.out.println("library = " + library.getName());
                System.out.println("library.getUrl() = " + library.getUrl());
                rssParser.getDocument(library.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
                Assertions.fail();
            } catch (FeedException e) {
                e.printStackTrace();
                Assertions.fail();
            }
        }
    }
    @Test
    public void test() throws IOException, FeedException {
        //
        String _url = "https://news.hada.io/rss/news";
        URL url = new URL(_url);

        SyndFeedInput syndFeedInput = new SyndFeedInput();
        XmlReader xmlReader = new XmlReader(url);
        SyndFeed build = syndFeedInput.build(xmlReader);

        String title = build.getTitle();
        System.out.println("title = " + title);
        build.getEntries().stream().forEach(item -> {
            System.out.println("item.getTitle() = " + item.getTitle());
            System.out.println("item.getLink() = " + item.getLink());
            System.out.println("item.getLink() = " + item.getUri());
        });
    }

    @Test
    public void test2()  {


        try{
            WebClient webClient = WebClient.builder()
                    .baseUrl("https://feeds.feedburner.com/geeknews-feed")
                    .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                    .build();
            String block = webClient.get()
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            //System.out.println("block = " + block);

            StringReader stringReader = new StringReader(block);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed build = input.build(stringReader);

            build.getEntries().stream().forEach(item -> {
                System.out.println("item.getTitle() = " + item.getTitle());
                System.out.println("item.getLink() = " + item.getLink());
                System.out.println("item.getLink() = " + item.getAuthor());
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
