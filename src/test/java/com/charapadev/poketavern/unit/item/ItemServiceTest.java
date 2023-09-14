package com.charapadev.poketavern.unit.item;

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

import com.charapadev.poketavern.item.CreateItemDTO;
import com.charapadev.poketavern.item.Item;
import com.charapadev.poketavern.item.ItemRepository;
import com.charapadev.poketavern.item.ItemService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ItemServiceTest {
    
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void list_all_items() {
        Item oranBerry = new Item(1L, "Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");
        Item focusSash = new Item(2L, "Focus Sash", "An item to be held by a Pokémon. If it has full HP, the holder will endure one potential KO attack, leaving 1 HP");

        // Given the repository with two items stored
        given(itemRepository.findAll()).willReturn(List.of(oranBerry, focusSash));

        // When listed the items
        List<Item> result = itemService.list();

        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected items
        assertEquals(oranBerry, result.get(0));
        assertEquals(focusSash, result.get(1));

        // And the repository must be called
        then(itemRepository).should().findAll();
    }

    @Test
    public void create_item_successfully() {
        CreateItemDTO creationDTO = new CreateItemDTO("Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");
        Item oranBerry = new Item(1L, "Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");

        // Given the repository saving the Oran Berry correctly
        given(itemRepository.save(any())).willReturn(oranBerry);

        // When created the item
        Item result = itemService.create(creationDTO);

        // Then return the expected item
        assertEquals(oranBerry, result);

        // And the repository must be called
        then(itemRepository).should().save(any());
    }

    @Test
    public void find_item_successfully() {
        long validID = 1L;
        Item oranBerry = new Item(1L, "Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");

        // Given the repository finding the Oran Berry successfully
        given(itemRepository.findById(anyLong())).willReturn(Optional.of(oranBerry));

        // When searched the Oran Berry
        Item result = itemService.find(validID);

        // Then return the expected item
        assertEquals(oranBerry, result);

        // And the repository must be called
        then(itemRepository).should().findById(anyLong());
    }

    @Test
    public void fail_on_item_searching() {
        // Given an invalid ID
        Long invalidID = 1L;

        // When tried to search the item
        Executable executable = () -> itemService.find(invalidID);

        // Then throws the NoSuchElement exception
        assertThrows(NoSuchElementException.class, executable);

        // And the repository must be called
        then(itemRepository).should().findById(anyLong());
    }

}
