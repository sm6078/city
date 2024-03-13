package org.javaacademy.profession;

import lombok.NonNull;
import org.javaacademy.employee.Employee;
import org.javaacademy.human.Sex;
import java.math.BigDecimal;

public class Manager extends Employee {
	public final static BigDecimal FIXED_WAGE_RATE = BigDecimal.valueOf(10_000.00);
	public final static double LABOR_FACTOR = 0.1;

	public Manager(@NonNull String firstName,
                   @NonNull String secondName,
                   @NonNull String middleName,
                   @NonNull Sex sex) {
		super(firstName, secondName, middleName, sex, LABOR_FACTOR);
	}
}
