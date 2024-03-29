package com.birariro.vkestrel.adapter.persistence.library;

import com.birariro.vkestrel.adapter.persistence.BaseEntity;
import com.birariro.vkestrel.adapter.persistence.EntityState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Entity
@Table(name = "tb_library")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"documents"})
public class Library extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    private String name;
    @NotNull
    @Column(length = 500)
    private String url;

    private String origin;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "script_type")
    private ScriptType scriptType;


    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    List<Document> documents = new ArrayList<>();

    public Library(String name, String url, String origin, ScriptType scriptType) {
        this.name = name;
        this.url = url;
        this.origin = origin;
        this.scriptType = scriptType;
        this.setEntityState(EntityState.ACTIVE);
    }



    public boolean existDocument(Document document){

        if(documents.size() == 0) return false;
        return this.documents.stream()
                .filter(item -> item.getTitle().equals(document.getTitle()))
                .findFirst()
                .isPresent();
    }

    public List<Document> getWaitDocuments(){

        return this.documents.stream()
                .filter(item -> item.getSendState() == SendState.WAITING)
                .collect(Collectors.toList());
    }
    public void addDocument(Document document){

        boolean present = existDocument(document);

        if(! present){
            this.documents.add(document);
            document.initLibrary(this);
            log.info("[add document in library] " + this.name + " in "+ document.getTitle());
        }

    }
}

