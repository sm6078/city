package org.javaacademy.civil_registry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CivilActionRecord implements Comparable<CivilActionRecord> {
	@ToString.Exclude
	LocalDate date;
	TypeOfCivilAction typeOfCivilAction;
	List<Citizen> listOfCitizens;

	@Override
	public int compareTo(CivilActionRecord o) {
		int result = this.getDate().compareTo(o.getDate());
		if (result == 0) {
			return this.hashCode() - o.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CivilActionRecord that = (CivilActionRecord) o;
		return Objects.equals(date, that.date)
				&& typeOfCivilAction == that.typeOfCivilAction
				&& Objects.equals(listOfCitizens, that.listOfCitizens);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, typeOfCivilAction, listOfCitizens);
	}
}
