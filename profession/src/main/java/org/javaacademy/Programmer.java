package org.javaacademy;

import java.math.BigDecimal;
import lombok.NonNull;
import org.javaacademy.human.Sex;

public class Programmer extends Employee {
    private static final BigDecimal MIN_EMPLOYEE_RATE = new BigDecimal(1500);
    private static final BigDecimal MAX_EMPLOYEE_RATE = new BigDecimal(2000);

    public Programmer(@NonNull String firstName, @NonNull String secondName,
                      @NonNull String middleName, Sex sex) {
        super(firstName, secondName, middleName, sex);
    }

    @Override
    public void setEmployeeRate(BigDecimal employeeRate) {
        if (employeeRate.compareTo(MIN_EMPLOYEE_RATE) == 0
                || employeeRate.compareTo(MAX_EMPLOYEE_RATE) == 0) {
            super.setEmployeeRate(employeeRate);
        } else if ((employeeRate.compareTo(MIN_EMPLOYEE_RATE)) == 1
                && (employeeRate.compareTo(MAX_EMPLOYEE_RATE) == -1)) {
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
