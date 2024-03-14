package org.javaacademy.company;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.javaacademy.employee.Employee;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.profession.Task;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
	@NonNull
	final String companyName;
	final Manager manager;
	final Set<Programmer> programmers;
	final MultiValuedMap<Programmer, Task> listOfCompletedTasks = new HashSetValuedHashMap<>();
	final Map<Employee, Duration> timeSheet = new HashMap<>();
	BigDecimal totalCost = BigDecimal.ZERO;

	/**
	 * Основной конструктор
	 * @ companyName
	 * @ manager
	 * @ programmers
	 * @ wageRateProgrammer
	 */
	public Company(String companyName,
				   Manager manager,
				   Set<Programmer> programmers,
				   BigDecimal wageRateProgrammer) {
		this.companyName = companyName;
		this.manager = manager;
		this.programmers = programmers;
		setWageRate(manager, programmers, wageRateProgrammer);
	}

	/**
	 * Метод выполнения работы компании
	 * @ tasksList
	 */
	public void getStarted(Queue<Task> tasksList) {
		while (!tasksList.isEmpty()) {
			programmers.forEach(programmer -> workDay(programmer, tasksList));
		}
	}

	/**
	 * Метод расчета дохода организации
	 */
	public void calculateExpenses() {
		timeSheet.forEach(this::calculate);
		timeSheet.clear();
	}

	/**
	 * Метод печати информации о компании
	 */
	public void infoCompany() {
		System.out.println("-----------");
		System.out.printf("Отчет по компании: %s\n", companyName);
		System.out.printf("Затраты компании составили: %s\n",
				totalCost.setScale(2, RoundingMode.HALF_UP));
		System.out.println("-----------");
		listOfCompletedTasks.asMap()
				.forEach((programmer, tasks) -> System.out.println(
						generateCompletedTaskList(programmer, tasks)
				));
	}

	private String generateCompletedTaskList(Programmer programmer, Collection<Task> tasks) {
		String result = tasks.stream()
				.map(Task::getDescription)
				.collect(Collectors.joining(", "));
		return String.format("%s   -   %s", programmer.getFullName(), result);
	}

	private void calculate(Employee employee, Duration duration) {
		BigDecimal hoursCount = BigDecimal.valueOf(duration.toSeconds() / 3600d);
		totalCost = totalCost.add(employee.getEmployeeRate().multiply(hoursCount));
	}

	private void workDay(Programmer programmer, Queue<Task> tasksList) {
		if (!tasksList.isEmpty()) {
			Task currentTask = tasksList.poll();
			programmer.doTask(currentTask);
			recordTheTimeItTakesToCompleteTheWork(currentTask, programmer);
			recordTheTimeItTakesToCompleteTheWork(currentTask, manager);
			listOfCompletedTasks.put(programmer, currentTask);
		}
	}

	private void setWageRate(Manager manager, Set<Programmer> programmers, BigDecimal wageRateProgrammer) {
		manager.setEmployeeRate(Manager.FIXED_WAGE_RATE);
		programmers.forEach(programmer -> programmer.setEmployeeRate(wageRateProgrammer));
	}

	private void recordTheTimeItTakesToCompleteTheWork(Task currentTask, Employee employee) {
		if (timeSheet.containsKey(employee)) {
			long seconds = timeSheet.get(employee).toSeconds();
			double factTime = employee.getLaborFactor() * currentTask.getTaskTime().toSeconds();
			timeSheet.put(employee, Duration.ofSeconds(seconds + Math.round(factTime)));
		} else {
			double factTime = employee.getLaborFactor() * currentTask.getTaskTime().toSeconds();
			timeSheet.put(employee, Duration.ofSeconds(Math.round(factTime)));
		}
	}
}
