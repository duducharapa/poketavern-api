package com.charapadev.poketavern.unit.pokemon;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.charapadev.poketavern.pokemon.CreatePokemonDTO;
import com.charapadev.poketavern.pokemon.Pokemon;
import com.charapadev.poketavern.pokemon.PokemonController;
import com.charapadev.poketavern.pokemon.PokemonService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PokemonControllerTest {
    
    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void list_pokemons_successfully() {
        Pokemon firstPokemon = new Pokemon(1L, "Charmander");
        Pokemon secondPokemon = new Pokemon(2L, "Ho-oh");

        // The service listing two pokemons
        given(pokemonService.list()).willReturn(List.of(firstPokemon, secondPokemon));

        // When listed the pokemons
        List<Pokemon> result = pokemonController.list();

        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected pokemons
        assertEquals(firstPokemon, result.get(0));
        assertEquals(secondPokemon, result.get(1));

        // And the service must be called
        then(pokemonService).should().list();
    }

    @Test
    public void create_pokemon_successfully() {
        CreatePokemonDTO creationDTO = new CreatePokemonDTO("Magmar");
        Pokemon magmar = new Pokemon("Magmar");

        // Given the service creating the Magmar correctly
        given(pokemonService.create(any())).willReturn(magmar);

        // When created the pokemon
        Pokemon result = pokemonController.create(creationDTO);

        // Then return the expected pokemon
        assertEquals(magmar, result);

        // And the service must be called
        then(pokemonService).should().create(any());
    }

}
