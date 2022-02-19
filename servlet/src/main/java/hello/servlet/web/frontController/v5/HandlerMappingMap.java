package hello.servlet.web.frontController.v5;

import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class HandlerMappingMap {
    private final Map<String, Object> handleMappingMap = new HashMap<>();

    public HandlerMappingMap(String version) {
        if (version.contains("3")) {
            handleMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
            handleMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
            handleMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        } else if (version.contains("4")) {
            handleMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
            handleMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
            handleMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        }
    }
}
