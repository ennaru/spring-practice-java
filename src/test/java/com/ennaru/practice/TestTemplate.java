package com.ennaru.practice;

import com.ennaru.practice.template.TemplateLogic;
import com.ennaru.practice.template.TemplateProcess;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestTemplate {

    @Test
    @DisplayName("인터페이스를 파라미터로 받아 수행하는 템플릿로직")
    public void test() {

        TemplateProcess templateProcess = new TemplateProcess();

        templateProcess.setTemplateLogic(new TemplateLogic() {
            @Override
            public void valid() {
                log.info("templateLogic 안에서 실행하는 valid()");
                log.info("templateLogic 안에서 실행하는 valid()");
            }
        });

        templateProcess.execute();
        log.info("templateProcess 종료");
    }


}
