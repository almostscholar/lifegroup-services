package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.global.BaseEntity;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Meeting extends BaseEntity {

    @Column
    private String locationName;

    @Column
    private LocalDateTime meetingDate;

    @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;

    @Column
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Boolean isComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
