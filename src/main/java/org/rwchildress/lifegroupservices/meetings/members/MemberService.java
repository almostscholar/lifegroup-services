package org.rwchildress.lifegroupservices.meetings.members;

import java.util.List;

public interface MemberService {
    List<FamilyDto> findAllFamilies();
    Family findFamilyByName(String name);
    Long save(FamilyDto familyDto);
}
