package org.rwchildress.lifegroupservices.meetings;

import org.springframework.data.repository.CrudRepository;

public interface MeetingsRepository extends CrudRepository<Meeting, Long> {

    Meeting findFirstByOrderByCreatedDateDesc();
}
