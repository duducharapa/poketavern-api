package com.charapadev.poketavern.unit.ability;

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
import com.charapadev.poketavern.ability.AbilityController;
import com.charapadev.poketavern.ability.AbilityService;
import com.charapadev.poketavern.ability.CreateAbilityDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AbilityControllerTest {

    @Mock
    private AbilityService abilityService;

    @InjectMocks
    private AbilityController abilityController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public void list_abilities_successfully() {
        Ability synchronize = new Ability(1L, "Synchronize", "If the opponent causes a burn, paralysis, or poisoning of a Pokémon with Synchronize, the opponent receives the status condition too. Self-inflicted status conditions (for example through the use of an item) are not passed on.");
        Ability adaptability = new Ability(2L, "Adaptability", "Adaptability increases the effectiveness of STAB moves from the usual 1.5× to 2×");

        // Given the service with two abilities stored
        given(abilityService.list()).willReturn(List.of(synchronize, adaptability));

        // When listed the abilities
        List<Ability> result = abilityController.list();

        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected abilities
        assertEquals(synchronize, result.get(0));
        assertEquals(adaptability, result.get(1));

        // And the service must be called
        then(abilityService).should().list();
    }

    @Test
    public void create_ability_successfully() {
        CreateAbilityDTO creationDTO = new CreateAbilityDTO("Adaptability", "Adaptability increases the effectiveness of STAB moves from the usual 1.5× to 2×");
        Ability adaptability = new Ability(1L, "Adaptability", "Adaptability increases the effectiveness of STAB moves from the usual 1.5× to 2×");
        
        // Given the service creating the Adaptability correctly
        given(abilityService.create(any())).willReturn(adaptability);
    
        // When created the Adaptability ability
        Ability result = abilityController.create(creationDTO);

        // Then return the expected ability
        assertEquals(adaptability, result);

        // And the service must be called
        then(abilityService).should().create(any());
    }
    
}
