package org.javaacademy.civil_registry;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.FamilyStatus;
import org.javaacademy.human.Human;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import static org.javaacademy.citizen.FamilyStatus.DIVORCED;
import static org.javaacademy.citizen.FamilyStatus.MARRIED;
import static org.javaacademy.civil_registry.TypeOfCivilAction.BIRTH_REGISTRATION;
import static org.javaacademy.civil_registry.TypeOfCivilAction.WEDDING_REGISTRATION;
import static org.javaacademy.civil_registry.TypeOfCivilAction.DIVORCE_REGISTRATION;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CivilRegistry {
	String name;
	TreeSet<CivilActionRecord> listOfCivilActionRecord = new TreeSet<>();

	/**
	 * Основной конструктор CivilRegistry
	 * @ name
	 */
	public CivilRegistry(String name) {
		this.name = name;
	}

	/**
	 * Регистрация рождения ребенка
	 * @ child
	 * @ father
	 * @ mother
	 * @ date
	 */
	public void birthRegistration(Human child, Citizen father, Citizen mother, LocalDate date) {
		CivilActionRecord civilActionRecord = generateCivilActionRecord(date,
				BIRTH_REGISTRATION,
				List.of(new Citizen(child), father, mother));
		listOfCivilActionRecord.add(civilActionRecord);
	}

	/**
	 * Метод регистрации свадьбы
	 * @ male
	 * @ female
	 * @ date
	 */
	public void weddingRegistration(Citizen male, Citizen female, LocalDate date) {
		CivilRegistryUtil.checkWeddingRegistration(male, female);
		changeFamilyStatus(male, female, MARRIED);
		CivilActionRecord civilActionRecord = generateCivilActionRecord(date,
				WEDDING_REGISTRATION,
				List.of(male, female));
		listOfCivilActionRecord.add(civilActionRecord);
	}

	/**
	 * Метод регистрации развода
	 * @ male
	 * @ female
	 * @ date
	 */
	public void divorceRegistration(Citizen male, Citizen female, LocalDate date) {
		CivilRegistryUtil.checkDivorceRegistration(male, female);
		changeFamilyStatus(male, female, DIVORCED);
		CivilActionRecord civilActionRecord = generateCivilActionRecord(date,
				DIVORCE_REGISTRATION,
				List.of(male, female));
		listOfCivilActionRecord.add(civilActionRecord);
	}

	/**
	 * Метод получения записей работы ЗАГС
	 */
	public void getStatistics() {
		Map<LocalDate, Map<TypeOfCivilAction, Integer>> recordsByDate = listOfCivilActionRecord.stream()
				.collect(Collectors.groupingBy(
						CivilActionRecord::getDate,
						Collectors.groupingBy(
								CivilActionRecord::getTypeOfCivilAction,
								Collectors.summingInt(e -> 1))));
		Map<LocalDate, Map<TypeOfCivilAction, Integer>> sortedRecordsByDate = new TreeMap<>(recordsByDate);

		System.out.printf("Статистика по ЗАГС: %s\n", name);
		sortedRecordsByDate.forEach(
				(date, typeOfCivilAction) -> System.out.println(
						date + " " + getCountOfTypeRegistration(typeOfCivilAction)));
	}

	private CivilActionRecord generateCivilActionRecord(LocalDate date,
								TypeOfCivilAction typeOfCivilAction,
								List<Citizen> listOfCitizens) {
		return new CivilActionRecord(date,
				typeOfCivilAction,
				listOfCitizens);
	}

	private String getCountOfTypeRegistration(Map<TypeOfCivilAction, Integer> countOfTypeRegistration) {
		StringJoiner stringJoiner = new StringJoiner(", ");
		countOfTypeRegistration.forEach((type, count) -> stringJoiner.add(generateString(type, count)));
		return stringJoiner.toString();
	}

	private String generateString(TypeOfCivilAction type, Integer count) {
		String string = "";
		if (type == BIRTH_REGISTRATION) {
			string = "Количество рождений - " + count;
		} else if (type == WEDDING_REGISTRATION) {
			string = "Количество свадеб - " + count;
		} else if (type == DIVORCE_REGISTRATION) {
			string = "Количество разводов - " + count;
		}
		return string;
	}

	private static void changeFamilyStatus(Citizen citizen1, Citizen citizen2, FamilyStatus familyStatus) {
		citizen1.changeFamilyStatus(citizen2, familyStatus);
		citizen2.changeFamilyStatus(citizen1, familyStatus);
	}
}
