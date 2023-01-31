package com.ozdemir.hibernatelocking.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {

    private String name;
    private String email;
    private String address;
}
