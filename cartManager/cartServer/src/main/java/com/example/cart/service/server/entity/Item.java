package com.example.cart.service.server.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Item extends BaseEntity {

    @Column(name = "item_name", nullable = false, length = 10)
    protected String name;

    @Column(name = "item_quantity", nullable = false, length = 10)
    protected Long quantity;

    @Column(name = "item_price", nullable = false, length = 10)
    protected Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    protected Cart cartId;
}
