package com.birariro.dailydevblogassemble.domain.member;

import com.birariro.dailydevblogassemble.domain.BaseEntity;
import com.birariro.dailydevblogassemble.domain.EntityState;
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

    @Embedded
    private Email email;

    public Member(Email email) {
        this.email = email;
        this.setEntityState(EntityState.INACTIVE);
    }

    public void active(){
        this.setEntityState(EntityState.ACTIVE);
    }
}
