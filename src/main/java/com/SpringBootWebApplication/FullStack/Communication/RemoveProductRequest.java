package com.SpringBootWebApplication.FullStack.Communication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveProductRequest {

    private long cartId;

    private String productName;
}
