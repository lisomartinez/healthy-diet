package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.FoodObjectMother;
import com.github.lmartinez84.healthydiet.recipes.adapters.FoodNotExistException;
import com.github.lmartinez84.healthydiet.recipes.domain.food.Food;
import com.github.lmartinez84.healthydiet.recipes.domain.food.FoodId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import java.util.UUID;

@Execution(ExecutionMode.CONCURRENT)
class CrudFoodRepositoryTest extends FoodRepositoryUnitTest {

    private Food food;

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
        food = FoodObjectMother.createRandomFood();

        StepVerifier.create(foodRepository.create(food))
                    .assertNext(saved -> assertFoodsAreEqual(food, saved))
                    .verifyComplete();
    }

    @Test
    void update_should_update_recipe_in_repo() {
        food.name("potato");
        StepVerifier.create(foodRepository.update(food))
                    .assertNext(updated -> assertFoodsAreEqual(food, updated))
                    .verifyComplete();
    }

    @Test
    void update_should_throw_an_FoodNotExistException() {
        food.id(FoodId.of(UUID.randomUUID().toString()));
        StepVerifier.create(foodRepository.update(food))
                    .expectError(FoodNotExistException.class)
                    .verify();
    }

    @Test
    void delete_should_delete_recipe_from_repo_if_exists() {
        StepVerifier.create(foodRepository.delete(food))
                    .verifyComplete();
    }

    @Test
    void delete_should_throw_FoodNotExistException_if_not_present_in_repo() {
        food.id(FoodId.of(UUID.randomUUID().toString()));

        StepVerifier.create(foodRepository.delete(food))
                    .expectError(FoodNotExistException.class)
                    .verify();
    }

    @Test
    void getById_should_return_entity_if_present() {
        StepVerifier.create(foodRepository.getById(food.id()))
                    .assertNext(retrieved -> assertFoodsAreEqual(food, retrieved))
                    .verifyComplete();
    }

    @Test
    void getById_should_return_an_empty_mono_if_not_present() {
        FoodId notExistingId = FoodId.of(UUID.randomUUID().toString());

        StepVerifier.create(foodRepository.getById(notExistingId))
                    .verifyComplete();
    }
}
