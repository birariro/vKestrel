package com.birariro.dailydevblogassemble.domain.library;

import com.birariro.dailydevblogassemble.domain.member.BaseEntity;
import com.birariro.dailydevblogassemble.domain.member.State;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_library")
@Where(clause = "state = 'ACTIVE'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Library extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    private String name;
    @NotNull
    private String url;

    private String origin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UrlType type;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    List<Document> documents = new ArrayList<>();

    public Library(String name, String url, String origin, UrlType type) {
        this.name = name;
        this.url = url;
        this.origin = origin;
        this.type = type;
        this.setState(State.ACTIVE);
    }

    public boolean existDocument(Document document){

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
        this.documents.add(document);
        document.initLibrary(this);
    }
}

