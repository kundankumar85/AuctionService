package com.cars.auction.controller;

import com.cars.auction.dto.ItemDTO;
import com.cars.auction.entity.Item;
import com.cars.auction.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
@AutoConfigureJsonTesters
class ItemControllerTest {

    @MockBean
    private ItemService itemService;

    @Autowired
    private JacksonTester<ItemDTO> requestJacksonTester;

    @Autowired
    private JacksonTester<Item> resutlJsonTester;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        Item item = new Item("A1","BMW");
        ItemDTO itemDTO = new ItemDTO("A1","BMW");
        BDDMockito.given(itemService.create(eq(itemDTO))).willReturn(item);
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders.post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJacksonTester.write(itemDTO).getJson())
        ).andReturn().getResponse();

        then(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(mockHttpServletResponse.getContentAsString()).isEqualTo(resutlJsonTester.write(item).getJson());

    }
}