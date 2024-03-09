package org.javaacademy.civil_registry;

import lombok.experimental.UtilityClass;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.FamilyStatus;

@UtilityClass
public class CivilRegistryUtil {
	void checkWeddingRegistration(Citizen male, Citizen female) {
		if (male.getSex() == female.getSex()) {
			throw new RuntimeException("Однополые браки запрещены.");
		}
		if (male.getFamilyStatus() == FamilyStatus.MARRIED || female.getFamilyStatus() == FamilyStatus.MARRIED) {
			throw new RuntimeException("Один из партнеров находится в отношениях.");
		}
	}

	void checkDivorceRegistration(Citizen male, Citizen female) {
		if (!male.getSpouse().equals(female) || !female.getSpouse().equals(male)) {
			throw new RuntimeException("Партнеры не находятся в отношениях");
		}
	}
}
