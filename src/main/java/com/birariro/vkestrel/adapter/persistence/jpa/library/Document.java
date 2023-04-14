package com.birariro.vkestrel.adapter.persistence.jpa.library;

import com.birariro.vkestrel.adapter.persistence.jpa.BaseEntity;
import com.birariro.vkestrel.adapter.persistence.jpa.EntityState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_document")
@Where(clause = "state = 'ACTIVE'")
@Getter
@ToString(exclude = "library")
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

    @Column(name = "send_state")
    @Enumerated(EnumType.STRING)
    private SendState sendState;

    @ManyToOne(fetch = FetchType.LAZY)
    private Library library;


    public Document(String title, String url, String author) {
        this.title = title;
        this.url = url;
        this.author = author;
        this.setEntityState(EntityState.ACTIVE);
        this.sendState = SendState.WAITING;
    }

    public void sendComplete(){
        this.sendState = SendState.COMPLETE;
    }

    public void initLibrary(Library library){
        this.library = library;
    }
}
