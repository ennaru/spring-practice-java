package com.ennaru.practice.common.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse {

    private String result_code;
    private String result_message;

}
