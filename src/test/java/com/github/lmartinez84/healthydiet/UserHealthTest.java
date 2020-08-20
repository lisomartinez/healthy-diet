package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class UserHealthTest {

    @Test
    void is_healthy_when_BMI_is_between_18_and_30_and_hasnt_preexisting_conditions() {
        User aHealthyUser = UserObjectMother.aRandomUserWith()
                                            .weight(91)
                                            .height(1.8)
                                            .dietaryRequirements(Set.of())
                                            .build();

        assertThat(aHealthyUser.isHealthy()).isTrue();
    }

    @Test
    void is_unhealthy_when_his_is_outside_normal_weight_() {
        User anUnhealthyUser = UserObjectMother.aRandomUserWith()
                                               .weight(150)
                                               .height(1.8)
                                               .dietaryRequirements(Set.of())
                                               .build();

        assertThat(anUnhealthyUser.isHealthy()).isFalse();
    }


}
