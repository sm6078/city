package org.javaacademy;

import java.math.BigDecimal;
import java.time.Duration;
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
        if (employeeRate.compareTo(MIN_EMPLOYEE_RATE) <= 0
                || employeeRate.compareTo(MAX_EMPLOYEE_RATE) >= 0) {
            throw new RuntimeException("Ошибка. "
                    + "Ставка работника "
                    + "не должна быть меньше 1500 и больше 2000.");
        } else {
            super.setEmployeeRate(employeeRate);
        }
    }

    public void doTask(Task task, Duration timeTask) {
        task.setDone(true);
        task.setTaskTime(timeTask);
        System.out.printf("%s - выполнена, время выполнения: %s час/а/ов\n",
                task.getDescription(), task.getTaskTime().toHours());
    }
}
