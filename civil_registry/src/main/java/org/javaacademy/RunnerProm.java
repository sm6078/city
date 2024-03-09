package org.javaacademy;

import org.javaacademy.citizen.Citizen;
import org.javaacademy.civil_registry.CivilRegistry;
import org.javaacademy.human.Sex;
import org.javaacademy.human.Human;

import java.time.LocalDate;

public class RunnerProm {
	private static String civilRegistryName;

	public static void main(String[] args) {
		civilRegistryName = getCivilRegistryName(args);

		Citizen citizenMale1 = new Citizen("Петряшин", "Александр", "Александрович", Sex.MALE);
		Citizen citizenMale2 = new Citizen("Сидоров", "Владимир", "Иванович", Sex.MALE);
		Citizen citizenMale3 = new Citizen("Петров", "Андрей", "Витальевич", Sex.MALE);

		Citizen citizenFemale1 = new Citizen("Зотова", "Лариса", "Петровна", Sex.FEMALE);
		Citizen citizenFemale2 = new Citizen("Урусова", "Галина", "Анатольевна", Sex.FEMALE);
		Citizen citizenFemale3 = new Citizen("Самсонова", "Светлана", "Геннадьевна", Sex.FEMALE);

		Human humanChild1 = citizenMale1.makeChild("Петряшин", "Антон", "Александрович", Sex.MALE, citizenFemale1);
		Human humanChild2 = citizenMale1.makeChild("Петряшин", "Денис", "Александрович", Sex.MALE, citizenFemale1);
		Human humanChild3 = citizenMale3.makeChild("Петров", "Петр", "Андреевич", Sex.MALE, citizenFemale3);

		CivilRegistry civilRegistry = new CivilRegistry(civilRegistryName);
		civilRegistry.weddingRegistration(citizenMale2, citizenFemale2, LocalDate.of(2020, 01, 01));

		civilRegistry.weddingRegistration(citizenMale1, citizenFemale1, LocalDate.of(2023, 02, 20));
		civilRegistry.weddingRegistration(citizenMale3, citizenFemale3, LocalDate.of(2023, 02, 20));

		civilRegistry.birthRegistration(humanChild1, citizenMale1, citizenFemale1, LocalDate.of(2023, 02, 20));
		civilRegistry.birthRegistration(humanChild2, citizenMale1, citizenFemale1, LocalDate.of(2023, 02, 20));
		civilRegistry.birthRegistration(humanChild3, citizenMale3, citizenFemale3, LocalDate.of(2023, 02, 20));

		civilRegistry.divorceRegistration(citizenMale2, citizenFemale2, LocalDate.of(2023, 02, 20));

		civilRegistry.getStatistics();
	}

	private static String getCivilRegistryName(String[] args) {
		if (args.length != 0) {
			return args[0];
		}
		return "";
	}
}
