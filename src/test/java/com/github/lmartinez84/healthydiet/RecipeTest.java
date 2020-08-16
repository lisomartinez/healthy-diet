package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.recipe.Recipe;
import com.github.lmartinez84.healthydiet.user.User;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.FoodGroup;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.lmartinez84.healthydiet.recipe.RecipeBuilder.aRecipe;
import static org.assertj.core.api.Assertions.assertThat;

public class RecipeTest {

    @Test
    public void can_be_edited_by_its_author() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        Recipe recipe = RecipeObjectMother.createARecipeWithAuthor(itsAuthor, Set.of());

        assertThat(recipe.isEditableBy(itsAuthor)).isEqualTo(true);
    }

    @Test
    public void cannot_be_edited_by_a_user_thats_neither_its_author_nor_a_collaborator() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        User otherUser = RecipeObjectMother.createUser("otherUser");

        Recipe recipe = RecipeObjectMother.createARecipeWithAuthor(itsAuthor, Set.of());

        assertThat(recipe.isEditableBy(otherUser)).isEqualTo(false);
    }

    @Test
    public void can_be_edited_by_a_collaborator() {
        User itsAuthor = RecipeObjectMother.createUser("jp");
        User aCollaborator = RecipeObjectMother.createUser("otherUser");

        Recipe recipe = RecipeObjectMother.createARecipeWithAuthor(itsAuthor, Set.of(aCollaborator));
        assertThat(recipe.isEditableBy(aCollaborator)).isEqualTo(true);
    }

    @Test
    public void can_have_subrecipes() {
        Recipe subRecipe = aRecipe()
                .name("pollo con paas")
                .author(RecipeObjectMother.createUser("jp"))
                .calories(1000)
                .ingredients(Set.of(new Ingredient(
                        new Food("potatos", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                        NumericQuantity.of(1.5, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("pelar papas"), Step.of("hervir papas")))
                .build();

        Recipe recipe = aRecipe()
                .name("pollo con paas")
                .author(RecipeObjectMother.createUser("jp"))
                .calories(500)
                .ingredients(Set.of(new Ingredient(
                        new Food("pollo", FoodGroup.MEAT_FISH_EGGS, Set.of()),
                        NumericQuantity.of(1.5, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("cortar pollo"), Step.of("cocinar pollo")))
                .subRecipes(List.of(subRecipe))
                .build();
        List<Step> totalSteps = List.of(Step.of("cortar pollo"),
                                        Step.of("cocinar pollo"),
                                        Step.of("pelar papas"),
                                        Step.of("hervir papas"));
        assertThat(recipe.steps())
                .containsExactlyElementsOf(totalSteps);
    }

    @Test
    public void calories_are_the_sum_of_its_calories_and_subrecipes_calroies() {
        Recipe subRecipe = aRecipe()
                .name("pollo con paas")
                .author(RecipeObjectMother.createUser("jp"))
                .calories(1000)
                .ingredients(Set.of(new Ingredient(
                        new Food("potatos", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                        NumericQuantity.of(1.5, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("pelar papas"), Step.of("hervir papas")))
                .build();

        Recipe recipe = aRecipe()
                .name("pollo con paas")
                .author(RecipeObjectMother.createUser("jp"))
                .calories(500)
                .ingredients(Set.of(new Ingredient(
                        new Food("pollo", FoodGroup.MEAT_FISH_EGGS, Set.of()),
                        NumericQuantity.of(1.5, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("cortar pollo"), Step.of("cocinar pollo")))
                .subRecipes(List.of(subRecipe))
                .build();

        int totalCalories = 1500;
        assertThat(recipe.calories()).isEqualTo(totalCalories);
    }

    @Test
    public void inadequadecies_are_its_inadequacies_and_of_its_subrecipes() {
        Set<FoodInadequacy> subRecipeInadequancies = Set.of(FoodInadequacy.CELIAC, FoodInadequacy.HYPERTENSIVE);
        Recipe subRecipe = aRecipe()
                .name("pollo con paas")
                .author(RecipeObjectMother.createUser("jp"))
                .calories(1000)
                .ingredients(Set.of(new Ingredient(
                        new Food("potatos", FoodGroup.VEGETABLES_FRUITS_SEED,
                                 subRecipeInadequancies),
                        NumericQuantity.of(1.5, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("pelar papas"), Step.of("hervir papas")))
                .build();

        Set<FoodInadequacy> recipeInadquencies = Set.of(FoodInadequacy.VEGAN, FoodInadequacy.VEGETARIAN);
        Recipe recipe = aRecipe()
                .name("pollo con paas")
                .author(RecipeObjectMother.createUser("jp"))
                .calories(500)
                .ingredients(Set.of(new Ingredient(
                        new Food("pollo", FoodGroup.MEAT_FISH_EGGS,
                                 recipeInadquencies),
                        NumericQuantity.of(1.5, Unit.KILOGRAMS))))
                .steps(List.of(Step.of("cortar pollo"), Step.of("cocinar pollo")))
                .subRecipes(List.of(subRecipe))
                .build();
        Set<FoodInadequacy> totalInadequencies = Stream.concat(
                recipeInadquencies.stream(),
                subRecipeInadequancies.stream()).collect(Collectors.toSet());
        assertThat(recipe.inadequateFor()).isEqualTo(totalInadequencies);
    }
}
