package com.milankas.training.persistance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="user_id")
    private UUID userId;

    @OneToMany(cascade=ALL, targetEntity = LineItemEntity.class)
    @JoinColumn(name = "line_item", referencedColumnName = "id")
    private List<LineItemEntity> lineItemEntities;

    @Column(name = "email_address")
    private String emailAddress;

    @OneToOne(cascade = CascadeType.ALL,  targetEntity = AddressEntity.class)
    @JoinColumn(name = "shop_address", referencedColumnName = "id")
    private AddressEntity shopAddressEntity;

}
