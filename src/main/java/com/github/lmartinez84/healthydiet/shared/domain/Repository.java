package com.github.lmartinez84.healthydiet.shared.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Repository<T extends Entity<K>, K extends Identifier> {
    Mono<T> create(T entity);

    Mono<Void> delete(T entity);

    Mono<T> update(T entity);

    Mono<T> getById(K identifier);

    Flux<T> search(String value);
}
