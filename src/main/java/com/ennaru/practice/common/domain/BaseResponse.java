package com.ennaru.practice.common.domain;

import lombok.Builder;

@Builder
public class BaseResponse {

    private String result_code;
    private String result_message;

}
