package com.birariro.vkestrel.adapter.parser;

import com.birariro.vkestrel.adapter.persistence.jpa.library.Document;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RSSParser {

    @Value("${setting.parser.max-size:10}")
    private int maxSize;

    public List<Document> getDocument(String resUrl) throws FeedException {

        WebClient webClient = WebClient.builder()
                .baseUrl(resUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();

        String body = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        StringReader stringReader = new StringReader(body);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed build = input.build(stringReader);

        List<Document> collect = build.getEntries()
                .stream()
                .map(item -> {
                    String link = "";
                    if(item.getUri().startsWith("http")) link = item.getUri();
                    else if (item.getLink().startsWith("http")) link =  item.getLink();
                    return new Document(item.getTitle(), link, item.getAuthor());
                })
                .limit(maxSize)
                .collect(Collectors.toList());

        return collect;
    }
}
