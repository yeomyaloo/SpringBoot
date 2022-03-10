package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {


    @Override
    public ModelView process(Map<String, String> paramMap) {
        //논리적 이름 넣어주기 물리적XXXXX(주소 전부 넣는 것을 의미)
        return new ModelView("new-form");
    }
}
