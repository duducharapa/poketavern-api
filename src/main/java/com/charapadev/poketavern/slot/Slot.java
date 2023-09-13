package com.charapadev.poketavern.slot;

import com.charapadev.poketavern.ability.Ability;
import com.charapadev.poketavern.item.Item;
import com.charapadev.poketavern.pokemon.Pokemon;
import com.charapadev.poketavern.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Slot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pokemon pokemon;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ability ability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Team team;

    public Slot() {}

    public Slot(Long id, Pokemon pokemon, Item item, Ability ability) {
        this.id = id;
        this.pokemon = pokemon;
        this.item = item;
        this.ability = ability;
    }

    public Slot(Pokemon pokemon, Item item, Ability ability) {
        this.pokemon = pokemon;
        this.item = item;
        this.ability = ability;
    }

    public Long getId() {
        return id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public Item getItem() {
        return item;
    }

    public Ability getAbility() {
        return ability;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String toString() {
        return String.format(
            "[id=%s, pokemon=%s, item=%s, ability=%s]",
            id, pokemon, item, ability
        );
    }

}
