package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    static class TestBean{

        //required - 기본값 true
        //의존관계가 없이도 작동하게 하기 위해서 수정자 자체를 호출하지 않게 함.
        @Autowired(required = false)
        public void setNoBean1(Member member1){
            System.out.println("member1 = " + member1);
        }

        //호출은 되는데 대신 null로 들어온다.
        @Autowired
        public void setNoBean2(@Nullable Member member2){
            System.out.println("member2 = " + member2);
        }

        //java8에서 제공하는 optional이라는 기능 자동주입 대상이 없으면 Optional.empty가 됨
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
