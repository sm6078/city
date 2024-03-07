package human;

import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class Human {
    private final String firstName;
    private final String secondName;
    private final String middleName;
    private final Sex sex;
    private Human father;
    private Human mother;
    private Set<Human> children = new HashSet<>();

    public Human(@NonNull String firstName, @NonNull String secondName,
                 @NonNull String middleName, @NonNull Sex sex) {
        this.firstName = capitalizeFirstLetterOtherToLower(firstName);
        this.secondName = capitalizeFirstLetterOtherToLower(secondName);
        this.middleName = capitalizeFirstLetterOtherToLower(middleName);
        this.sex = sex;
    }

    public Human makeChild(String firstName, String secondName, String middleName, Sex sex, Human humanTwo) {
        checkParentsSex(this, humanTwo);
        Human newHuman = new Human(firstName, secondName, middleName, sex);
        newHuman.addParents(this, humanTwo);
        addChild(this, newHuman);
        addChild(humanTwo, newHuman);
        return newHuman;
    }

    public String getFullName() {
        return secondName + " " + firstName + " " + middleName;
    }

    private void addChild(Human humanParent, Human child) {
        humanParent.children.add(child);
    }

    private void addParents(Human humanOne, Human humanTwo) {
        checkParentsSex(humanOne, humanTwo);
        if ((humanOne.sex == Sex.MALE)) {
            father = humanOne;
            mother = humanTwo;
        } else {
            father = humanTwo;
            mother = humanOne;
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
