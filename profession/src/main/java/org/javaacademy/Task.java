package org.javaacademy;

import java.time.Duration;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    final String description;
    boolean isDone;
    final Duration taskTime;
}
