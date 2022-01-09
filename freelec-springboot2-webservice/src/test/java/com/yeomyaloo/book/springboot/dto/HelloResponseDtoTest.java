package com.yeomyaloo.book.springboot.dto;

import com.yeomyaloo.book.springboot.web.HelloController;
import com.yeomyaloo.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import lombok.Getter;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);

    }
}
