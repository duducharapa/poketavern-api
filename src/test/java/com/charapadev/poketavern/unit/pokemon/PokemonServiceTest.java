package com.charapadev.poketavern.unit.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.charapadev.poketavern.pokemon.CreatePokemonDTO;
import com.charapadev.poketavern.pokemon.Pokemon;
import com.charapadev.poketavern.pokemon.PokemonRepository;
import com.charapadev.poketavern.pokemon.PokemonService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PokemonServiceTest {
    
    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void list_all_pokemons() {
        Pokemon firstPokemon = new Pokemon(1L, "Charmander");
        Pokemon secondPokemon = new Pokemon(2L, "Ho-oh");

        // Given two pokemons stored on repository
        given(pokemonRepository.findAll()).willReturn(List.of(firstPokemon, secondPokemon));

        // When listed the pokemons
        List<Pokemon> result = pokemonService.list();
        
        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected pokemons
        assertEquals(firstPokemon, result.get(0));
        assertEquals(secondPokemon, result.get(1));

        // And the repository must be called
        then(pokemonRepository).should().findAll();
    }

    @Test
    public void create_pokemon_successfully() {
        CreatePokemonDTO creationDTO = new CreatePokemonDTO("Squirtle");
        Pokemon squirtle = new Pokemon(1L, "Squirtle");

        // Given the pokemon saving correctly on repository
        given(pokemonRepository.save(any())).willReturn(squirtle);

        // When created the pokemon
        Pokemon result = pokemonService.create(creationDTO);

        // Then return the expected pokemon
        assertEquals(squirtle, result);

        // And the repository must be called
        then(pokemonRepository).should().save(any());
    }

    @Test
    public void find_pokemon_successfully() {
        Pokemon pikachu = new Pokemon(1L, "Pikachu");
        long validID = 1L;

        // Given the repository finding the correctly pokemon
        given(pokemonRepository.findById(anyLong())).willReturn(Optional.of(pikachu));

        // When searched the pokemon
        Pokemon result = pokemonService.find(validID);

        // Then return the expected pokemon
        assertEquals(pikachu, result);

        // And the repository must be called
        then(pokemonRepository).should().findById(anyLong());
    }

    @Test
    public void fail_on_pokemon_searching() {
        // Given an invalid pokemon ID
        long invalidID = 1L;

        // When tried to search the pokemon
        Executable executable = () -> pokemonService.find(invalidID);

        // Then throws the NoSuchElement exception
        assertThrows(NoSuchElementException.class, executable);

        // And the repository must be called
        then(pokemonRepository).should().findById(anyLong());
    }

}
