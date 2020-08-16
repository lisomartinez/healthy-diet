package com.github.lmartinez84.healthydiet.domain.recipe;

import com.github.lmartinez84.healthydiet.domain.Ingredient;
import com.github.lmartinez84.healthydiet.domain.Step;
import com.github.lmartinez84.healthydiet.domain.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeBuilder {
    private final Set<User> collaborators = new HashSet<>();
    private final Set<Ingredient> ingredients = new HashSet<>();
    private String name;
    private User author;
    private List<Step> steps = new ArrayList<>();
    private int calories;
    private List<Recipe> subRecipes = new ArrayList<>();

    public static RecipeBuilder aRecipe() {
        return new RecipeBuilder();
    }

    public RecipeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RecipeBuilder author(User author) {
        this.author = author;
        return this;
    }

    public RecipeBuilder ingredients(Set<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
        return this;
    }

    public RecipeBuilder collaborators(Set<User> collaborators) {
        this.collaborators.addAll(collaborators);
        return this;
    }

    public RecipeBuilder calories(int calories) {
        this.calories = calories;
        return this;
    }

    public RecipeBuilder steps(List<Step> steps) {
        this.steps.addAll(steps);
        return this;
    }

    public RecipeBuilder subRecipes(List<Recipe> subRecipes) {
        this.subRecipes = subRecipes;
        return this;
    }

    public Recipe build() {
        return new Recipe(name, author, collaborators, calories, ingredients, steps, subRecipes);
    }
}
