package com.yeomyaloo.book.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //이 클래스에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동으로 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity 값이 변경될 때 시간이 자동으로 저장된다.
    private LocalDateTime modifiedDate;

}
