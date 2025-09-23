package com.galaxy_swords.mvc.model;

import com.galaxy_swords.mvc.model.enums.Rarity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sword {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    private double damage;

    private List<String> characteristics;

    private String description;

    @ManyToOne
    private Adventurer owner;

    private String imageUrl;

    private double price;

}
