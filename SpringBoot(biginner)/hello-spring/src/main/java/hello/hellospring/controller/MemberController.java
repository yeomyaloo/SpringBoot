package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller //컴포넌트 스캔
public class MemberController {
    private final MemberService memberService;

    @Autowired //연관관계
    public MemberController(MemberService memberService){

        this.memberService = memberService;
    }

    /* 회원 등록관리 */
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; //여기로 이동
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    /* 회원 목록 */
    @GetMapping("/members")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        //list 자체를 넘겨주는 것.
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
