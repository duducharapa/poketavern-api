package com.charapadev.poketavern.pokemon;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> list() {
        return pokemonRepository.findAll();
    }

    public Pokemon create(CreatePokemonDTO creationDTO) {
        Pokemon newPokemon = new Pokemon(creationDTO.name());

        return pokemonRepository.save(newPokemon);
    }

    public Pokemon find(Long id) throws NoSuchElementException {
        return pokemonRepository.findById(id)
            .orElseThrow();
    }

}
