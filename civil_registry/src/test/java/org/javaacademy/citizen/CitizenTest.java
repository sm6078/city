package org.javaacademy.citizen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.javaacademy.human.Sex;

import static org.junit.jupiter.api.Assertions.*;

public class CitizenTest {
    private static Citizen citizen = new Citizen("Иван",
            "Иванов",
            "Иванович",
            Sex.MALE);

    @Test
    @DisplayName("Проверка корректного добавления супруга")
    public void setMarriedSuccess() {
        Citizen spouse = new Citizen("Марина",
                "Петрова",
                "Александровна",
                Sex.FEMALE);
        FamilyStatus status = FamilyStatus.MARRIED;

        citizen.changeFamilyStatus(spouse, status);
        assertEquals(citizen.getSpouse(), spouse);
    }

    @Test
    @DisplayName("Проверка некорректного добавления супруга")
    public void setSingleSuccess() {
        Citizen spouse = new Citizen("Марина",
                "Петрова",
                "Александровна",
                Sex.FEMALE);
        FamilyStatus status = FamilyStatus.SINGLE;

        citizen.changeFamilyStatus(spouse, status);
        assertNull(citizen.getSpouse());
    }

    @Test
    @DisplayName("Проверка на отсутствие ошибок в методе ToString")
    public void toStringDoesNotThrow() {
        assertDoesNotThrow(() -> citizen.toString());
    }
}
