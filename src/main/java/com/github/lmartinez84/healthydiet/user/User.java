package com.github.lmartinez84.healthydiet.user;

import com.github.lmartinez84.healthydiet.Food;
import com.github.lmartinez84.healthydiet.Routine;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.DietaryRequirement;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.exceptions.BirthDateNotInPastUserException;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.exceptions.ShortNameUserException;
import com.github.lmartinez84.healthydiet.user.exceptions.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class User {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final double weight;
    private final double height;
    private final Set<DietaryRequirement> dietaryRequirements;
    private final Routine routine;
    private Set<Food> favoriteFoods;

    public User(String username,
                String firstName,
                String lastName,
                LocalDate dateOfBirth,
                double weight,
                double height,
                Set<DietaryRequirement> dietaryRequirements,
                Routine routine,
                Set<Food> favoriteFoods
    ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.dietaryRequirements = dietaryRequirements;
        this.routine = routine;
        this.favoriteFoods = favoriteFoods;
        this.validate(this);
    }

    private void validate(User user) {
        isFirstNameValid(user);
        hasWeight(user);
        hasHeight(user);
        hasBirthDateInThePast(user);
        hasRoutine(user);
        satisfyDietaryRequirementsValidations(user);
    }

    private void satisfyDietaryRequirementsValidations(User user) {
        user.dietaryRequirements.forEach(dietaryRequirement -> dietaryRequirement.validate(this));
    }

    private void hasRoutine(User user) {
        if (user.routine == null) {
            throw new NoRoutineUserException();
        }
    }

    private void hasBirthDateInThePast(User user) {
        if (user.dateOfBirth == null) {
            throw new NoBirthDatetUserException();
        } else if (Period.between(user.dateOfBirth, LocalDate.now()).getDays() < 1) {
            throw new BirthDateNotInPastUserException();
        }
    }

    private void hasHeight(User user) {
        if (user.height < 1) {
            throw new NoHeightUserException();
        }
    }

    private void hasWeight(User user) {
        if (user.weight < 1) {
            throw new NoWeightUserException();
        }
    }

    private void isFirstNameValid(User user) {
        if (user.firstName == null) {
            throw new NullNameUserException();
        } else if (user.firstName.isBlank()) {
            throw new EmptyNameUserException();
        } else if (user.firstName.length() <= 4) {
            throw new ShortNameUserException();
        }
    }

    public double calculateBMI() {
        return weight / Math.pow(height, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public boolean isHealthy() {
        return hasNormalWeight() && allDietaryRequirementsAreCompensated();
    }

    private boolean allDietaryRequirementsAreCompensated() {
        return dietaryRequirements.stream()
                                  .allMatch(dietaryRequirement -> dietaryRequirement.isCompensated(this));
    }

    private boolean hasNormalWeight() {
        return (calculateBMI() - 18 >= 0.0000001) && (calculateBMI() - 30 <= 0.00000001);
    }

    public Routine routine() {
        return this.routine;
    }

    public double weight() {
        return weight;
    }

    public int age() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public Set<Food> favoritesFoods() {
        return Collections.unmodifiableSet(favoriteFoods);
    }
}
