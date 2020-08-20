package com.github.lmartinez84.healthydiet.recipe;

import com.github.lmartinez84.healthydiet.recipe.ingredient.Ingredient;
import com.github.lmartinez84.healthydiet.recipe.step.Step;
import com.github.lmartinez84.healthydiet.user.domain.User;

import java.util.List;
import java.util.Set;

public class CopiedRecipe extends Recipe {

    private final Recipe original;

    public CopiedRecipe(String name,
                        User author,
                        Set<User> collaborators,
                        int calories,
                        Set<Ingredient> ingredients,
                        List<Step> steps,
                        List<Recipe> subRecipes,
                        Recipe original
    ) {
        super(name, author, collaborators, calories, ingredients, steps, subRecipes);
        this.original = original;
    }

    public static CopiedRecipe from(Recipe recipe, User author) {
        return new CopiedRecipe(new String(recipe.name),
                                author,
                                Set.of(),
                                recipe.calories,
                                recipe.copyOfIngredients(),
                                recipe.copyOfSteps(),
                                recipe.copyOfSubRecipes(),
                                recipe);
    }

    @Override
    public Recipe original() {
        return this.original;
    }
}
