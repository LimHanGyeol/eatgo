package me.hangyeol.eatgo.menu.service;

import me.hangyeol.eatgo.menu.MenuItem;
import me.hangyeol.eatgo.menu.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(Long restaurantId) {
        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            update(restaurantId, menuItem);
        }
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if (menuItem.isDestroy()) {
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }

        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }
}
