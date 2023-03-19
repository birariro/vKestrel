package com.birariro.visitknowledge.controller.page;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PageController {

    private final DocumentRepository documentRepository;
    @GetMapping
    public String home(Model model){
        PageRequest pageRequest = PageRequest.of(0,20);
        Page<Document> all = documentRepository.findAll(pageRequest);

        List<PostModel> collect = all.stream()
                .map(item ->{
                    LocalDateTime createAt = item.getCreateAt();
                    String date = createAt.getYear() +"-"+ createAt.getMonthValue() +"-"+ createAt.getDayOfMonth();
                    return new PostModel(item.getLibrary().getName(),
                            item.getTitle(),
                            item.getAuthor(),
                            item.getUrl(),
                            date);
                })
                .collect(Collectors.toList());

        model.addAttribute("posts",collect);
        return "page/index";
    }

    @GetMapping("about")
    public String abort(Model model){
        return "page/about";
    }
    @GetMapping("contact")
    public String contact(Model model){
        return "page/contact";
    }
    @GetMapping("post")
    public String post(Model model){
        return "page/post";
    }

}
