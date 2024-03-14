package org.javaacademy.company;

import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.profession.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import static org.javaacademy.human.Sex.FEMALE;
import static org.javaacademy.human.Sex.MALE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyTest {
    Manager manager = new Manager("Сафра Кац", "Ларри", "Эллисон", MALE);
    Programmer programmer1 = new Programmer("Наумов", "Дмитрий", "Евгеньевич", MALE);
    Programmer programmer2 = new Programmer("Романова", "Ирина", "Александровна", FEMALE);
    private Company testCompany = new Company("TestCompany",
            manager, Set.of(programmer1, programmer2), new BigDecimal(1700));

    @Test
    public void getStartedSuccess() {
        Task task1 = new Task("Задача №1. Тесты", Duration.ofHours(8));
        Task task2 = new Task("Задача №2. Снова тесты", Duration.ofHours(4));
        Task task3 = new Task("Задача №3. И снова седая ночь", Duration.ofHours(16));
        Queue<Task> tasks = new LinkedList<>(List.of(task1, task2, task3));
        testCompany.getStarted(tasks);

        assertTrue(task1.isDone());
        assertTrue(task2.isDone());
        assertTrue(task3.isDone());
    }

    @Test
    void calculateExpensesSuccess() {
        Task task1 = new Task("Задача №1. Тесты", Duration.ofHours(8));
        Task task2 = new Task("Задача №2. Снова тесты", Duration.ofHours(4));
        Task task3 = new Task("Задача №3. И снова седая ночь", Duration.ofHours(16));
        Queue<Task> tasks = new LinkedList<>(List.of(task1, task2, task3));
        testCompany.getStarted(tasks);

        Assertions.assertDoesNotThrow(() -> testCompany.calculateExpenses());
    }

    @Test
    void infoCompanySuccess() {
        Task task1 = new Task("Задача №1. Тесты", Duration.ofHours(8));
        Task task2 = new Task("Задача №2. Снова тесты", Duration.ofHours(4));
        Task task3 = new Task("Задача №3. И снова седая ночь", Duration.ofHours(16));
        Queue<Task> tasks = new LinkedList<>(List.of(task1, task2, task3));
        testCompany.getStarted(tasks);
        testCompany.calculateExpenses();

        Assertions.assertDoesNotThrow(() -> testCompany.infoCompany());
    }
}