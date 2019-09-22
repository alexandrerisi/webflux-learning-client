package com.risi.learningreactiveclient.webfluxlearningclient.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemClient {

    private String id;
    private String description;
    private double price;
}
