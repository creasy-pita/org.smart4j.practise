import org.junit.Assert;
import org.junit.Test;
import org.smart4j.framwork.util.StringUtil;

/**
 * Created by creasypita on 8/28/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class StringUtilTest {
    @Test
    public void test()
    {
        String[]  array = StringUtil.splitString(null,",");
        Assert.assertEquals(null,array);
    }
}
