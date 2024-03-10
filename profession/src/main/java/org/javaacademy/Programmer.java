package org.javaacademy;

import java.math.BigDecimal;
import lombok.NonNull;
import org.javaacademy.human.Human;

public class Programmer extends Employee {

    private static final BigDecimal minEmployeeRate = new BigDecimal(1500);
    private static final BigDecimal maxEmployeeRate = new BigDecimal(2000);

    public Programmer(@NonNull Human human) {
        super(human);
    }

    public Programmer(@NonNull Human human, BigDecimal employeeRate) {
        super(human);
        if (employeeRate.compareTo(minEmployeeRate) < 0
                || employeeRate.compareTo(maxEmployeeRate) > 0) {
            throw new RuntimeException("Ошибка. "
                    + "Ставка работника "
                    + "не должна быть меньше 1500 и больше 2000.");
        } else {
            super.setEmployeeRate(employeeRate);
        }
    }

    public void doTask(Task task) {
        task.setDone(true);
    }
}