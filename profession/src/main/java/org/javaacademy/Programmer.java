package org.javaacademy;

import java.math.BigDecimal;
import lombok.NonNull;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;

public class Programmer extends Employee {

    private static final BigDecimal MIN_EMPLOYEE_RATE = new BigDecimal(1500);
    private static final BigDecimal MAX_EMPLOYEE_RATE = new BigDecimal(2000);

    public Programmer(@NonNull String firstName, @NonNull String secondName, @NonNull String middleName, @NonNull Sex sex) {
        super(firstName, secondName, middleName, sex);
    }






    /* public Programmer(@NonNull Human human, BigDecimal employeeRate) {
        super(human);
        if (employeeRate.compareTo(MIN_EMPLOYEE_RATE) <= 0
                || employeeRate.compareTo(MAX_EMPLOYEE_RATE) >= 0) {
            throw new RuntimeException("Ошибка. "
                    + "Ставка работника "
                    + "не должна быть меньше 1500 и больше 2000.");
        } else {
            super.setEmployeeRate(employeeRate);
        }
    }*/

    public void doTask(Task task) {
        task.setDone(true);
    }
}