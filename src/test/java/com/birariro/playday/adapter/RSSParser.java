package com.birariro.playday.adapter;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;

@SpringBootTest
public class RSSParser {

    @Test
    public void test() throws IOException, FeedException {
        //

        URL url = new URL("https://www.44bits.io/ko/feed/posts");
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed build = syndFeedInput.build(new XmlReader(url));
        build.getEntries().stream().forEach(item -> {

            System.out.println("item.getTitle() = " + item.getTitle());
            System.out.println("item.getLink() = " + item.getLink());
        });
    }
}
