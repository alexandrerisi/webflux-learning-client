package com.risi.learningreactiveclient.webfluxlearningclient.cloud;

import com.risi.learningreactiveclient.webfluxlearningclient.domain.ItemClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "webflux-server")
public interface ReactiveProxy {

    @GetMapping("/items/")
    Flux<ItemClient> retrieveItems();

    @GetMapping("/items/test/")
    Flux<String> test();
}
