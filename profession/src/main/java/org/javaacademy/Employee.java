package org.javaacademy;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.javaacademy.human.Human;

public abstract class Employee extends Human {
    @Getter
    @Setter
    private BigDecimal employeeRate;

    public Employee(@NonNull Human human) {
        super(human.getFirstName(), human.getSecondName(), human.getMiddleName(), human.getSex());
    }
}
