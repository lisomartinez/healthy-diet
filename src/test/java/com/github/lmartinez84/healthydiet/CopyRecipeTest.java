package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.recipe.Recipe;
import com.github.lmartinez84.healthydiet.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CopyRecipeTest {

    private User originalAuthor;
    private User copyAuthor;
    private Recipe aRecipe;

    @BeforeEach
    void setUp() {
        originalAuthor = UserObjectMother.createRandomUser();
        copyAuthor = UserObjectMother.createRandomUser();
        aRecipe = RecipeObjectMother.createARecipeWithAuthor(originalAuthor, Set.of());
    }

    @Test
    void copied_has_author_that_has_copied() {
        assertThat(aRecipe.author()).isEqualTo(originalAuthor);
        Recipe copiedRecipe = aRecipe.copy(copyAuthor);
        assertThatRecipeDiffersOnlyInAuthor(aRecipe, copiedRecipe);
    }

    private void assertThatRecipeDiffersOnlyInAuthor(Recipe aRecipe, Recipe copiedRecipe) {
        assertThat(copiedRecipe.author()).isNotEqualTo(aRecipe.author());
        assertThat(copiedRecipe.name()).isEqualTo(aRecipe.name());
        assertThat(copiedRecipe.collaborators()).containsExactlyElementsOf(aRecipe.collaborators());
        assertThat(copiedRecipe.calories()).isEqualTo(aRecipe.calories());
        assertThat(copiedRecipe.ingredients()).containsExactlyElementsOf(aRecipe.ingredients());
        assertThat(copiedRecipe.steps()).containsExactlyElementsOf(aRecipe.steps());
    }

    @Test
    void copied_has_an_empty_collaborators() {
        addRandomCollaboratorToRecipe();

        Recipe copiedRecipe = aRecipe.copy(copyAuthor);

        assertThat(aRecipe.collaborators()).isNotEmpty();
        assertThat(copiedRecipe.collaborators()).isEmpty();
    }

    private void addRandomCollaboratorToRecipe() {
        User aCollaborator = UserObjectMother.createRandomUser();
        aRecipe.addCollaborator(aCollaborator);
    }

    @Test
    void copied_recipe_knows_its_original() {
        addRandomCollaboratorToRecipe();
        Recipe copiedRecipe = aRecipe.copy(copyAuthor);

        assertThat(copiedRecipe.original()).isEqualTo(aRecipe);
    }

    @Test
    void original_recipe_knows_its_own_original() {
        assertThat(aRecipe.original()).isEqualTo(aRecipe);
    }
}
