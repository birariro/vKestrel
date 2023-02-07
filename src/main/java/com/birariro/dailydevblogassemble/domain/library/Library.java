package com.birariro.dailydevblogassemble.domain.library;

import com.birariro.dailydevblogassemble.domain.member.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "tb_library")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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

    public Library(String name, String url, String origin, UrlType type) {
        this.name = name;
        this.url = url;
        this.origin = origin;
        this.type = type;
    }
}

