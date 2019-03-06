package org.rwchildress.lifegroupservices.meetings.members;

import org.rwchildress.lifegroupservices.global.BaseEntity;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Family extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "family", fetch = FetchType.LAZY)
    private List<MenuItem> menuItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
