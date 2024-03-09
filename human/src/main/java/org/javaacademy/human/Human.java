package org.javaacademy.human;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Human {
    private final String firstName;
    private final String secondName;
    private final String middleName;
    private final Sex sex;
    private Human father;
    private Human mother;
    @EqualsAndHashCode.Exclude
    private Set<Human> children = new HashSet<>();

    public Human(@NonNull String firstName, @NonNull String secondName, @NonNull String middleName, @NonNull Sex sex) {
        this.firstName = capitalizeFirstLetterOtherToLower(firstName);
        this.secondName = capitalizeFirstLetterOtherToLower(secondName);
        this.middleName = capitalizeFirstLetterOtherToLower(middleName);
        this.sex = sex;
    }

    public Human makeChild(String firstName, String secondName, String middleName, Sex sex, Human humanTwo) {
        checkParentsSex(this, humanTwo);
        Human child = new Human(firstName, secondName, middleName, sex);
        child.makeFamilyTies(this, humanTwo);
        return child;
    }

    private void makeFamilyTies(Human parentOne, Human parentTwo) {
        this.addParents(parentOne, parentTwo);
        addChild(parentOne, this);
        addChild(parentTwo, this);
    }

    public String getFullName() {
        return StringUtils.joinWith(" ", List.of(secondName, firstName, middleName).toArray());
    }

    private void addChild(Human humanParent, Human child) {
        humanParent.children.add(child);
    }

    private void addParents(Human parentOne, Human parentTwo) {
        checkParentsSex(parentOne, parentTwo);
        if ((parentOne.sex == Sex.MALE)) {
            this.father = parentOne;
            this.mother = parentTwo;
        } else {
            this.father = parentTwo;
            this.mother = parentOne;
        }
    }

    private String capitalizeFirstLetterOtherToLower(String value) {
        return StringUtils.capitalize(value.toLowerCase());
    }

    private void checkParentsSex(Human parentOne, Human parentTwo) {
        if (parentOne.sex == parentTwo.sex) {
            throw new RuntimeException("Пол у родителей должны быть разным");
        }
    }
}
