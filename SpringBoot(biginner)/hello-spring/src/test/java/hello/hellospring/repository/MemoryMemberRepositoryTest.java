package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest { //다른곳에서 쓸 일이 없어서 public 없어도 됨

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //테스트케이스 실행되고 끝날 때 마다 repository 저장소를 지움 --> 순서와 상관 없이 테스트 케이스가 진행되도록 함.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional은 get으로 바로 꺼낼 수 있다.
        assertThat(member).isEqualTo(result);

    }
    @Test
    void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        //shift + F6 누르면 rename가능.. (복붙 수정가능.)
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }
    @Test
    void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}
