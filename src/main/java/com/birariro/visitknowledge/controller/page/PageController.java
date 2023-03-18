package com.birariro.visitknowledge.controller.page;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class PageController {

    @GetMapping
    public String home(Model model){
        List<PostModel> postModels = Arrays.asList(
                new PostModel("우아한 형제들", "배달의 민족 주문", "배민저자"),
                new PostModel("당근 마켓", "당근 오이 양파", "당근저자"),
                new PostModel("카카오", "카카오 초콜릿", "카카오저자"));

        model.addAttribute("posts",postModels);
        return "page/index";
    }

    @GetMapping("/card")
    public String cards(Model model){
        List<Card> cards = Arrays.asList(new Card("title","de1","https://picsum.photos/200/300"),
                new Card("title2","de3","https://picsum.photos/200/300"),
                new Card("title3","de2","https://picsum.photos/200/300"));

        model.addAttribute("cards", cards);
        return "page/card";
    }
}
