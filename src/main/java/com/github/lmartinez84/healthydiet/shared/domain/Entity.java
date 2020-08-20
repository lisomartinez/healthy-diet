package com.github.lmartinez84.healthydiet.shared.domain;

public abstract class Entity<T extends Identifier> {
    protected T identifier;

    protected Entity(T identifier) {
        this.identifier = identifier;
    }

    public T id() {
        return identifier;
    }

    public void id(T id) {
        identifier = id;
    }
}
