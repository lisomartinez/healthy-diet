package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import java.util.UUID;

import static com.github.lmartinez84.healthydiet.UserObjectMother.createPedroMendoza;

@Execution(ExecutionMode.CONCURRENT)
class CrudUserRepositoryTest extends UserRepositoryUnitTest {

    private User user;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        user = createPedroMendoza("elmejor");
        StepVerifier.create(userRepository.create(user))
                    .assertNext(created -> assertUsersAreEqual(createPedroMendoza("elmejor"), created))
                    .verifyComplete();

    }

    @Test
    void update_should_update_recipe_in_repo() {
        user.firstName("Javier");
        StepVerifier.create(userRepository.update(user))
                    .assertNext(updated -> assertUsersAreEqual(user, updated))
                    .verifyComplete();
    }

    @Test
    void update_should_throw_an_UserNotExistException() {
        user.id(UserId.of(UUID.randomUUID().toString()));
        StepVerifier.create(userRepository.update(user))
                    .expectError(UserNotExistsException.class)
                    .verify();
    }

    @Test
    void delete_should_delete_recipe_from_repo_if_exists() {
        StepVerifier.create(userRepository.delete(user))
                    .verifyComplete();
    }

    @Test
    void delete_should_throwRecipeNotExistException_if_not_present_in_repo() {
        user.id(UserId.of(UUID.randomUUID().toString()));
        StepVerifier.create(userRepository.delete(user))
                    .expectError(UserNotExistsException.class)
                    .verify();
    }

    @Test
    void getById_should_return_entity_if_present() {
        StepVerifier.create(userRepository.getById(user.id()))
                    .assertNext(updated -> assertUsersAreEqual(user, updated))
                    .verifyComplete();
    }

    @Test
    void getById_should_return_an_empty_mono_if_not_present() {
        user.id(UserId.of(UUID.randomUUID().toString()));
        StepVerifier.create(userRepository.getById(user.id()))
                    .expectError(UserNotExistsException.class)
                    .verify();
    }

}
