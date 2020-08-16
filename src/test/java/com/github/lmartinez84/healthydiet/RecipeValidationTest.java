package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.RecipeWithoutStepsException;
import com.github.lmartinez84.healthydiet.domain.Step;
import com.github.lmartinez84.healthydiet.domain.recipe.RecipeBuilder;
import com.github.lmartinez84.healthydiet.domain.recipe.exceptions.InvalidCaloriesRecipeException;
import com.github.lmartinez84.healthydiet.domain.recipe.exceptions.InvalidNumberOfIngredientsRecipeException;
import com.github.lmartinez84.healthydiet.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RecipeValidationTest {
    @Test
    public void should_not_be_created_if_it_has_not_ingredients() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        assertThatExceptionOfType(InvalidNumberOfIngredientsRecipeException.class)
                .isThrownBy(() -> RecipeBuilder
                        .aRecipe()
                        .name("aRecipe")
                        .author(itsAuthor)
                        .collaborators(Set.of()).ingredients(Set.of())
                        .steps(List.of(Step.of("aStep")))
                        .build());
    }

    @Test
    public void should_not_be_created_without_steps() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        assertThatExceptionOfType(RecipeWithoutStepsException.class)
                .isThrownBy(() -> RecipeBuilder
                        .aRecipe()
                        .name("aRecipe")
                        .author(itsAuthor)
                        .ingredients(Set.of(RecipeObjectMother.createRandomIngredient()))
                        .collaborators(Set.of()).ingredients(Set.of())
                        .build());
    }

    @Test
    public void calories_below_10_should_not_be_allowed() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        assertThatExceptionOfType(InvalidCaloriesRecipeException.class)
                .isThrownBy(() -> RecipeBuilder
                        .aRecipe()
                        .name("aRecipe")
                        .author(itsAuthor)
                        .ingredients(Set.of(RecipeObjectMother.createRandomIngredient()))
                        .collaborators(Set.of()).ingredients(Set.of())
                        .steps(List.of(Step.of("aStep")))
                        .calories(9)
                        .build());
    }

    @Test
    public void calories_above_5000_should_not_be_allowed() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        assertThatExceptionOfType(InvalidCaloriesRecipeException.class)
                .isThrownBy(() -> RecipeBuilder
                        .aRecipe()
                        .name("aRecipe")
                        .author(itsAuthor)
                        .ingredients(Set.of(RecipeObjectMother.createRandomIngredient()))
                        .collaborators(Set.of()).ingredients(Set.of())
                        .steps(List.of(Step.of("aStep")))
                        .calories(5001)
                        .build());
    }
}
