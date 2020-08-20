package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.user.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.lmartinez84.healthydiet.domain.user.UserBuilder.aUser;

@Execution(ExecutionMode.CONCURRENT)
public class UserObjectMother {

    public static final String JULIAN = "Julian";
    public static final String FRANSINO = "Fransino";
    public static final String JAVIER = "Javier";
    public static final String MASCHERANO = "Mascherano";
    public static final String MENDOZA = "MENDOZA";
    public static final String PEDRO = "Pedro";

    public static User createUserWithWeightAndHeight(double weight, double height) {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(weight)
                .height(height)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
    }

    public static UserBuilder aRandomUserWith() {
        return aUser()
                .username("jp")
                .firstName("Juan Pablo")
                .lastName("Perez")
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11));
    }


    public static User userWithNullFirstName() {
        return aUser()
                .firstName(null)
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(180)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    public static User userWithEmptyFirstName() {
        return aUser()
                .firstName("")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(180)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    public static User userWithZeroWeight() {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(0)
                .height(180)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    public static User userWithZeroHeight() {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(30)
                .height(0)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    public static User userWithoutBirthDate() {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(30)
                .dateOfBirth(null)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .build();
    }

    public static User createRandomUser() {
        return UserBuilder.aUser()
                          .firstName("Juan Pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .build();
    }

    public static User userWithoutBirthDateInThePast() {
        return UserBuilder.aUser()
                          .firstName("Juan Pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.now())
                          .routine(Routine.ACTIVE)
                          .build();
    }

    public static User userWithoutRoutine() {
        return UserBuilder.aUser()
                          .firstName("Juan Pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .build();
    }

    public static User userWithShortFirstName() {
        return UserBuilder.aUser()
                          .firstName("Jua")
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .build();
    }

    public static User hypertensiveUserWithoutFavoriteFood() {
        return UserBuilder.aUser()
                          .firstName("Juan pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .dietaryRequirements(Set.of(new HypertensiveDietaryRequirement()))
                          .build();
    }

    public static User diabeticUserWithoutFavoriteFood() {
        return UserBuilder.aUser()
                          .firstName("Juan pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .dietaryRequirements(Set.of(new DiabeticDietaryRequirement()))
                          .build();
    }

    public static User createPedroMendoza(String userName) {
        return aUser()
                .firstName(PEDRO)
                .lastName(MENDOZA)
                .username(userName)
                .weight(70)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
    }

    public static User createJulianFransino(String userName) {
        return aUser()
                .firstName(JULIAN)
                .lastName(FRANSINO)
                .username(userName)
                .weight(70)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
    }

    public static User createJavierMascherano(String userName) {
        return aUser()
                .firstName(JAVIER)
                .lastName(MASCHERANO)
                .username(userName)
                .weight(70)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
    }
}
