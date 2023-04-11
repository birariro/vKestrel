package com.birariro.visitknowledge.adapter;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.birariro.visitknowledge.adapter.parser.ParserAdapter;
import com.birariro.visitknowledge.adapter.parser.utils.SSL;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.ScriptType;

@SpringBootTest
public class BoannewsPerserTest {
    @Autowired
    ParserAdapter parserAdapter;


    @Test
    public void jsoupTest() throws IOException, NoSuchAlgorithmException, KeyManagementException {

        String baseUrl = "https://www.boannews.com";
        SSL.setSSL();
        Connection connect = Jsoup.connect(baseUrl+"/media/s_list.asp?skind=5");
        Document document = connect.get();
        //class 이름으로 얻기
        Elements newsPosts = document.select(".news_list");

        for (Element newsPost : newsPosts) {

            String title = newsPost.select(".news_txt").stream().findFirst().get().text();
            System.out.println("title = " + title);

            //테그로 찾기
            Element linkTag = newsPost.select("a").stream().findFirst().get();
            //속성 찾기
            String link = linkTag.attr("href");
            System.out.println("link = " + baseUrl+link);

        }
    }

    @Test
    public void testtest(){
        parserAdapter.getDocuments("https://www.boannews.com/media/s_list.asp?skind=5",ScriptType.BOANNEWS);
    }



}
