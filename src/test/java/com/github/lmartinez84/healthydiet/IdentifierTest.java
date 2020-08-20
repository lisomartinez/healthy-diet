package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.recipe.RecipeId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class IdentifierTest {

    @Test
    void id_are_equals_if_its_values_are_equals() {
        RecipeId anIdentifier = RecipeId.of("unId");
        assertThat(anIdentifier)
                .isEqualTo(anIdentifier)
                .isEqualTo(RecipeId.of("unId"));
    }

    @Test
    void id_are_not_equals_if_its_values_are_not_equals() {
        RecipeId anIdentifier = RecipeId.of("unId");
        assertThat(anIdentifier).isNotEqualTo(RecipeId.of("otroId"));
    }

    @Test
    void id_returns_its_value_as_string() {
        String idValue = "unId";
        RecipeId anIdentifier = RecipeId.of(idValue);
        assertThat(anIdentifier.asString()).isEqualTo(idValue);
    }

    @Test
    void nulls_ids_have_empty_as_its_values() {
        assertThat(RecipeId.nullId().asString()).isEqualTo("EMPTY");
    }
}