import human.Human;
import human.Sex;

public class Runner {
    /*
    Метод main создан для тестирования модуля human.
    Можно править для тестирования других модулей
     */
    public static void main(String[] args) {
        Human humanOne = new Human("дАМир", "Шакиров", "Харисович", Sex.Male);
        Human humanTwo = new Human("аНЯ", "Горелова", "Александровна", Sex.Female);
        humanOne.makeChild("артур", "шакиров", "дамирович", Sex.Male, humanTwo);
        System.out.println(humanOne.getFullName());
    }
}
