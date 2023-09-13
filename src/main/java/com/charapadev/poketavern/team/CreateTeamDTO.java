package com.charapadev.poketavern.team;

import java.util.List;

import com.charapadev.poketavern.slot.GenerateSlotDTO;

public record CreateTeamDTO(
    String title,
    String author,
    List<GenerateSlotDTO> slots
) {
    
}
