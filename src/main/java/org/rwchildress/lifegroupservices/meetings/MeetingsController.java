package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.meetings.members.FamilyDto;
import org.rwchildress.lifegroupservices.meetings.members.MemberService;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItemDto;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/lifegroup")
@CrossOrigin(origins = { "http://localhost:4200", "https://rwchildress.org" })
public class MeetingsController {

    private static final Logger log = LoggerFactory.getLogger(MeetingsController.class);

    private MemberService memberService;
    private MeetingsService meetingsService;
    private MenuItemService menuItemService;

    @Autowired
    public MeetingsController(MemberService memberService,
                              MeetingsService meetingsService,
                              MenuItemService menuItemService) {
        this.memberService = memberService;
        this.meetingsService = meetingsService;
        this.menuItemService = menuItemService;
    }

    @GetMapping(path = "/families")
    public List<FamilyDto> getAllFamilies() {
        log.debug("In MeetingsController #getAllFamilies");
        return memberService.findAllFamilies();
    }

    @PutMapping(path = "/family")
    public Long saveFamily(@RequestBody FamilyDto familyDto) {
        log.debug("In MeetingsController #saveFamily");
        return memberService.save(familyDto);
    }

    @GetMapping(path = "/meeting")
    public MeetingDto getCurrentMeeting() {
        log.debug("In MeetingsController #getCurrentMeeting");
        return meetingsService.findCurrentMeetingDto();
    }

    @PatchMapping(path = "/meeting/{id}/complete")
    public void completeMeeting(@PathVariable Long id) {
        log.debug("In MeetingsController #completeMeeting");
        meetingsService.completeMeeting(id);
    }

    @PutMapping(path = "/menuitem")
    public Long saveMenuItem(@RequestBody MenuItemDto menuItemDto) {
        log.debug("In MeetingsController #saveMenuItem");
        return meetingsService.saveMenuItemForCurrentMeeting(menuItemDto);
    }

    @DeleteMapping(path = "/menuitem/{menuItemId}")
    public void deleteMenuItem(@PathVariable Long menuItemId) {
        log.debug("In MeetingsController #deleteMenuItem");
        menuItemService.delete(menuItemId);
    }

}
