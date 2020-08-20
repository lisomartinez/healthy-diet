package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.*;
import com.github.lmartinez84.healthydiet.domain.recipe.Recipe;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;
import java.util.Set;

import static com.github.lmartinez84.healthydiet.domain.recipe.RecipeBuilder.aRecipe;

@Execution(ExecutionMode.CONCURRENT)
public class RecipeObjectMother {
    public static final String FRIED = "fried";
    public static final String CHICKEN_FIRST_THREE_CHARS = "chi";
    public static final String EGGPLANT = "eggplant";
    public static final String EGGPLANT_TOMATO_BAKE = "eggplant tomato bake";
    public static final String TOMATO = "tomato";
    public static final String FRIED_CHICKEN = "fried chicken";
    public static final String CHICKEN_PARMESAN = "chicken parmesan";
    public static final String CHICKEN = "chicken";

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

    public static Recipe createRandomRecipe() {
        return createARecipeWithAuthor(UserObjectMother.createRandomUser(), Set.of());
    }


    public static Recipe createChickeParmesan(User user) {
        return aRecipe()
                .name(CHICKEN_PARMESAN)
                .author(user)
                .calories(2000)
                .collaborators(Set.of())
                .ingredients(Set.of(new Ingredient(
                        new Food(CHICKEN, FoodGroup.MEAT_FISH_EGGS, Set.of(FoodInadequacy.VEGAN)),
                        NumericQuantity.of(1.4, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("primer paso")))
                .subRecipes(List.of())
                .build();
    }

    public static Recipe createEggplantTomatoBake(User user) {
        return aRecipe()
                .name(EGGPLANT_TOMATO_BAKE)
                .author(user)
                .calories(100)
                .collaborators(Set.of())
                .ingredients(Set.of(
                        new Ingredient(new Food(EGGPLANT, FoodGroup.VEGETABLES_FRUITS_SEED,
                                                Set.of()),
                                       NumericQuantity.of(1.4, Unit.KILOGRAMS)),
                        new Ingredient(new Food(TOMATO, FoodGroup.VEGETABLES_FRUITS_SEED,
                                                Set.of()),
                                       NumericQuantity.of(1, Unit.UNIT)))
                )
                .steps(List.of(Step.of("primer paso")))
                .subRecipes(List.of())
                .build();
    }

    public static Recipe createFriedChickenRecipe(User user) {
        return aRecipe()
                .name(FRIED_CHICKEN)
                .author(user)
                .calories(2000)
                .collaborators(Set.of())
                .ingredients(Set.of(new Ingredient(
                        new Food(CHICKEN, FoodGroup.MEAT_FISH_EGGS, Set.of(FoodInadequacy.VEGAN)),
                        NumericQuantity.of(1.4, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("primer paso")))
                .subRecipes(List.of())
                .build();
    }
}
