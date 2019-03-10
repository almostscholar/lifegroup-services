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
        log.debug("in MemberServiceImpl #findAllFamilies");
        List<Family> families = new ArrayList<>();
        memberRepository.findAll().forEach(families::add);
        log.debug("returning from memberRepository");
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
        log.debug("in MemberServiceImpl #findAllFamilies");
        Family family = new Family();
        family.setName(familyDto.getName());
        log.debug("returning from memberRepository");
        return memberRepository.save(family).getId();
    }

    private FamilyDto convertToDto(Family family) {
        FamilyDto familyDto = new FamilyDto();
        familyDto.setName(family.getName());
        return familyDto;
    }
}
