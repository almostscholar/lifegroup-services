package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.meetings.menus.MenuItemDto;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingDto {

    private String locationName;
    private LocalDateTime meetingDate;
    private List<MenuItemDto> menuItems;
    private Boolean isComplete;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocalDateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDateTime meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<MenuItemDto> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDto> menuItems) {
        this.menuItems = menuItems;
    }

    public Boolean isComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
