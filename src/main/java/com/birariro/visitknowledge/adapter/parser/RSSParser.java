package com.birariro.visitknowledge.adapter.parser;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
class RSSParser {

    public List<Document> getDocument(String resUrl) throws IOException, FeedException {
        URL url = new URL(resUrl);
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed build = syndFeedInput.build(new XmlReader(url));

        List<Document> collect = build.getEntries()
                .stream()
                .map(item -> {
                        return new Document(item.getTitle(), item.getUri(), item.getAuthor());
                    }).collect(Collectors.toList());

        return collect;
    }
}
