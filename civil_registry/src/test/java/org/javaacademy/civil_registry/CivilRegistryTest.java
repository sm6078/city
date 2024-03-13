package org.javaacademy.civil_registry;

import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.FamilyStatus;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CivilRegistryTest {
    private static final CivilRegistry civilRegistry = new CivilRegistry("Test_Zags");


    @Test
    @DisplayName("Проверка успешной регистрации рождения ребенка")
    public void birthRegistrationSuccess() {
        Human child = new Human("Ребенок", "Иванов", "Иванович", Sex.MALE);
        Citizen father = new Citizen("Отец", "Петров", "Петрович", Sex.MALE);
        Citizen mother = new Citizen("Мать", "Петрова", "Петровна", Sex.FEMALE);
        LocalDate date = LocalDate.of(2023, 1, 1);

        civilRegistry.birthRegistration(child, father, mother, date);
        CivilActionRecord civilActionRecord = civilRegistry.generateCivilActionRecord(date, TypeOfCivilAction.BIRTH_REGISTRATION,
                List.of(new Citizen(child), father, mother));

        assertTrue(civilRegistry.getListOfCivilActionRecord().contains(civilActionRecord));

    }

    @Test
    @DisplayName("Проверка успешной регистрации свадьбы")
    public void weddingRegistrationSuccess() {
        Citizen male = new Citizen("Мужчина", "Петров", "Петрович", Sex.MALE);
        Citizen female = new Citizen("Женщина", "Петрова", "Петровна", Sex.FEMALE);
        LocalDate date = LocalDate.of(2023, 1, 2);

        civilRegistry.weddingRegistration(male, female, date);
        CivilActionRecord civilActionRecord = civilRegistry.generateCivilActionRecord(date, TypeOfCivilAction.WEDDING_REGISTRATION,
                List.of(male, female));

        assertTrue(civilRegistry.getListOfCivilActionRecord().contains(civilActionRecord));
    }

    @Test
    @DisplayName("Проверка успешной регистрации развода")
    public void divorceRegistrationSuccess() {
        Citizen male = new Citizen("Мужчина", "Петров", "Петрович", Sex.MALE);
        Citizen female = new Citizen("Женщина", "Петрова", "Петровна", Sex.FEMALE);
        LocalDate date = LocalDate.of(2023, 1, 3);
        male.changeFamilyStatus(female, FamilyStatus.MARRIED);
        female.changeFamilyStatus(male, FamilyStatus.MARRIED);

        civilRegistry.divorceRegistration(male, female, date);
        CivilActionRecord civilActionRecord = civilRegistry.generateCivilActionRecord(date, TypeOfCivilAction.DIVORCE_REGISTRATION,
                List.of(male, female));

        assertTrue(civilRegistry.getListOfCivilActionRecord().contains(civilActionRecord));
    }


    @Test
    @DisplayName("Проверка отсутствия ошибок при выводе статистики в консоль")
    public void getStatisticsSuccess() {
        assertDoesNotThrow(civilRegistry::getStatistics);
    }
}
