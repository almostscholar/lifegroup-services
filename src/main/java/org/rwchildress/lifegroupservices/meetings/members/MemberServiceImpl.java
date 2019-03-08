package org.rwchildress.lifegroupservices.meetings.members;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public List<FamilyDto> findAllFamilies() {
        log.info("in MemberServiceImpl #findAllFamilies");
        List<Family> families = new ArrayList<>();
        log.info("before calling memberRepository");
        memberRepository.findAll().forEach(families::add);
        log.info("after calling memberRepository");
        return families.stream()
                .map(this::convertToDto)
                .collect(toList());
    }

    @Override
    public Family findFamilyByName(String name) {
        return memberRepository.findFirstByName(name);
    }

    @Override
    public Long save(FamilyDto familyDto) {
        log.info("in MemberServiceImpl #findAllFamilies");
        Family family = new Family();
        log.info("before calling memberRepository");
        family.setName(familyDto.getName());
        log.info("after calling memberRepository");
        return memberRepository.save(family).getId();
    }

    private FamilyDto convertToDto(Family family) {
        FamilyDto familyDto = new FamilyDto();
        familyDto.setName(family.getName());
        return familyDto;
    }
}
