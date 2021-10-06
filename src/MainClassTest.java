import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{
    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Local number expected 14, but we have " + this.getLocalNumber(),
                this.getLocalNumber() == 14);
    }
}
