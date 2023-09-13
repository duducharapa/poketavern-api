package com.charapadev.poketavern.team;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.charapadev.poketavern.slot.Slot;
import com.charapadev.poketavern.slot.SlotService;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final SlotService slotService;

    public TeamService(TeamRepository teamRepository, SlotService slotService) {
        this.teamRepository = teamRepository;
        this.slotService = slotService;
    }

    public List<Team> list() {
        return teamRepository.findAll();
    }

    public Team create(CreateTeamDTO creationDTO) {
        Team newTeam = new Team(creationDTO.title());
        
        Optional.ofNullable(creationDTO.author())
            .ifPresent(author -> newTeam.setAuthor(author));

        List<Slot> slots = creationDTO.slots()
            .stream().map(slot -> slotService.generate(slot, newTeam))
            .toList();
        slots.forEach(newTeam::addSlot);

        return teamRepository.save(newTeam);
    }
}
