package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    //기본 요청으로 HTTP 메서드 모두 허용하는 경우다.
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("hellobasic");
        return "ok";
    }

    //특정 HTTP메서드 요청에만 허용하는 경우
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mapping-get-v1");
        return "ok";
    }

    //특정 HTTP 메서드 요청 허용의 축약버전
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }

    //최근 HTTP API는 리소스 경로에 식별자 넣는 것을 선하고 아래가 그 경우다.
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId = {}",data);
        return "ok";
    }

    //pathVariable 다중 매핑(사용)한 경우우
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String userId, @PathVariable Long orderId){
        log.info("mappingPath userId = {}, orderId = {}",userId, orderId);
        return "ok";
    }

    //특정 파라미터 조건 매핑(쿼리 파라미터를 조건 매핑할 수 있다) 잘 사용 X
    //파라미터의 mode가 debug라고 나와야 한다.
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    //특정헤더 조건 추가 매핑한 경우
    @GetMapping(value = "mapping-header", headers = "mode = debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    //미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume
    //consumes = MediaType.APPLICATION_JSON_VALUE로 사용 가능
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    //미디어 타입 조건 매핑 - HTTP요청 Accept, produce
    //produces = MediaType.TEXT_HTML_VALUE로 사용 가능
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
