package com.redcutlery.thingder.api.member.controller;

import com.redcutlery.thingder.api.member.dto.find.MemberResponse;
import com.redcutlery.thingder.api.member.transaction.MemberTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@Service
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberTransaction memberTransaction;

    @GetMapping("/{memberUid}")
    public MemberResponse find(@PathVariable UUID memberUid) {
        return memberTransaction.find(memberUid);
    }

    @DeleteMapping("/delete")
    public void delete(){
        memberTransaction.delete();
    }
}
