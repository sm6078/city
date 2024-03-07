import human.Human;
import human.Sex;

public class Runner {
    /*
    Метод main создан для быстрого тестирования модуля human.
    Можно править для тестирования других модулей
     */
    public static void main(String[] args) {
        Human humanOne = new Human("дАМир", "Шакиров", "Харисович", Sex.MALE);
        Human humanTwo = new Human("аНЯ", "Горелова", "Александровна", Sex.FEMALE);
        humanOne.makeChild("артур", "шакиров", "дамирович", Sex.MALE, humanTwo);
        humanOne.makeChild("тимур", "шакиров", "дамирович", Sex.MALE, humanTwo);
        System.out.println(humanOne.getFullName());
    }
}
