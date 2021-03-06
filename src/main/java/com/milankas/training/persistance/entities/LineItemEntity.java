package com.milankas.training.persistance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "line_item")
public class LineItemEntity {

    @Id
    private UUID id;

    @Column(name="qty")
    private Integer qty;

}
