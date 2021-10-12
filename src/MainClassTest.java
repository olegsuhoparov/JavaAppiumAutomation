import org.junit.Assert;
import org.junit.Test;

public class MainClassTest{

    MainClass cls = new MainClass();

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Class number expected more than 45, but we have " + cls.getClassNumber(),
                cls.getClassNumber() > 45);
    }
}
