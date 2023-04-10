package com.birariro.visitknowledge.adapter;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.StringReader;

public class RSSParserTest {


    @Test
    public void test2()  {


        try{
            WebClient webClient = WebClient.builder()
                    .baseUrl("https://blog.jetbrains.com/team/feed/")
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
