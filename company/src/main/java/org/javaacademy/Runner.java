package org.javaacademy;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.javaacademy.company.Company;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.profession.Task;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import static org.javaacademy.human.Sex.FEMALE;
import static org.javaacademy.human.Sex.MALE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NonNull
public class Runner {

	public static void main(String[] args) {
		String companyName = getCompanyName(args);
		BigDecimal wageRateProgrammer = getWageRateProgrammer(args);
		Manager manager = new Manager("Сафра Кац", "Ларри", "Эллисон", MALE);
		Company myCompany = new Company(
				companyName, manager, generateProgrammers(), wageRateProgrammer);
		Queue<Task> tasksList = generateTasks();
		myCompany.getStarted(tasksList);
		myCompany.calculateExpenses();
		myCompany.infoCompany();
	}

	/**
	 * Для служебного применения
	 * Генерация программистов
	 * @return Set<Programmer>
	 */
	private static Set<Programmer> generateProgrammers() {
		Programmer programmer1 = new Programmer("Наумов", "Дмитрий", "Евгеньевич", MALE);
		Programmer programmer2 = new Programmer("Романова", "Ирина", "Александровна", FEMALE);
		return Set.of(programmer1, programmer2);
	}

	/**
	 * Для служебного применения
	 * Генерация задач
	 * @return Queue<Task>
	 */
	private static Queue<Task> generateTasks() {
		Task task1 = new Task("Задача №1. Создать...", Duration.ofHours(8));
		Task task2 = new Task("Задача №2. Разработать отчет...", Duration.ofHours(4));
		Task task3 = new Task("Задача №3. Автоматизировать функционал...", Duration.ofHours(16));
		return new LinkedList<>(List.of(task1, task2, task3));
	}

	/**
	 * Для служебного применения.
	 * Получить имя компании
	 * @ String[] args
	 * @return String
	 */
	private static String getCompanyName(String[] args) {
		if (args.length >= 1) {
			return args[0];
		}
		return "";
	}

	/**
	 * Для служебного применения.
	 * Получить единую ставку для программистов
	 * @ String[] args
	 * @return BigDecimal
	 */
	private static BigDecimal getWageRateProgrammer(String[] args) {
		if (args.length >= 2) {
			long rate = Long.parseLong(args[1]);
			return BigDecimal.valueOf(rate);
		}
		return BigDecimal.ZERO;
	}
}
