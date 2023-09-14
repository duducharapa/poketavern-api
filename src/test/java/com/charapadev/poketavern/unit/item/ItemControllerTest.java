package com.charapadev.poketavern.unit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.charapadev.poketavern.item.CreateItemDTO;
import com.charapadev.poketavern.item.Item;
import com.charapadev.poketavern.item.ItemController;
import com.charapadev.poketavern.item.ItemService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void list_items_successfully() {
        Item oranBerry = new Item(1L, "Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");
        Item focusSash = new Item(2L, "Focus Sash", "An item to be held by a Pokémon. If it has full HP, the holder will endure one potential KO attack, leaving 1 HP");

        // Given the service with two items stored
        given(itemService.list()).willReturn(List.of(oranBerry, focusSash));

        // When listed the items
        List<Item> result = itemController.list();

        // Then return the expected array
        assertEquals(2, result.size());
        // With the expected items
        assertEquals(oranBerry, result.get(0));
        assertEquals(focusSash, result.get(1));

        // And the service must be called
        then(itemService).should().list();
    }

    @Test
    public void create_item_successfully() {
        CreateItemDTO creationDTO = new CreateItemDTO("Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");
        Item oranBerry = new Item(1L, "Oran Berry", "If held by a Pokémon, it heals the user by just 10 HP");

        // Given the service creating the Oran Berry successfully
        given(itemService.create(any())).willReturn(oranBerry);

        // When created the Oran Berry item
        Item result = itemController.create(creationDTO);

        // Then return the expected item
        assertEquals(oranBerry, result);

        // And the service must be called
        then(itemService).should().create(any());
    }
    
}
