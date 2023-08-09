package testclass;

public class App {
    public void doSomething() {
        callPrivateMethod();
    }


    private String callPrivateMethod() {
        return "Private method is called.";
    }
}
