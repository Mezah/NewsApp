import com.hazem.business.TopHeadersUseCase
import com.hazem.business.di.BusinessModule
import com.hazem.entities.Languages
import com.hazem.entities.Result
import com.hazem.network.di.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.StringQualifier
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*

class TestHeadLineUseCase : KoinTest {

    private val headerUsecase: TopHeadersUseCase by inject(StringQualifier(BusinessModule.TOP_HEADER))

    @Before
    fun setup() {
        startKoin {
            modules(
                listOf(
                    NetworkModule("https://newsapi.org/v2/", "84e50b1d40064a60812b2859a547260e"),
                    BusinessModule()
                )

            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testHeadLineUseCaseSuccess() = runBlocking {
        val params = WeakHashMap<String,String>()
        params["Language"] = Languages.EN.langName
        params["Source"] = ""
        val result = headerUsecase(params)
        Assert.assertNotNull("Error:Result shouldn't be Null",result)
        Assert.assertTrue("Error: This result should be succcess", result is Result.Success)
        when(result){
            is Result.Success -> {
             val headLines = result.data
                Assert.assertNotNull("Error:HeadLines Shouldn't be null",headLines)
                Assert.assertFalse("Error:HeadLines Shouldn't be null or Empty",headLines.isNullOrEmpty())
            }

        }
    }

    @Test
    fun testHeadLineUseCaseFailed() = runBlocking {
        val params = WeakHashMap<String,String>()
        params["Language"] = Languages.NONE.langName
        params["Source"] = ""
        val result = headerUsecase(params)
        Assert.assertNotNull("Error:Result shouldn't be Null",result)
        Assert.assertTrue("Error: This result should be succcess", result is Result.ApiError)
        when(result){
            is Result.ApiError -> {
                val errorMessage = result.errorMessage
                println("======== $errorMessage =========")
                Assert.assertNotNull("Error:HeadLines Shouldn't be null",errorMessage)
                Assert.assertFalse("Error:HeadLines Shouldn't be null or Empty",errorMessage.isEmpty())
            }

        }
    }

}