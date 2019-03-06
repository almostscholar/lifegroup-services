package org.rwchildress.lifegroupservices.meetings.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Family> findAllFamilies() {
        List<Family> families = new ArrayList<>();
        memberRepository.findAll().forEach(families::add);
        return families;
    }

    @Override
    public Long save(FamilyDto familyDto) {
        Family family = new Family();
        family.setName(familyDto.getName());
        return memberRepository.save(family).getId();
    }
}
