package com.charapadev.poketavern.unit.slot;

import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.charapadev.poketavern.ability.Ability;
import com.charapadev.poketavern.ability.AbilityService;
import com.charapadev.poketavern.item.Item;
import com.charapadev.poketavern.item.ItemService;
import com.charapadev.poketavern.pokemon.Pokemon;
import com.charapadev.poketavern.pokemon.PokemonService;
import com.charapadev.poketavern.slot.GenerateSlotDTO;
import com.charapadev.poketavern.slot.Slot;
import com.charapadev.poketavern.slot.SlotService;
import com.charapadev.poketavern.team.Team;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SlotServiceTest {

    @Mock
    private PokemonService pokemonService;

    @Mock
    private ItemService itemService;

    @Mock
    private AbilityService abilityService;

    @InjectMocks
    private SlotService slotService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generate_slot_successfully() {
        Pokemon quilava = new Pokemon(1L, "Quilava");
        Item lumBerry = new Item(1L, "Lum Berry", "If held by a Pokémon, it recovers from any status problem");
        Ability blaze = new Ability(1L, "Flash fire", "When a Pokémon with Flash Fire is hit by a Fire-type move, it does not deal damage but instead raises the power of the bearer's Fire-type moves by 50%. Subsequent hits do not raise the power even more, but the effect remains while the ability-bearer is in battle.");
        
        Team team = new Team("New team");
        Slot quilavaSlot = new Slot(quilava, lumBerry, blaze);
        quilavaSlot.setTeam(team);

        // The services finding the Quilava, Lum Berry and Blaze correctly
        given(pokemonService.find(anyLong())).willReturn(quilava);
        given(itemService.find(anyLong())).willReturn(lumBerry);
        given(abilityService.find(anyLong())).willReturn(blaze);

        // When generated the Quilava slot
        Slot result = slotService.generate(new GenerateSlotDTO(1L, 1L, 1L), team);

        // Then the returned slot must have the same properties as expected
        assertThat(result)
            .usingRecursiveComparison().isEqualTo(quilavaSlot);

        // And the services related must be called
        then(pokemonService).should().find(anyLong());
        then(itemService).should().find(anyLong());
        then(abilityService).should().find(anyLong());
    }
    
}
