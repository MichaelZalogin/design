package ru.job4j.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    RoleStore store = new RoleStore();

    @BeforeEach
    public void setUp() {
        store.add(new Role("1", "SomeRole"));
    }

    @Test
    void whenAddAndFindThenSomeRole() {
        assertThat(store.findById("1")
                .getRoleName())
                .isEqualTo("SomeRole");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        assertThat(store.findById("2")).isNull();
    }

    @Test
    void whenAddDuplicateAndFindSomeRole() {
        store.add(new Role("1", "NewRole"));
        assertThat(store.findById("1")
                .getRoleName())
                .isEqualTo("SomeRole");
    }

    @Test
    void whenReplaceThenAddNewRole() {
        store.replace("1", new Role("1", "NewRole"));
        assertThat(store.findById("1")
                .getRoleName())
                .isEqualTo("NewRole");
    }

    @Test
    void whenNoReplaceRoleThenNoFindRole() {
        store.replace("10", new Role("10", "NewRole"));
        assertThat(store.findById("1")
                .getRoleName())
                .isEqualTo("SomeRole");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.delete("1");
        assertThat(store.findById("1")).isNull();
    }

    @Test
    void whenNoDeleteRoleThenSomeRole() {
        store.delete("10");
        assertThat(store.findById("1")
                .getRoleName())
                .isEqualTo("SomeRole");
    }

    @Test
    void whenReplaceOkThenTrue() {
        assertThat(store.replace("1", new Role("1", "NewRole"))).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        assertThat(store.replace("10", new Role("10", "NewRole"))).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        assertThat(store.delete("1")).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        assertThat(store.delete("2")).isFalse();
    }
}