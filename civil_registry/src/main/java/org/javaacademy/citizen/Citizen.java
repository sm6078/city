package org.javaacademy.citizen;

import lombok.Getter;
import lombok.ToString;
import org.javaacademy.human.Human;
import org.javaacademy.human.Sex;

@Getter
@ToString
public class Citizen extends Human {
	private FamilyStatus familyStatus;
	@ToString.Exclude
	private Citizen spouse;

		public Citizen(String firstName, String lastName, String patronymic, Sex gender) {
		super(firstName, lastName, patronymic, gender);
		familyStatus = FamilyStatus.NOT_MARRIED;
	}

	public Citizen(Human human) {
		super(human.getFirstName(),
				human.getSecondName(),
				human.getMiddleName(),
				human.getSex(),
				human.getFather(),
				human.getMother(),
				human.getChildren());
		familyStatus = FamilyStatus.NOT_MARRIED;
	}

	public void changeFamilyStatus(Citizen spouse, FamilyStatus status) {
		this.familyStatus = status;
		if (status.equals(FamilyStatus.MARRIED)) {
			this.spouse = spouse;
		} else {
			this.spouse = null;
		}
	}
}
