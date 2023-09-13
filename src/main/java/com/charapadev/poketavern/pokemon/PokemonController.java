package com.charapadev.poketavern.pokemon;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody List<Pokemon> list() {
        return pokemonService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Pokemon create(@RequestBody CreatePokemonDTO creationDTO) {
        return pokemonService.create(creationDTO);
    }

}
