package com.birariro.vkestrel.service.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.birariro.vkestrel.adapter.persistence.library.Document;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class GeekNewsParser {

    @Value("${setting.parser.max-size:10}")
    private int maxSize;

    public List<Document> getDocument(String url) throws IOException {

        List<Document> collect = new ArrayList<>();

        Connection connect = Jsoup.connect(url);
        org.jsoup.nodes.Document document = connect.get();

        Elements select = document.select("div.topic_row");

        for (Element element : select) {
            Element titleElement = element.select("div.topictitle a").stream().findFirst().get();
            String title = titleElement.select("h1").stream().findFirst().get().text();

            String href = titleElement.attr("abs:href");

            String likeElement = element.select("div.topicinfo a.u").stream().findFirst().get().text();

            int likeCount = 0;
            if(! likeElement.contains("토론")){
                likeCount = Integer.parseInt(likeElement.replace("댓글", "").replace("개", "").trim());
            }

            /**
             * 댓글이 5개 이상 달린 글만 가지고온다.
             */
            if(likeCount >= 5){
                Document _document = new Document(title, href, "");
                collect.add(_document);
            }
            if(collect.size() >= maxSize) break;
        }

        return collect;
    }

}
