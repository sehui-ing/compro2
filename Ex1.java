package compro2.ex;

public class Ex1 {
    public static void main(String[] args) {
    Employee e1 = new Employee("Kim", "20210001");
    System.out.printf("name:%s  id:%s\n", e1.name, e1.id);

    Employee e2 = new Employee("Tom");
    System.out.printf("name:%s  id:%s\n", e2.name, e2.id);

    Employee e3 = new Employee();
    System.out.printf("name:%s  id:%s\n", e3.name, e3.id);
    }
}
class Person {
    String name;
    public Person() {
    }
    public Person(String theName) {
        this.name = theName;
    }
}
class Employee extends Person {
    String id;
    public Employee() {
        super();
    }
    public Employee(String name) {
        super(name);
    }
    public Employee(String name, String id) {
        super(name);
        this.id = id;
    }
}