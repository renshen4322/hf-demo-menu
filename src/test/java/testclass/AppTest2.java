package testclass;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertEquals;

public class AppTest2 {

    @Test
    public void test() {
        App app = new App();

        assertEquals("Private method is called.", //
                ReflectionTestUtils.invokeMethod(app, "callPrivateMethod", null));
    }

}
