package com.example.user.service.server.entity;

import com.example.cart.service.common.datatypes.enums.Gender;
import com.example.user.manager.datatypes.UserState;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"})
    },
    indexes = {
        @Index(columnList = "user_id")
    })
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class User extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false, length = 20)
    protected UserState userState;

    @Column(name = "user_id", nullable = false)
    protected String userId;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "age")
    protected Long age;

    @Column(name = "gender", nullable = false)
    protected Gender gender;

}
