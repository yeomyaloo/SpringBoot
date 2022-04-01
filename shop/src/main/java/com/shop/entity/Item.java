package com.shop.entity;


import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 50)
    private String itemNm;

    @Column(name ="price",nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;//상품 상세 정보

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;//상품 판매 상태

    private LocalDateTime regTime;//등록 시간

    private LocalDateTime updateTime; //수정 시간

}
