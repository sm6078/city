package org.javaacademy;

import java.time.Duration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    final String description;
    boolean isDone;
    Duration taskTime;
}
