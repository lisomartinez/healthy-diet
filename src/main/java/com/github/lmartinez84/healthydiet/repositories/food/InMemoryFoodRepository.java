package com.github.lmartinez84.healthydiet.repositories.food;

import com.github.lmartinez84.healthydiet.domain.food.Food;
import com.github.lmartinez84.healthydiet.domain.food.FoodId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InMemoryFoodRepository implements FoodRepository {
    private Map<FoodId, Food> foods;

    public InMemoryFoodRepository() {
        foods = new LinkedHashMap<>();
    }

    @Override
    public Mono<Food> create(Food entity) {
        entity.id(FoodId.of(UUID.randomUUID().toString()));
        foods.putIfAbsent(entity.id(), entity);
        return Mono.just(entity);
    }

    @Override
    public Mono<Void> delete(Food entity) {
        Food deleted = foods.remove(entity.id());
        if (deleted == null) {
            return Mono.error(new FoodNotExistException(entity.id()));
        }
        return Mono.empty();
    }

    @Override
    public Mono<Food> update(Food entity) {
        Food actualizado = foods.computeIfPresent(entity.id(), (foodId, food) -> food);
        if (actualizado == null) {
            return Mono.error(new FoodNotExistException(entity.id()));
        }
        return Mono.just(actualizado);
    }

    @Override
    public Mono<Food> getById(FoodId identifier) {
        return Mono.justOrEmpty(foods.get(identifier));
    }

    @Override
    public Flux<Food> search(String value) {
        if (isValueInvalid(value)) {
            return Flux.empty();
        }
        Stream<Food> foodsMatchingName = foods.values()
                                              .stream()
                                              .filter(foodMatchingCriteria(value));

        return Flux.fromStream(foodsMatchingName);
    }

    private Predicate<Food> foodMatchingCriteria(String value) {
        return food -> food.name().startsWith(value);
    }

    private boolean isValueInvalid(String value) {
        return value == null || value.isBlank();
    }
}
