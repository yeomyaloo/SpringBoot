package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class MemberServiceTest {

    /*테스트의 경우엔 한글로 적어도 됨
    왜냐하면 영어권 사람과 일하는 것이 아니라면 직관적인
    이해를 위해 한국어를 사용하고 실제 build에 사용되지
    않기 때문에 한국어를 사용해도 됨*/
    MemberService memberService;
    MemoryMemberRepository memberRepository;


    //각 테스트 실행전에 수행됨
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach //테스트케이스 실행되고 끝날 때 마다 repository 저장소를 지움 --> 순서와 상관 없이 테스트 케이스가 진행되도록 함.
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given - 어떤 데이터를
        Member member = new Member();
        member.setName("spring");


        //when - 어떤 것을 검증하는지
        Long saveId = memberService.join(member);


        //then - 나와야 하는 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        //Shift +  F6 => rename
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*      try {
            memberService.join(member2);
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}