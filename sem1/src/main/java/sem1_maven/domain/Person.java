package sem1_maven.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Person {
    private String name;
    private String surname;
    private Integer age;

    public Person(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    // МЕТОДЫ, сгенерированные с помощью org.apache.commons
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return new EqualsBuilder().append(name, person.name).append(surname, person.surname).append(age, person.age).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).append(surname).append(age).toHashCode();
    }

    // метод сгенерирован по шаблону ToStringBuilder (apache.commons-lang3)
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("age", age)
                .toString();
    }


    /*
    // МЕТОДЫ, сгенерированные и использованием java.util.Objects.equals и hashCode() (java 7+)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    // МЕТОД СГЕНЕРИРОВАН по шаблону по умолчанию String.concat(+)
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
*/
}


/*
    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.14.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    </dependencies>
 */