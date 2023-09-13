package com.charapadev.poketavern.ability;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class AbilityService {
    private AbilityRepository abilityRepository;

    public AbilityService(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }

    public List<Ability> list() {
        return abilityRepository.findAll();
    }

    public Ability find(Long id) throws NoSuchElementException {
        return abilityRepository.findById(id)
            .orElseThrow();
    }

    public Ability create(CreateAbilityDTO creationDTO) {
        Ability ability = new Ability(creationDTO.name(), creationDTO.description());

        return abilityRepository.save(ability);
    }
}
