package com.redcutlery.thingder.api.matching.controller;

import com.redcutlery.thingder.api.matching.dto.findList.MemberListResponse;
import com.redcutlery.thingder.api.matching.dto.pick.PickRequest;
import com.redcutlery.thingder.api.matching.dto.pick.PickResponse;
import com.redcutlery.thingder.api.matching.transaction.MatchingTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingTransaction matchingTransaction;

    @GetMapping("/")
    public MemberListResponse findList() {
        return matchingTransaction.findList();
    }

    @PostMapping("/pick")
    public PickResponse pick(@RequestBody PickRequest pickRequest) {
        return matchingTransaction.pick(pickRequest);
    }
}
