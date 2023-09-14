package com.charapadev.poketavern.unit.team;

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
import com.charapadev.poketavern.item.Item;
import com.charapadev.poketavern.pokemon.Pokemon;
import com.charapadev.poketavern.slot.GenerateSlotDTO;
import com.charapadev.poketavern.slot.Slot;
import com.charapadev.poketavern.team.CreateTeamDTO;
import com.charapadev.poketavern.team.Team;
import com.charapadev.poketavern.team.TeamController;
import com.charapadev.poketavern.team.TeamService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;


@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TeamControllerTest {
    
    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void list_teams_successfully() {
        Pokemon clefable = new Pokemon(1L, "Clefable");
        Item leftovers = new Item(1L, "Leftovers", "An item to be held by a Pokémon. The holder's HP is gradually restored during battle");
        Ability unaware = new Ability(1L, "Unaware", "Ignores any stat changes in the Pokémon");
        Slot clefableSlot = new Slot(1L, clefable, leftovers, unaware);

        Team firstTeam = new Team(1L, "Equipe 1");
        firstTeam.addSlot(clefableSlot);

        Team secondTeam = new Team(2L, "Time 2");
        secondTeam.addSlot(clefableSlot);

        // The service showing two teams stored
        given(teamService.list()).willReturn(List.of(firstTeam, secondTeam));

        // When listed the teams
        List<Team> result = teamController.list();

        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected teams
        assertEquals(firstTeam, result.get(0));
        assertEquals(secondTeam, result.get(1));

        // And the service must be called
        then(teamService).should().list();
    }

    @Test
    public void create_team_successfully() {
        // Data to perform creation
        GenerateSlotDTO generationSlotDTO = new GenerateSlotDTO(1L, 1L, 1L);
        CreateTeamDTO creationDTO = new CreateTeamDTO("Equipe 2", "Teste", List.of(generationSlotDTO));

        // Data to assert
        Pokemon crawdaunt = new Pokemon(1L, "Crawdaunt");
        Ability shellArmor = new Ability(1L, "Shell Armor", "Shell Armor prevents the Pokémon from receiving a critical hit");
        Item choiceBand = new Item(1L, "Choice Band", "Raises Attack, but only one move can be used");
        Slot crawdauntSlot = new Slot(1L, crawdaunt, choiceBand, shellArmor);
        
        Team team = new Team(1L, "Equipe 1");
        team.addSlot(crawdauntSlot);

        // The service saving the team correctly
        given(teamService.create(any())).willReturn(team);
        
        // When created the team
        Team result = teamService.create(creationDTO);

        // Then return the expected team
        assertEquals(team, result);

        // And the service must be called
        then(teamService).should().create(any());
    }

}
