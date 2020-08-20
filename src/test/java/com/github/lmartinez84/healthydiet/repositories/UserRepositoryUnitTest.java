package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.user.adapters.InMemoryUserRepository;
import com.github.lmartinez84.healthydiet.user.domain.User;
import com.github.lmartinez84.healthydiet.user.domain.UserRepository;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class UserRepositoryUnitTest {
    protected UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
    }

    void assertUsersAreEqual(User user, User fromRepository) {
        assertThat(user.username()).isEqualTo(fromRepository.username());
        assertThat(user.firstName()).isEqualTo(fromRepository.firstName());
        assertThat(user.lastName()).isEqualTo(fromRepository.lastName());
        assertThat(user.lastName()).isEqualTo(fromRepository.lastName());
        assertThat(user.dateOfBirth()).isEqualTo(fromRepository.dateOfBirth());
        assertThat(user.age()).isEqualTo(fromRepository.age());
        assertThat(user.weight()).isCloseTo(fromRepository.weight(), Offset.offset(0.0000001));
        assertThat(user.height()).isCloseTo(fromRepository.height(), Offset.offset(0.0000001));
        assertThat(user.routine()).isEqualTo(fromRepository.routine());
        assertThat(user.favoritesFoods()).containsExactlyElementsOf(fromRepository.favoritesFoods());
        assertThat(user.dietaryRequirements()).containsExactlyElementsOf(fromRepository.dietaryRequirements());
    }
}
