package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.meetings.members.Family;
import org.rwchildress.lifegroupservices.meetings.members.MemberService;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItem;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItemDto;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MeetingsServiceImpl implements MeetingsService {

    private MenuItemService menuItemService;
    private MeetingsRepository meetingsRepository;
    private MemberService memberService;

    @Autowired
    public MeetingsServiceImpl(MenuItemService menuItemService,
                               MeetingsRepository meetingsRepository,
                               MemberService memberService) {
        this.menuItemService = menuItemService;
        this.meetingsRepository = meetingsRepository;
        this.memberService = memberService;
    }

    @Override
    public Meeting findCurrentMeeting() {
        Meeting currentMeeting = meetingsRepository.findFirstByIsCompleteIsFalseOrderByMeetingDateDesc();

        if (currentMeeting != null) {
            return currentMeeting;
        }

        currentMeeting = new Meeting();
        currentMeeting.setLocationName("The Sander's Residence");
        currentMeeting.setMeetingDate(LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
        currentMeeting.setComplete(false);
        return meetingsRepository.save(currentMeeting);
    }

    @Override
    @Transactional
    public MeetingDto findCurrentMeetingDto() {
        Meeting currentMeeting = findCurrentMeeting();
        return convertToDto(currentMeeting);
    }

    @Override
    @Transactional
    public Long saveMenuItemForCurrentMeeting(MenuItemDto menuItemDto) {
        String familyName = menuItemDto.getFamilyName();
        Family family = memberService.findFamilyByName(familyName);

        if (family == null) {
            return 0L;
        }

        Meeting currentMeeting = findCurrentMeeting();
        return menuItemService.save(menuItemDto, family, currentMeeting);
    }

    private MeetingDto convertToDto(Meeting meeting) {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setLocationName(meeting.getLocationName());
        meetingDto.setMeetingDate(meeting.getMeetingDate());
        List<MenuItemDto> menuItemDtos = meeting.getMenuItems().stream()
                .map(this::convertToMenuItemDto)
                .collect(toList());
        meetingDto.setMenuItemDtos(menuItemDtos);
        meetingDto.setComplete(meeting.isComplete());
        return meetingDto;
    }

    private MenuItemDto convertToMenuItemDto(MenuItem menuItem) {
        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setName(menuItem.getName());
        menuItemDto.setFamilyName(menuItem.getFamily().getName());
        return menuItemDto;
    }

}
