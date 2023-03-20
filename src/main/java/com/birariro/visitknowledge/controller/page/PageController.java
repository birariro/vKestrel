package com.birariro.visitknowledge.controller.page;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PageController {

    private final DocumentRepository documentRepository;
    @GetMapping
    public String home(@RequestParam(value = "page", required = false, defaultValue = "0") int page, Model model){

        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.by("createAt").descending());
        Page<Document> all = documentRepository.findAll(pageRequest);

        List<PostPageModel.Model> collect = all.stream()
                .map(item -> {
                    LocalDateTime createAt = item.getCreateAt();
                    String date = createAt.getYear() + "-" + createAt.getMonthValue() + "-" + createAt.getDayOfMonth();
                    return new PostPageModel.Model(item.getLibrary().getName(),
                            item.getTitle(),
                            item.getAuthor(),
                            item.getUrl(),
                            date);
                })
                .collect(Collectors.toList());

        PostPageModel postPageModel = new PostPageModel(page, collect);

        model.addAttribute("posts",postPageModel);
        return "index";
    }

    @GetMapping("about")
    public String abort(Model model){
        return "about";
    }
    @GetMapping("contact")
    public String contact(Model model){
        return "contact";
    }
    @GetMapping("post")
    public String post(Model model){
        return "post";
    }

}
