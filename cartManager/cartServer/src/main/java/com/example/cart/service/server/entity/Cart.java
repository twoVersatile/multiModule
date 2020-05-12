package com.example.cart.service.server.entity;

import com.example.cart.service.datatypes.CartStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cart")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Cart extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "cart_state", nullable = false, length = 10)
    protected CartStatus cartStatus;

    @Column(name = "user_id", nullable = false)
    protected String userId;

    @Transient
    @OneToMany(cascade = CascadeType.REMOVE,  fetch = FetchType.LAZY)
    protected List<Item> items;
}
