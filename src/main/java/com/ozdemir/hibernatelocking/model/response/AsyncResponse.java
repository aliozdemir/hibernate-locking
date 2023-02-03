package com.ozdemir.hibernatelocking.model.response;

import com.ozdemir.hibernatelocking.model.entity.Campaign;
import com.ozdemir.hibernatelocking.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AsyncResponse {
    private List<UserResponse> users;
    private List<Campaign> campaigns;

}
