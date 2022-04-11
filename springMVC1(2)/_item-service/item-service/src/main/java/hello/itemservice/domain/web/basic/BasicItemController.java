package hello.itemservice.domain.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor

public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }


    //등록 view를 단순하게 보여주기위함이다.
    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }
    //위와 아래가 같은 URL을 사용할 때 HTTP 메서드로 기능을 분류할 수 있게 했다
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "/basic/item";
    }
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){

        itemRepository.save(item);

        //model.addAttribute("item",item); 자동 추가 해줌, 생략 가능

        return "/basic/item";
    }
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){


        // default 규칙 담긴 객체의 클래스명을 첫 글자만 소문자로 바꿔서 모델 에트리뷰트에 넣어줌
        itemRepository.save(item);

        //model.addAttribute("item",item); 자동추가 생략 가능

        return "/basic/item";
    }
    //@PostMapping("/add")
    //객체를 넘겨줄 땐 기본값으로 @ModelAttribute가 넘어감
    //기본(원시)타입의 경우엔 @RequestParam이 넘어가기 때문에 @ModelAttribute를 사용하려면 생략 불가!
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "/basic/item";
    }

    //Redirect로 새로고침으로 상품 중복 등록을 막는방법
    //@PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    /*사용자에게 저장이 잘 되었음을 알려주기 위해서 따로 화면 구성 없이
    *RedirectAttribute를 사용해서 이를 표현하는 방법법*/
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.updateItem(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    //테스트용 데이터를 추가한 것임.
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
