package org.javaacademy.citizen;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;
import static org.javaacademy.citizen.FamilyStatus.MARRIED;
import static org.javaacademy.citizen.FamilyStatus.SINGLE;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Citizen extends Human {
	FamilyStatus familyStatus;
	@ToString.Exclude
	Citizen spouse;

	public Citizen(@NonNull String firstName,
				   @NonNull String lastName,
	               @NonNull String patronymic,
	               @NonNull Sex gender) {
		super(firstName, lastName, patronymic, gender);
		familyStatus = SINGLE;
	}

	public Citizen(Human human) {
		super(human.getFirstName(),
				human.getSecondName(),
				human.getMiddleName(),
				human.getSex(),
				human.getFather(),
				human.getMother(),
				human.getChildren());
		familyStatus = SINGLE;
	}

	public void changeFamilyStatus(Citizen spouse, FamilyStatus status) {
		this.familyStatus = status;
		if (status.equals(MARRIED)) {
			this.spouse = spouse;
		} else {
			this.spouse = null;
		}
	}
}
