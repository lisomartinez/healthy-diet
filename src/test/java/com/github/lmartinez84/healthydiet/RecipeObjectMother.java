package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.*;
import com.github.lmartinez84.healthydiet.domain.recipe.Recipe;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;

import java.util.List;
import java.util.Set;

import static com.github.lmartinez84.healthydiet.domain.recipe.RecipeBuilder.aRecipe;

class RecipeObjectMother {
    static Recipe createARecipeWithAuthor(User itsAuthor, Set<User> collaborators) {
        return aRecipe()
                .name("Fried Chicken")
                .author(itsAuthor)
                .collaborators(collaborators)
                .ingredients(Set.of(createRandomIngredient()))
                .steps(List.of(Step.of("aStep")))
                .calories(10)
                .build();
    }

    static User createUser(String username) {
        return UserObjectMother.createRandomUser();
    }

    static Ingredient createRandomIngredient() {
        return new Ingredient(createRandomFood(),
                              NumericQuantity.of(1.5, Unit.KILOGRAMS));
    }

    private static Food createRandomFood() {
        return new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED,
                        Set.of());
    }

    static Recipe createRecipeWithoutSteps() {
        return aRecipe()
                .name("Fried Chicken")
                .author(createUser("jp"))
                .collaborators(Set.of())
                .ingredients(Set.of(createRandomIngredient()))
                .steps(List.of())
                .calories(10)
                .build();
    }
}
