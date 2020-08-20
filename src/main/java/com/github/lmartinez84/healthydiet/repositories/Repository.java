package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.Entity;
import com.github.lmartinez84.healthydiet.domain.Identifier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Repository<T extends Entity<K>, K extends Identifier> {
    Mono<T> create(T entity);

    Mono<Void> delete(T entity);

    Mono<T> update(T entity);

    Mono<T> getById(K identifier);

    Flux<T> search(String value);
}
