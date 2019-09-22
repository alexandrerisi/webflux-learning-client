package com.risi.learningreactiveclient.webfluxlearningclient.controller;

import com.risi.learningreactiveclient.webfluxlearningclient.domain.ItemClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ItemClientController {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    @GetMapping("/client/retrieve")
    public Flux<ItemClient> getItemsUsingRetrieve() {
        return webClient.get().uri("/items").retrieve().bodyToFlux(ItemClient.class).log();
    }

    @GetMapping("/client/exchange")
    public Flux<ItemClient> getItemsUsingExchange() {
        return webClient.get().uri("/items")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(ItemClient.class)).log();
    }

    @GetMapping("/client/retrieve/{id}")
    public Mono<ItemClient> getItem(@PathVariable String id) {
        return webClient.get().uri("/items/{id}", id).retrieve().bodyToMono(ItemClient.class).log();
    }

    @PostMapping("/client/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ItemClient> createItem(@RequestBody ItemClient item) {
        return webClient.post().uri("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), ItemClient.class)
                .retrieve().bodyToMono(ItemClient.class).log();
    }

    @PutMapping("/client/update/{id}")
    public Mono<ItemClient> updateItem(@PathVariable String id, @RequestBody ItemClient item) {
        return webClient.put().uri("/items/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), ItemClient.class)
                .retrieve().bodyToMono(ItemClient.class).log();
    }

    @DeleteMapping("/client/delete/{id}")
    public Mono<Void> deleteItem(@PathVariable String id) {
        return webClient.delete().uri("/items/{id}", id)
                .retrieve().bodyToMono(Void.class).log();
    }
}
