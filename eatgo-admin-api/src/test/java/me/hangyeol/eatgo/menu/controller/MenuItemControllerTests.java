package me.hangyeol.eatgo.menu.controller;

import me.hangyeol.eatgo.menu.MenuItem;
import me.hangyeol.eatgo.menu.service.MenuItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuItemController.class)
class MenuItemControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;

    private List<MenuItem> initMockMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        return menuItems;
    }

    @Test
    @DisplayName("레스토랑에 속한 메뉴 가져오기")
    public void list() throws Exception {
        List<MenuItem> menuItems = initMockMenuItems();

        given(menuItemService.getMenuItems(1L)).willReturn(menuItems);

        mvc.perform(get("/restaurants/1/menuitems"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Kimchi")));
    }

    @Test
    @DisplayName("메뉴아이템 업데이트")
    public void bulkUpdate() throws Exception {
        mvc.perform(patch("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(1L), any());
    }

}
