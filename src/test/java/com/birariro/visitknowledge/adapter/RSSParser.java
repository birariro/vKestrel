package com.birariro.visitknowledge.adapter;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class RSSParser {

    @Test
    public void test() throws IOException, FeedException {
        //
        String _url = "https://meetup.nhncloud.com/rss";
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
}
