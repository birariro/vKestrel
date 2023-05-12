package com.birariro.vkestrel.service.parser;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.batch.step.event.LibraryStateSwitchEvent;
import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.library.Document;
import com.birariro.vkestrel.adapter.persistence.library.ScriptType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserService {
    private final RSSParser rssParser;
    private final VelogParser velogParser;
    private final BoanNewsParser boanNewsParser;

    public List<Document> getDocuments(String name, String url, ScriptType type) {

        try {
            if (type == ScriptType.RSS) {
                return rssParser.getDocument(url);
            }
            if (type == ScriptType.VELOG) {
                return velogParser.getDocument(url);
            }
            if(type == ScriptType.BOANNEWS){
                return boanNewsParser.getDocument(url);
            }


            Events.raise(ActionEvent.errorMessage("not exist url type :" + type));
        } catch (Exception e) {


            String errorMessage =
                String.format("action: document parser \nsite name: %s \nurl: %s \nException: %s", name, url, getStackTraceToString(e.fillInStackTrace()));
            Events.raise(ActionEvent.errorMessage(errorMessage));
            Events.raise(LibraryStateSwitchEvent.inActive(name));
        }
        return new ArrayList<Document>();
    }

    private String getStackTraceToString(Throwable t){

        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}