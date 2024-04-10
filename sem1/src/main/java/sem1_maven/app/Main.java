package sem1_maven.app;

import com.google.gson.Gson;
import sem1_maven.domain.Person;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Person pers1 = new Person("Alice", "Dawn", 32);
        Person pers2 = new Person("Sebastian", "Smith", 27);

        Gson gson = new Gson();
        String pers1Json = gson.toJson(pers1);
        String pers2Json = gson.toJson(pers2);

        System.out.println(pers1 + "\n" + pers1Json + "\n");
        System.out.println(pers2 + "\n" + pers2Json + "\n");

        Person pers3 = gson.fromJson(pers1Json, Person.class);
        Person pers4 = gson.fromJson(pers2Json, Person.class);

        System.out.println(pers3);
        System.out.println(pers4);
        System.out.println();

        System.out.printf("Сравнение %s и %s. Результат - %s.%n", pers1, pers3, Objects.equals(pers1, pers3));
        System.out.printf("Сравнение %s и %s. Результат - %s.%n", pers1, pers3, Objects.equals(pers2, pers4));

    }
}
