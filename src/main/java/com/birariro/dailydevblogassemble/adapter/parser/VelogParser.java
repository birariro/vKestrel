package com.birariro.dailydevblogassemble.adapter.parser;

import com.birariro.dailydevblogassemble.domain.library.Document;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class VelogParser {

    public List<Document> getDocument(String url) throws IOException {

        Connection connect = Jsoup.connect(url);
        org.jsoup.nodes.Document document = connect.get();

        List<Document> collect = new ArrayList<>();
        Elements select = document.select("div.sc-lbhJGD a.sc-jgrJph");

        for (Element element : select) {

            String href = element.attr("abs:href");

            Optional<Element> title = element.select("h4").stream().findFirst();
            if(!title.isPresent()) continue;

            Document _document = new Document(title.get().text(), href, "");
            collect.add(_document);
        }

        return collect;
    }

}
