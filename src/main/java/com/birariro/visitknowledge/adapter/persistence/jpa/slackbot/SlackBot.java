package com.birariro.visitknowledge.adapter.persistence.jpa.slackbot;

import com.birariro.visitknowledge.adapter.persistence.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tb_slack_bot")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SlackBot extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String token;
    private String channel;
    private String errorChannel;;
}
