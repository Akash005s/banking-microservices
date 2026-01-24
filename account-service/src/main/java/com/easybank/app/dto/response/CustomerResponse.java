package com.easybank.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerResponse {

    private Long customerId;
    private String fullName;
    private String email;
    private String mobile;
    private Boolean active;
    private List<AccountResponse> accounts;
}

