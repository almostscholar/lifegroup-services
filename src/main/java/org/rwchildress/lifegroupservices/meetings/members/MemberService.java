package org.rwchildress.lifegroupservices.meetings.members;

import java.util.List;

public interface MemberService {
    List<Family> findAllFamilies();
    Long save(FamilyDto familyDto);
}
