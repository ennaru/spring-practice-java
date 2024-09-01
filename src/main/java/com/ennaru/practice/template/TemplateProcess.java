package com.ennaru.practice.template;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
public class TemplateProcess {

    private TemplateLogic templateLogic;

    public void execute() {
        log.info("templateProcess execute 시작");
        templateLogic.valid();
        log.info("templateProcess execute 종료");
    }

}
