package com.birariro.playday.domain.library;

import com.birariro.playday.domain.member.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_library")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String url;
    private String origin;

    @Enumerated(EnumType.STRING)
    private UrlType type;
}

