package com.birariro.vkestrel.adapter.parser;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.batch.step.event.LibraryStateSwitchEvent;
import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.jpa.library.Document;
import com.birariro.vkestrel.adapter.persistence.jpa.library.ScriptType;

import jdk.jfr.Event;
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
public class ParserAdapter {
    private final RSSParser rssParser;
    private final VelogParser velogParser;
    private final BoanNewsAdapter boanNewsAdapter;

    public List<Document> getDocuments(String name, String url, ScriptType type) {

        try {
            if (type == ScriptType.RSS) {
                return rssParser.getDocument(url);
            }
            if (type == ScriptType.VELOG) {
                return velogParser.getDocument(url);
            }
            if(type == ScriptType.BOANNEWS){
                return boanNewsAdapter.getDocument(url);
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