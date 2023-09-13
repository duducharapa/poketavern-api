package com.charapadev.poketavern.pokemon;

import com.charapadev.poketavern.ability.Ability;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pokemon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ability ability;

    public Pokemon() {}

    public Pokemon(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pokemon(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public String toString() {
        return String.format(
            "[id=%s, name=%s]",
            id, name
        );
    }

}
