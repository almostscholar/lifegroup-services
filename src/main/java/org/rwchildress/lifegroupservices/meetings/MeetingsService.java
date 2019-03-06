package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.meetings.menus.MenuItemDto;

public interface MeetingsService {

    Meeting findCurrentMeeting();
    Long saveMenuItemForCurrentMeeting(MenuItemDto menuItemDto);
}
