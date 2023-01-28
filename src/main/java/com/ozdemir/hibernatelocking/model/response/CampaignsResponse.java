package com.ozdemir.hibernatelocking.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CampaignsResponse implements Serializable {
    private String name;
    private Integer codeCount;
}
