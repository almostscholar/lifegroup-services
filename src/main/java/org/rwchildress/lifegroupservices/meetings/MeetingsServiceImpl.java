package org.rwchildress.lifegroupservices.meetings;

import org.rwchildress.lifegroupservices.meetings.members.Family;
import org.rwchildress.lifegroupservices.meetings.members.MemberService;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItem;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItemDto;
import org.rwchildress.lifegroupservices.meetings.menus.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class MeetingsServiceImpl implements MeetingsService {

    private static final Logger log = LoggerFactory.getLogger(MeetingsServiceImpl.class);

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

        log.info("No current meeting found. Creating new meeting!");
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
        if (familyName == null || familyName.isEmpty()) {
            familyName = "None";
        }
        Family family = memberService.findFamilyByName(familyName);

        if (family == null) {
            if (log.isWarnEnabled()) {
                log.warn(format("Attempted to save menu item for non-existent family name of %s", familyName));
            }
            return -1L;
        }

        Meeting currentMeeting = findCurrentMeeting();
        return menuItemService.save(menuItemDto, family, currentMeeting);
    }

    @Override
    public void completeMeeting(Long id) {
        Optional<Meeting> meeting = meetingsRepository.findById(id);

        if (meeting.isPresent()) {
            Meeting m = meeting.get();
            m.setComplete(true);
            meetingsRepository.save(m);
        } else {
            throw new MissingResourceException("no meeting found for id " + id, "Meeting", "meeting.id");
        }
    }

    private MeetingDto convertToDto(Meeting meeting) {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setId(meeting.getId());
        meetingDto.setLocationName(meeting.getLocationName());
        meetingDto.setMeetingDate(meeting.getMeetingDate());
        if (meeting.getMenuItems() == null) {
            meetingDto.setMenuItems(new ArrayList<>());
        } else {
            List<MenuItemDto> menuItemDtos = meeting.getMenuItems().stream()
                    .map(this::convertToMenuItemDto)
                    .collect(toList());
            meetingDto.setMenuItems(menuItemDtos);
        }
        meetingDto.setComplete(meeting.isComplete());
        return meetingDto;
    }

    private MenuItemDto convertToMenuItemDto(MenuItem menuItem) {
        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setId(menuItem.getId());
        menuItemDto.setName(menuItem.getName());
        menuItemDto.setFamilyName(menuItem.getFamily().getName());
        return menuItemDto;
    }

}
