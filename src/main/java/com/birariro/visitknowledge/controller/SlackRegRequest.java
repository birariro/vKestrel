package com.birariro.visitknowledge.controller;

import lombok.Getter;

@Getter
public class SlackRegRequest {
    private String normalBotToken;
    private String normalBotChannel;
    private String errorBotToken;
    private String errorBotChannel;
}
