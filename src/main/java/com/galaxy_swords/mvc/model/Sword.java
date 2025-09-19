package com.galaxy_swords.mvc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sword {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    private Rarity rarity;

    private double damage;

    private List<String> characteristics;

    private String description;

    @ManyToOne
    private User owner;

    private String imageUrl;

}
