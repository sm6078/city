package org.javaacademy;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;

public abstract class Employee extends Human {
    @Getter
    @Setter
    private BigDecimal employeeRate;

    public Employee(@NonNull String firstName, @NonNull String secondName,
                    @NonNull String middleName, @NonNull Sex sex) {
        super(firstName, secondName, middleName, sex);
    }
}
