package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;


//ip port 객체 생성
@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        log.info("convert source = {}", source);

        //"127.0.0.1:8080"
        String[] split = source.split(":");
        // ip 주소를 :(port 번호 기준) 기준으로 나눈다.
        String ip = split[0];
        int port = Integer.parseInt(split[1]);
        return new IpPort(ip,port);
    }

}
