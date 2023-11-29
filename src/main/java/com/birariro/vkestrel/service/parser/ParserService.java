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
    private final GeekNewsParser geekNewsParser;

    public List<Document> getDocuments(String name, String url, ScriptType type) {

        List<Document> documents = new ArrayList<>();

        try {
            if (type == ScriptType.RSS) {
                documents =  rssParser.getDocument(url);
            }
            else if (type == ScriptType.VELOG) {
                documents =  velogParser.getDocument(url);
            }
            else if(type == ScriptType.BOANNEWS){
                documents =  boanNewsParser.getDocument(url);

            } else if(type == ScriptType.GEEKNEWS){
                documents =  geekNewsParser.getDocument(url);
            }

            if(documents.isEmpty()){
                String errorMessage =
                    String.format("action: site name: %s parsing success but empty document", name);
                Events.raise(ActionEvent.errorMessage(errorMessage));
            }

        } catch (Exception e) {

            String errorMessage =
                String.format("action: document parser \nsite name: %s \nurl: %s \nException: %s", name, url, getStackTraceToString(e.fillInStackTrace()));

            log.warn(errorMessage);
            Events.raise(ActionEvent.errorMessage(errorMessage));
            Events.raise(LibraryStateSwitchEvent.error(name));
        }
        return documents;
    }

    private String getStackTraceToString(Throwable t){

        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}