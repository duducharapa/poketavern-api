package com.charapadev.poketavern.unit.ability;

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

import com.charapadev.poketavern.ability.Ability;
import com.charapadev.poketavern.ability.AbilityRepository;
import com.charapadev.poketavern.ability.AbilityService;
import com.charapadev.poketavern.ability.CreateAbilityDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AbilityServiceTest {

    @Mock
    private AbilityRepository abilityRepository;

    @InjectMocks
    private AbilityService abilityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void list_all_abilities() {
        Ability synchronize = new Ability(1L, "Synchronize", "If the opponent causes a burn, paralysis, or poisoning of a Pokémon with Synchronize, the opponent receives the status condition too. Self-inflicted status conditions (for example through the use of an item) are not passed on.");
        Ability adaptability = new Ability(2L, "Adaptability", "Adaptability increases the effectiveness of STAB moves from the usual 1.5× to 2×");

        // Given two abilities stored on repository
        given(abilityRepository.findAll()).willReturn(List.of(synchronize, adaptability));

        // When listed the abilities
        List<Ability> result = abilityService.list();

        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected abilities
        assertEquals(synchronize, result.get(0));
        assertEquals(adaptability, result.get(1));

        // And the repository must be called
        then(abilityRepository).should().findAll();
    }

    @Test
    public void create_ability_sucessfully() {
        CreateAbilityDTO creationDTO = new CreateAbilityDTO("Adaptability", "Adaptability increases the effectiveness of STAB moves from the usual 1.5× to 2×");
        Ability ability = new Ability(1L, "Adaptability", "Adaptability increases the effectiveness of STAB moves from the usual 1.5× to 2×");

        // Given the ability saving correctly on repository
        given(abilityRepository.save(any())).willReturn(ability);

        // When created the ability
        Ability result = abilityService.create(creationDTO);

        // Then return the expected ability
        assertEquals(ability, result);

        // And the repository must be called
        then(abilityRepository).should().save(any());
    }

    @Test
    public void find_ability_successfully() {
        Ability overgrow = new Ability(1L, "Overgrow", "Overgrow increases the power of Grass-type moves by 50% (1.5×) when the ability-bearer's HP falls below a third of its maximum");
        long validID = 1L;

        //
        given(abilityRepository.findById(anyLong())).willReturn(Optional.of(overgrow));

        //
        Ability result = abilityService.find(validID);

        //
        assertEquals(overgrow, result);

        // And the repository must be called
        then(abilityRepository).should().findById(any());
    }

    @Test
    public void fail_on_ability_searching() {
        // Given an invalid ID
        long invalidID = 1L;

        // When tried to search the ability
        Executable executable = () -> abilityService.find(invalidID);

        // Then throws the NoSUchElement exception
        assertThrows(NoSuchElementException.class, executable);

        // And the repository must be called
        then(abilityRepository).should().findById(anyLong());
    }
    
}
