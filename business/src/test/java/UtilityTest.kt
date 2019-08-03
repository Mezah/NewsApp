import com.hazem.business.util.headLinesDateFormatter
import org.junit.Assert
import org.junit.Test

class UtilityTest {

    @Test
    fun testDateConverter(){
        val date = "2019-08-03T13:48:00Z"
        val formatterDate = headLinesDateFormatter(date)
        println(formatterDate)
        Assert.assertTrue("Error:A problem while formatting Date from api", formatterDate == "3 Aug 19")
    }
}