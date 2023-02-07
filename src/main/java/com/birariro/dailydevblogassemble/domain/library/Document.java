package com.birariro.dailydevblogassemble.domain.library;

import com.birariro.dailydevblogassemble.domain.member.BaseEntity;
import com.birariro.dailydevblogassemble.domain.member.State;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_document")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String title;
    private String url;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Library library;


    public Document(String title, String url, String author) {
        this.title = title;
        this.url = url;
        this.author = author;
        this.setState(State.ACTIVE);
    }
    public void initLibrary(Library library){
        this.library = library;
    }
}
