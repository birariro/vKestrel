package com.birariro.dailydevblogassemble.adapter;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;

public class RSSParser {

    @Test
    public void test() throws IOException, FeedException {
        //
        String _url = "https://engineering.linecorp.com/ko/feed/";
        URL url = new URL(_url);
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed build = syndFeedInput.build(new XmlReader(url));
        String title = build.getTitle();
        System.out.println("title = " + title);
        build.getEntries().stream().forEach(item -> {
            System.out.println("item.getTitle() = " + item.getTitle());
            System.out.println("item.getLink() = " + item.getLink());
            System.out.println("item.getLink() = " + item.getUri());
        });
    }
}
