package org.rwchildress.lifegroupservices.meetings.menus;

import org.rwchildress.lifegroupservices.meetings.Meeting;
import org.rwchildress.lifegroupservices.meetings.members.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public Long save(MenuItemDto menuItemDto, Family family, Meeting currentMeeting) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setFamily(family);
        menuItem.setMeeting(currentMeeting);
        return menuItemRepository.save(menuItem).getId();
    }

    @Override
    public void delete(Long menuItemId) {
        menuItemRepository.findById(menuItemId).ifPresent(menuItemRepository::delete);
    }

}
