package com.example.demo.controller;


import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@Builder
public class TodoController {

    //빈을 알아서 찾고 그 빈을 이 인스턴스 멤버 변수로 연결하라는 뜻이다.
    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = service.testService();//테스트 서비스 사용
        List<String> list = new ArrayList<>();
        list.add(str);

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);

    }
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try {
            String temporaryUserId = "temporary-user"; // 임시 유저 아이디

            //1 TOdoEntity 변환
            TodoEntity entity = TodoDTO.toEntity(dto);
            //2. id를 null로 초기화 해준다. 생성 당시에 id가 없어야 하기 때문이다.
            entity.setId(null);
            //3.임시 사용자 아이디를 설정해 준다. 지금은 당장 인가가 필요 없는 부분이기에 추후에 수정해줄 예정
            entity.setUserId(temporaryUserId);
            //4. 서비스를 사용해 Todo 엔티티를 생성해준다.
            List<TodoEntity> entities = service.create(entity);
            //5. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            //6. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            //7. ResponseDTO를 리턴
            return ResponseEntity.ok().body(response);

        }catch (Exception e){
            //8. 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴해준다.
            String errorMessage = e.getMessage();
            ResponseDTO<TodoDTO> response =  ResponseDTO.<TodoDTO>builder().error(errorMessage).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "temporary-user"; // 임시 유저 아이디

        //1. 서비스 메서드의 retrieve() 메서드를 사용해 Todo 리스트를 가져온다.
        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        //2. 자바 스트림을 사용해 리턴된 엔티티 리스트를 TodoDTD 리스트로 변환한다.
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        //3. 뱐환된 TodoDTO 리스트를 이용해서 ResponseDTO를 초기화한다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        //4. ResponseDTO 리턴
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
        String temporaryUserId = "temporary-user"; // 임시 유저 아이디
        //1. dto를 entity로 변환
        TodoEntity entity = TodoDTO.toEntity(dto);

        //2. id를 임시 유저 아이디로 초기화한다. 여기 역시 수정할 예정
        entity.setUserId(temporaryUserId);

        //3. 서비스를 이용해 entity를 업데이트한다.
        List<TodoEntity> entities = service.update(entity);

        //4. 자바 스트림을 사용해서 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        //5. 변환된 TodoDTO 리스트를 이용해서 ResponseDTO를 초기화 한다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
        try{
            String temporaryUserId = "temporary-user"; // 임시 유저 아이디

            //1. TodoEntity 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            //2. 임시 사용자 아이디 설정, 이 부분도 수정 예정 인증, 인가가 들어오면 바꿀 부분
            entity.setUserId(temporaryUserId);

            //3. 서비스를 이용해 entity 삭제
            List<TodoEntity> entities = service.delete(entity);

            //4. 자바 스트림을 사용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            //5.변환된 TodoDTO 리스트를 이용하여 ResponseDTO 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            //6. ResponseDTO 리턴
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String errorMessage = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(errorMessage).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
