package com.birariro.visitknowledge.adapter.parser;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.birariro.visitknowledge.adapter.parser.utils.SSL;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BoanNewsAdapter {

	@Value("${setting.parser.max-size:10}")
	private int maxSize;
	private final String baseUrl = "https://www.boannews.com";

	public List<Document> getDocument(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {

		SSL.setSSL();

		Connection connect = Jsoup.connect(url);
		org.jsoup.nodes.Document document = connect.get();

		List<Document> collect = new ArrayList<>();

		//class 이름으로 얻기
		Elements newsPosts = document.select(".news_list");

		for (Element newsPost : newsPosts) {

			Optional<Element> titleOp = newsPost.select(".news_txt").stream().findFirst();
			if(!titleOp.isPresent()) continue;

			String title = titleOp.get().text();

			//테그로 찾기
			Optional<Element>  linkTagOp = newsPost.select("a").stream().findFirst();
			if(!linkTagOp.isPresent()) continue;

			//속성 찾기
			String link = baseUrl+ linkTagOp.get().attr("href");

			Document _document = new Document(title, link, "");
			collect.add(_document);
			if(collect.size() >= maxSize) break;
		}


		return collect;
	}
}
