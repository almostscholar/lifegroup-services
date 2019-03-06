package org.rwchildress.lifegroupservices.meetings.menus;

import org.rwchildress.lifegroupservices.meetings.members.FamilyDto;

public class MenuItemDto {
    private String name;
    private FamilyDto familyDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FamilyDto getFamilyDto() {
        return familyDto;
    }

    public void setFamilyDto(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }
}
