package com.devdynamo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDTO {
    private String cartId;
    private Long userId;
    private List<CartItemDTO> items;
    private Double totalPrice;
}