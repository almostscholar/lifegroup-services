package org.rwchildress.lifegroupservices.meetings.menus;

import org.rwchildress.lifegroupservices.meetings.Meeting;
import org.rwchildress.lifegroupservices.meetings.members.Family;

public interface MenuItemService {
    Long save(MenuItemDto menuItemDto, Family family, Meeting currentMeeting);
    void delete(Long menuItemId);
}
