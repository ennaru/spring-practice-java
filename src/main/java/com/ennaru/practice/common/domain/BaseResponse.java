package com.ennaru.practice.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse {

    private String result_code;
    private String result_message;
    private Object result_data;

}
