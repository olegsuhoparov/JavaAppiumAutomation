import org.junit.Assert;
import org.junit.Test;

public class MainClassTest{

    MainClass cls = new MainClass();

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Class string expected contains 'hello' or 'Hello', but we have " + cls.getClassString(),
                cls.getClassString().toLowerCase().contains("hello"));

//         через логическое "или"

//        Assert.assertTrue("Class string expected contains 'hello' or 'Hello', but we have " + cls.getClassString(),
//                cls.getClassString().contains("Hello") | cls.getClassString().contains("hello"));
    }
}
