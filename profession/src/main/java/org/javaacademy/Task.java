package org.javaacademy;

import java.time.Duration;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @NonNull
    String description;
    @Setter
    boolean isDone;
    @Setter


    Duration taskTime;
}
