package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;


    public String testService(){

        //TodoEntity 생성
        TodoEntity entity = new TodoEntity().builder().title("My first todo item").build();

        //TodoEntity 저장
        repository.save(entity);

        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();


        return savedEntity.getTitle();
    }

    //POST 메서드를 사용한 create() 메서드
    public List<TodoEntity> create(final TodoEntity entity){
        validate(entity);
        repository.save(entity);

        log.info("Entity Id: {} is saved.",entity.getId());

        return repository.findByUserId(entity.getUserId());

    }

    private void validate(TodoEntity entity) {
        //검증(Validations)
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }

    //GET 메서드를 사용한 retrieve 메서드
    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    //UPDATE 메서드를 사용한 update() 메서드
    public List<TodoEntity> update(final TodoEntity entity){
        //1. 저장할 엔티티가 유효한지 확인해준다.
        validate(entity);

        //2. 넘겨받은 엔티티 id를 사용해서 TodoEntity를 가져온다. 존재하지 않는 경우라면 업데이트 할수 없기 때문이다.
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        if(original.isPresent()){
            //3. 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
            final TodoEntity todo = original.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        }
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        //1. 저장할 엔티티 유효성 검사를 한다
        validate(entity);

        try {
            //2. 엔티티를 삭제한다.
            repository.delete(entity);
        } catch (Exception e){
            //3. 예외 발생 시 id와 exception을 로깅한다.
            log.error("error deleting entity", entity.getId(), e);

            //4. 컨트롤러로 예외를 보낸다. 데이터베이스 내부 로직을 캡슐화 하기 위해서는 e를 리턴하지 않고 새 예외 오브젝트를 리턴해준다.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        //5. 새 Todo 리스트를 가져온 뒤 리턴해준다.
        return retrieve(entity.getUserId());
    }
}
