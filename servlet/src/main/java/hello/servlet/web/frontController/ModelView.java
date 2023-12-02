package hello.servlet.web.frontController;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    private String viewName;//뷰의 논리적 이름
    private Map<String, Object> model = new HashMap<>();//원하는 데이터를 넣어두고 view-template에서 사용할 수 있도록 해줌

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
