package com.charapadev.poketavern.slot;

import org.springframework.stereotype.Service;

import com.charapadev.poketavern.ability.Ability;
import com.charapadev.poketavern.ability.AbilityService;
import com.charapadev.poketavern.item.Item;
import com.charapadev.poketavern.item.ItemService;
import com.charapadev.poketavern.pokemon.Pokemon;
import com.charapadev.poketavern.pokemon.PokemonService;
import com.charapadev.poketavern.team.Team;

@Service
public class SlotService {
    private PokemonService pokemonService;
    private ItemService itemService;
    private AbilityService abilityService;

    public SlotService(PokemonService pokemonService, ItemService itemService, AbilityService abilityService) {
        this.pokemonService = pokemonService;
        this.itemService = itemService;
        this.abilityService = abilityService;
    }

    public Slot generate(GenerateSlotDTO generationDTO, Team team) {
        Pokemon pokemonFound = pokemonService.find(generationDTO.pokemonId());
        Item itemFound = itemService.find(generationDTO.itemId());
        Ability abilityFound = abilityService.find(generationDTO.abilityId());

        Slot newSlot = new Slot(pokemonFound, itemFound, abilityFound);
        newSlot.setTeam(team);

        return newSlot;
    }

}
