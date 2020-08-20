package com.github.lmartinez84.healthydiet.domain.user;

import com.github.lmartinez84.healthydiet.domain.Entity;
import com.github.lmartinez84.healthydiet.domain.recipe.Food;
import com.github.lmartinez84.healthydiet.domain.recipe.FoodInadequacy;
import com.github.lmartinez84.healthydiet.utils.DoubleComparerUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class User extends Entity<UserId> {
    public static final int MIN_HEALTHY_BMI = 18;
    public static final int MAX_HEALTHY_BMI = 30;
    public static final int TO_THE_TWO = 2;
    public static final int MIN_NAME_LENGTH = 4;
    private final String username;
    private String firstName;
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
        super(UserId.nullId());
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
        }
        if (Period.between(user.dateOfBirth, LocalDate.now()).getDays() < 1) {
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
        }
        if (user.firstName.isBlank()) {
            throw new EmptyNameUserException();
        }
        if (user.firstName.length() <= MIN_NAME_LENGTH) {
            throw new ShortNameUserException();
        }
    }

    public double calculateBMI() {
        return weight / Math.pow(height, TO_THE_TWO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
        return DoubleComparerUtils.isGreaterThanOrEqual(calculateBMI(), MIN_HEALTHY_BMI)
                && DoubleComparerUtils.isLessThanOrEqual(calculateBMI(), MAX_HEALTHY_BMI);
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

    public String username() {
        return this.username;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public double height() {
        return height;
    }

    public Set<FoodInadequacy> dietaryRequirements() {
        return dietaryRequirements.stream()
                                  .map(DietaryRequirement::inadequacy)
                                  .collect(Collectors.toSet());
    }

    public void firstName(String name) {
        this.firstName = name;
    }
}
