package com.birariro.vkestrel.adapter.persistence.staff;

import com.birariro.vkestrel.adapter.persistence.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_staff")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Staff extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String token;
    private String channel;

    @Enumerated(EnumType.STRING)
    StaffType type;


    public Staff(String token, String channel, StaffType type) {
        this.token = token;
        this.channel = channel;
        this.type = type;
        super.active();
    }

}
