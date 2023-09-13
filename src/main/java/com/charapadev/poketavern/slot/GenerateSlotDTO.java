package com.charapadev.poketavern.slot;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GenerateSlotDTO(
    @JsonProperty("pokemon-id")
    Long pokemonId,
    
    @JsonProperty("item-id")
    Long itemId,
    
    @JsonProperty("ability-id")
    Long abilityId
) {
    
}
