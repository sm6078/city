package org.javaacademy;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public abstract class Employee extends Human {
    BigDecimal employeeRate;

    public Employee(@NonNull String firstName, @NonNull String secondName,
                    @NonNull String middleName, @NonNull Sex sex) {
        super(firstName, secondName, middleName, sex);
    }
}
