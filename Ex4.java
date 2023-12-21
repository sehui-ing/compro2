package compro2.ex;

public class Ex4 {
    public static void main(String[] args) {
        RemoteControl rc = new RemoteControl() {
            public void turnOn() {
                System.out.println("TV turn on.");
            }

            public void turnOff() {
                System.out.println("TV turn off.");
            }
        };

        rc.turnOn();
        rc.turnOff();
    }
}
interface RemoteControl {
    void turnOn();
    void turnOff();
}