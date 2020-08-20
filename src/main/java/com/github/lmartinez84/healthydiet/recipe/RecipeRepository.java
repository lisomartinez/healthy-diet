package com.github.lmartinez84.healthydiet.recipe;

import com.github.lmartinez84.healthydiet.shared.domain.Repository;
import reactor.core.publisher.Flux;

import java.util.function.Predicate;

public interface RecipeRepository extends Repository<Recipe, RecipeId> {

    Flux<Recipe> findBy(Predicate<Recipe> filter);
}
