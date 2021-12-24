package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller //컴포넌트 스캔
public class MemberController {
    private final MemberService memberService;

    @Autowired //연관관계
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
