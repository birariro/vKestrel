package com.birariro.vkestrel.adapter.persistence.jpa.member;

import com.birariro.vkestrel.adapter.persistence.jpa.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String token;
    private String channel;

    @Enumerated(EnumType.STRING)
    MemberType type;


    public Member(String token, String channel, MemberType type) {
        this.token = token;
        this.channel = channel;
        this.type = type;
        super.active();
    }

}
