package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    //ctrl + D를 누르면 그대로 복사해줌 아주 편리하다 ~ 이말이야
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanType(){
        //이름을 빼고 타입으로만 조회가 가능. 그러나 같은 타입이 여러개일 때 문제가 발생한다.
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanName2(){
        //이름을 빼고 타입으로만 조회가 가능. 그러나 같은 타입이 여러개일 때 문제가 발생한다.
        MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //실패 테스트도 매우 중요 !
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanNameX(){
        //이름을 빼고 타입으로만 조회가 가능. 그러나 같은 타입이 여러개일 때 문제가 발생한다.
        //ac.getBean("XXX",MemberServiceImpl.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("XXX",MemberServiceImpl.class));

    }




}
