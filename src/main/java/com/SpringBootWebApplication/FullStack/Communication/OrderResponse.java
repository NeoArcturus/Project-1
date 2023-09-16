package com.SpringBootWebApplication.FullStack.Communication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String orderMessage;
    private String orderStatus;
    private String transactionId;
}
