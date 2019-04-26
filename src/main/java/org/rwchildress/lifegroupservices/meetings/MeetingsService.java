package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.meetings.menus.MenuItemDto;

public interface MeetingsService {

    Meeting findCurrentMeeting();
    MeetingDto findCurrentMeetingDto();
    Long saveMenuItemForCurrentMeeting(MenuItemDto menuItemDto);
    void completeMeeting(Long id);
}
