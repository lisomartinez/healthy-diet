package com.github.lmartinez84.healthydiet.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import static com.github.lmartinez84.healthydiet.repositories.FoodObjectMother.*;

@Execution(ExecutionMode.CONCURRENT)
class SearchFoodRepositoryTest extends FoodRepositoryUnitTest {

    public static final String NOT_MATCH = "other";
    private static final String EMPTY = "";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        foodRepository.create(createPotato());
    }

    @Test
    void should_return_an_empty_flux_if_empty_value() {
        StepVerifier.create(foodRepository.search(EMPTY))
                    .verifyComplete();
    }

    @Test
    void should_return_an_empty_flux_if_not_match() {
        StepVerifier.create(foodRepository.search(NOT_MATCH))
                    .verifyComplete();
    }

    @Test
    void should_return_an_empty_flux_if_value_null() {
        StepVerifier.create(foodRepository.search(null))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_fully_matching_name() {
        StepVerifier.create(foodRepository.search(POTATO))
                    .assertNext(found -> assertFoodsAreEqual(createPotato(), found))
                    .verifyComplete();
    }

    @Test
    void should_retun_entities_partially_matchin_name() {
        StepVerifier.create(foodRepository.search(POT))
                    .assertNext(found -> assertFoodsAreEqual(createPotato(), found))
                    .verifyComplete();
    }
}
