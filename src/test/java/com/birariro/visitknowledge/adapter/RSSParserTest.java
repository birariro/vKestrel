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

public class RSSParserTest {

    @Test
    public void test() throws IOException, FeedException {
        //
        String _url = "https://saramin.github.io/feed.xml";
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
                    .baseUrl("https://aws.amazon.com/ko/blogs/tech/feed/")
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
                System.out.println("item.getUri() = " + item.getUri());
                System.out.println("item.getAuthor() = " + item.getAuthor());
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
