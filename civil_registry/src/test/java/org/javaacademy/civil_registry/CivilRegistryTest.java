package org.javaacademy.civil_registry;

import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.FamilyStatus;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

public class CivilRegistryTest {
    private static final CivilRegistry civilRegistry = new CivilRegistry("Test_Zags");
    Citizen male = new Citizen("Мужчина", "Петров", "Петрович", Sex.MALE);
    Citizen female = new Citizen("Женщина", "Петрова", "Петровна", Sex.FEMALE);
    LocalDate date = LocalDate.of(2023, 1, 1);
    Human child = new Human("Ребенок", "Иванов", "Иванович", Sex.MALE);

    @Test
    @DisplayName("Проверка успешной регистрации рождения ребенка")
    public void birthRegistrationSuccess() {
        civilRegistry.birthRegistration(child, male, female, date);
        CivilActionRecord civilActionRecord = civilRegistry.generateCivilActionRecord(date, TypeOfCivilAction.BIRTH_REGISTRATION,
                List.of(new Citizen(child), male, female));

        assertTrue(civilRegistry.getListOfCivilActionRecord().contains(civilActionRecord));
    }

    @Test
    @DisplayName("Проверка успешной регистрации свадьбы")
    public void weddingRegistrationSuccess() {
        civilRegistry.weddingRegistration(male, female, date);
        CivilActionRecord civilActionRecord = civilRegistry.generateCivilActionRecord(date, TypeOfCivilAction.WEDDING_REGISTRATION,
                List.of(male, female));

        assertTrue(civilRegistry.getListOfCivilActionRecord().contains(civilActionRecord));
    }

    @Test
    @DisplayName("Проверка успешной регистрации развода")
    public void divorceRegistrationSuccess() {
        male.changeFamilyStatus(female, FamilyStatus.MARRIED);
        female.changeFamilyStatus(male, FamilyStatus.MARRIED);
        civilRegistry.divorceRegistration(male, female, date);
        CivilActionRecord civilActionRecord = civilRegistry.generateCivilActionRecord(date, TypeOfCivilAction.DIVORCE_REGISTRATION,
                List.of(male, female));

        assertTrue(civilRegistry.getListOfCivilActionRecord().contains(civilActionRecord));
        male.changeFamilyStatus(female, FamilyStatus.SINGLE);
        female.changeFamilyStatus(male, FamilyStatus.SINGLE);
    }

    @Test
    @DisplayName("Проверка на ошибку при однополом браке")
    public void weddingRegistrationSomeSexException() {
        assertThrows(RuntimeException.class, () -> civilRegistry.weddingRegistration(male, male, date));
        assertThrows(RuntimeException.class, () -> civilRegistry.weddingRegistration(female, female, date));

    }

    @Test
    @DisplayName("Проверку на ошибку, если один из партнеров находится в отношениях")
    public void weddingRegistrationMarriesException() {
        male.changeFamilyStatus(female, FamilyStatus.MARRIED);
        assertThrows(RuntimeException.class, () -> civilRegistry.weddingRegistration(male, female, date));
        male.changeFamilyStatus(female, FamilyStatus.SINGLE);
        female.changeFamilyStatus(male, FamilyStatus.MARRIED);
        assertThrows(RuntimeException.class, () -> civilRegistry.weddingRegistration(male, female, date));
        female.changeFamilyStatus(male, FamilyStatus.SINGLE);
    }

    @Test
    @DisplayName("Проверку на ошибку, если при разводе партнеры не женаты")
    public void divorceRegistrationException() {
        male.changeFamilyStatus(female, FamilyStatus.SINGLE);
        female.changeFamilyStatus(male, FamilyStatus.MARRIED);
        assertThrows(RuntimeException.class, () -> civilRegistry.divorceRegistration(male, female, date));
    }

    @Test
    @DisplayName("Проверка отсутствия ошибок при выводе статистики в консоль")
    public void getStatisticsDoesNotThrow() {
        assertDoesNotThrow(civilRegistry::getStatistics);
    }

    @Test
    public void getCountOfTypeRegistrationSuccess() {
        Map<TypeOfCivilAction, Integer> countOfTypeRegistration = new TreeMap<>();
        countOfTypeRegistration.put(TypeOfCivilAction.BIRTH_REGISTRATION, 3);
        countOfTypeRegistration.put(TypeOfCivilAction.WEDDING_REGISTRATION, 2);
        countOfTypeRegistration.put(TypeOfCivilAction.DIVORCE_REGISTRATION, 1);

        String result = civilRegistry.getCountOfTypeRegistration(countOfTypeRegistration);

        assertEquals("Количество рождений - 3, Количество свадеб - 2, Количество разводов - 1", result);
    }
}
