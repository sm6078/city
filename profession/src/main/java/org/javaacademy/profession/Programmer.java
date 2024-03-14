package org.javaacademy.profession;

import java.math.BigDecimal;
import lombok.NonNull;
import org.javaacademy.employee.Employee;
import org.javaacademy.human.Sex;

public class Programmer extends Employee {
    private static final BigDecimal MIN_EMPLOYEE_RATE = new BigDecimal(1500);
    private static final BigDecimal MAX_EMPLOYEE_RATE = new BigDecimal(2000);
    static double LABOR_FACTOR = 1.00;

    public Programmer(@NonNull String firstName, @NonNull String secondName,
                      @NonNull String middleName, Sex sex) {
        super(firstName, secondName, middleName, sex, LABOR_FACTOR);
    }

    @Override
    public void setEmployeeRate(BigDecimal employeeRate) {
        if (employeeRate.compareTo(MIN_EMPLOYEE_RATE) == 0
                || employeeRate.compareTo(MAX_EMPLOYEE_RATE) == 0) {
            super.setEmployeeRate(employeeRate);
        } else if ((employeeRate.compareTo(MIN_EMPLOYEE_RATE)) > 0
                && (employeeRate.compareTo(MAX_EMPLOYEE_RATE) < 0)) {
            super.setEmployeeRate(employeeRate);
        } else {
            throw new RuntimeException("Указана некорректная часовая ставка для программиста.");
        }
    }

    public void doTask(Task task) {
        task.setDone(true);
        System.out.printf("%s - выполнена, время выполнения: %s час/а/ов\n",
                task.getDescription(), task.getTaskTime().toHours());
    }
}
