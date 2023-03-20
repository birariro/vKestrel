package com.birariro.visitknowledge.controller.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostPageModel {

    private int page;
    private List<Model> models;

    @Getter
    @AllArgsConstructor
    public static class Model{
        private String blogTitle;
        private String postTitle;
        private String author;
        private String url;
        private String date;
    }
}
