package com.github.lmartinez84.healthydiet.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import static com.github.lmartinez84.healthydiet.UserObjectMother.*;

@Execution(ExecutionMode.CONCURRENT)
class SearchUserRepositoryTest extends UserRepositoryUnitTest {

    public static final String ELMEJOR = "elmejor";
    public static final String PEORJUGADOR = "peorjugador";
    public static final String CAPITAN = "capitan";
    public static final String NOT_MATCH = "other";
    public static final String CAP = CAPITAN.substring(0, 3);
    private static final String EMPTY = "";
    private static final String JUL = JULIAN.substring(0, 3);
    private static final String MEN = MENDOZA.substring(0, 3);

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        userRepository.create(createJavierMascherano(CAPITAN));
        userRepository.create(createPedroMendoza(PEORJUGADOR));
        userRepository.create(createJulianFransino(ELMEJOR));
    }

    @Test
    void should_return_an_empty_flux_if_no_match() {
        StepVerifier.create(userRepository.search(NOT_MATCH))
                    .verifyComplete();
    }

    @Test
    void should_return_an_empty_flux_if_no_empty_value() {
        StepVerifier.create(userRepository.search(EMPTY))
                    .verifyComplete();
    }

    @Test
    void should_return_an_empty_flux_if_value_null() {
        StepVerifier.create(userRepository.search(null))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_if_fully_matching_of_username() {
        StepVerifier.create(userRepository.search(ELMEJOR))
                    .assertNext(found -> assertUsersAreEqual(createJulianFransino(ELMEJOR), found))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_if_partially_matching_of_username() {
        StepVerifier.create(userRepository.search(CAP))
                    .assertNext(found -> assertUsersAreEqual(createJavierMascherano(CAPITAN), found))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_if_fully_matching_of_firstname() {
        StepVerifier.create(userRepository.search(JULIAN))
                    .assertNext(found -> assertUsersAreEqual(createJulianFransino(ELMEJOR), found))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_if_partially_matching_of_firstname() {
        StepVerifier.create(userRepository.search(JUL))
                    .assertNext(found -> assertUsersAreEqual(createJulianFransino(ELMEJOR), found))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_if_fully_matching_of_lastname() {
        StepVerifier.create(userRepository.search(MENDOZA))
                    .assertNext(found -> assertUsersAreEqual(createPedroMendoza(PEORJUGADOR), found))
                    .verifyComplete();
    }

    @Test
    void should_return_entities_if_partially_matching_of_lastname() {
        StepVerifier.create(userRepository.search(MEN))
                    .assertNext(found -> assertUsersAreEqual(createPedroMendoza(PEORJUGADOR), found))
                    .verifyComplete();
    }

}
