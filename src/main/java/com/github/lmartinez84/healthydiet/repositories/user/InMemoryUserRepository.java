package com.github.lmartinez84.healthydiet.repositories.user;

import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.UserId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InMemoryUserRepository implements UserRepository {
    private Map<UserId, User> users;

    public InMemoryUserRepository() {
        users = new LinkedHashMap<>();
    }

    @Override
    public Mono<User> create(User entity) {
        entity.id(UserId.of(UUID.randomUUID().toString()));
        users.put(entity.id(), entity);
        return Mono.just(entity);
    }

    @Override
    public Mono<Void> delete(User entity) {
        User removed = users.remove(entity.id());
        if (removed == null) {
            return Mono.error(new UserNotExistsException(entity.id()));
        }
        return Mono.empty();
    }

    @Override
    public Mono<User> update(User entity) {
        User updated = users.computeIfPresent(entity.id(), (userId, user) -> entity);
        if (updated == null) {
            return Mono.error(new UserNotExistsException(entity.id()));
        }
        return Mono.just(updated);
    }

    @Override
    public Mono<User> getById(UserId identifier) {
        User found = users.get(identifier);
        if (found == null) {
            return Mono.error(new UserNotExistsException(identifier));
        }
        return Mono.just(found);
    }

    @Override
    public Flux<User> search(String value) {
        if (isValueInvalid(value)) {
            return Flux.empty();
        }
        Stream<User> usersMatchingValue = users.values().stream()
                                               .filter(userFieldsMatcher(value));

        return Flux.fromStream(usersMatchingValue);
    }

    protected boolean isValueInvalid(String value) {
        return value == null || value.isBlank();
    }

    private Predicate<User> userFieldsMatcher(String value) {
        Predicate<User> matchUsername = user -> user.username().startsWith(value);
        Predicate<User> matchFirstName = user -> user.firstName().startsWith(value);
        Predicate<User> matchLastName = user -> user.lastName().startsWith(value);
        return matchUsername.or(matchFirstName).or(matchLastName);
    }
}
