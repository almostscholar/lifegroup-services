package org.rwchildress.lifegroupservices.meetings.members;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Family, Long> {
    Family findFirstByName(String name);
}
