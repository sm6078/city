package org.javaacademy;

import java.time.Duration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @NonNull
    String description;
    @Setter
    boolean isDone;
    @NonNull
    Duration taskTime;
}
