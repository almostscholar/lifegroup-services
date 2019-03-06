package org.rwchildress.lifegroupservices.meetings.menus;

import org.rwchildress.lifegroupservices.global.BaseEntity;
import org.rwchildress.lifegroupservices.meetings.Meeting;
import org.rwchildress.lifegroupservices.meetings.members.Family;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MenuItem extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
