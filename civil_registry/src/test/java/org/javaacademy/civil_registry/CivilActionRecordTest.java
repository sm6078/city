package org.javaacademy.civil_registry;

import org.javaacademy.citizen.Citizen;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CivilActionRecordTest {
    public static final CivilActionRecord civilActionRecord1 =
            new CivilActionRecord(LocalDate.of(2024, 1, 1),
            TypeOfCivilAction.BIRTH_REGISTRATION,
            List.of(new Citizen(new Human("Иван", "Иванов", "Иванович", Sex.MALE))));

    public static final CivilActionRecord civilActionRecord2 =
            new CivilActionRecord(LocalDate.of(2024, 1, 1),
                    TypeOfCivilAction.BIRTH_REGISTRATION,
                    List.of(new Citizen(new Human("Иван", "Иванов", "Иванович", Sex.MALE))));

    public static final CivilActionRecord civilActionRecord3 =
            new CivilActionRecord(LocalDate.of(2024, 2, 1),
                    TypeOfCivilAction.BIRTH_REGISTRATION,
                    List.of(new Citizen(new Human("Иван", "Иванов", "Иванович", Sex.MALE))));

    public static final CivilActionRecord civilActionRecord4 = null;

    @Test
    @DisplayName("Проверка equals, если объекты совпадают")
    public void equalsSuccess() {
        assertEquals(civilActionRecord1, civilActionRecord2);
    }

    @Test
    @DisplayName("Проверка equals, если объекты не совпадают")
    public void notEqualsSuccess() {
        assertNotEquals(civilActionRecord1, civilActionRecord3);
    }

    @Test
    @DisplayName("Проверка equals, если у объектов совпадает ссылка")
    public void linkSuccess() {
        assertEquals(civilActionRecord1, civilActionRecord2);
    }

    @Test
    @DisplayName("Проверка equals на значение false")
    public void falseSuccess() {
        assertNull(civilActionRecord4);
    }

    @Test
    @DisplayName("Проверка на получение списка жителей")
    public void getListOfCitizenSuccess() {
        assertDoesNotThrow(civilActionRecord1::getListOfCitizens);
    }

    @Test
    @DisplayName("Проверка на получение типа регистрации")
    public void getTypeOfCivilActionSuccess() {
        assertDoesNotThrow(civilActionRecord1::getTypeOfCivilAction);
    }

    @Test
    @DisplayName("Проверка на отсутствие ошибок в методе ToString")
    public void toStringDoesNotThrow() {
        assertDoesNotThrow(civilActionRecord1::toString);
    }
}
