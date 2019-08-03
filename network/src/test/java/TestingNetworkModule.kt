import com.google.gson.Gson
import com.hazem.entities.Languages
import com.hazem.entities.NewsApiError
import com.hazem.entities.clients.INewsWebClient
import com.hazem.network.di.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.HttpException

class TestingNetworkModule : KoinTest {

    private val newsWebClient: INewsWebClient by inject()
    private val gson: Gson = Gson()
    @Before
    fun prepare() {
        startKoin {
            modules(
                listOf(NetworkModule("https://newsapi.org/v2/", "INSERT_YOUR_API_KEY_HERE"))
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testLoadingTopHeadersSuccessfully() = runBlocking {
        try {
            val topHeaders = newsWebClient.loadLatestNewsHeaders(Languages.EN)
            Assert.assertTrue(
                "Error: An Error Occurred While loading Latest News Headers",
                topHeaders.status.equals("ok")
            )
            Assert.assertNotNull("Error: News Headers list count should not be null", topHeaders.totalResults)
            Assert.assertFalse("Error: News Headers list should not be Empty or Null", topHeaders.articles.isNullOrEmpty())

        } catch (e: Exception) {
            throw RuntimeException("This call should not fail")
        }

    }

    @Test
    fun testLoadTopHeadersFailed() = runBlocking {
        try {
            val topHeaders = newsWebClient.loadLatestNewsHeaders(Languages.NONE)
            Assert.assertFalse(
                "Error: This call of Api should Fail",
                topHeaders.status.equals("ok")
            )

        } catch (e: java.lang.Exception) {
            if (e is HttpException) {
                Assert.assertTrue("Error: Wrong Status code, this should be 4XX", e.code() >= 400)
                Assert.assertNotNull("Error: No Response Body send from backend", e.response())
                Assert.assertNotNull("Error: No Error Body send from backend", e.response()?.errorBody())
                val errorBody = gson.fromJson(e.response()?.errorBody()?.string(), NewsApiError::class.java)
                Assert.assertNotNull("Error: No Error Json send from backend", errorBody)
                Assert.assertNotNull("Error: No Status Sent from Backend", errorBody.status)
                Assert.assertTrue("Error: Wrong Status sent from backend", errorBody.status.equals("error"))
                //
                Assert.assertFalse("Error: No Status Code sent from backend", errorBody.code.isNullOrEmpty())
                Assert.assertFalse("Error: No Error Message sent from backend", errorBody.message.isNullOrEmpty())
            }
        }

    }

    @Test
    fun testNewsHeadersData() = runBlocking {
        try {
            val topHeaders = newsWebClient.loadLatestNewsHeaders(Languages.EN)
            Assert.assertTrue(
                "Error: An Error Occurred While loading Latest News Headers",
                topHeaders.status.equals("ok")
            )
            Assert.assertNotNull("Error: News Headers list count should not be null", topHeaders.totalResults)
            Assert.assertFalse("Error: News Headers list should not be Empty or Null", topHeaders.articles.isNullOrEmpty())
            val size = topHeaders.articles?.size ?: 0
            if (size != 0) {
                val header = topHeaders.articles?.get(0)
                Assert.assertNotNull("Error:There Should be A header in this position", header)
                Assert.assertNotNull("Error:Header should contains a title", header?.title)
                Assert.assertNotNull("Error:Header should contains a publish date", header?.publishedAt)
                Assert.assertNotNull("Error:Header should contains a logo url", header?.urlToImage)
            }


        } catch (e: java.lang.Exception) {
            throw RuntimeException("This call should not fail")
        }
    }

    @Test
    fun testLoadingNewsSources() = runBlocking {
        try {
            val newsSources = newsWebClient.loadNewsSources()
            Assert.assertTrue(
                "Error: An Error Occurred While loading News sources",
                newsSources.status.equals("ok")
            )
            Assert.assertNotNull("Error: News Sources list should not be null", newsSources.sources)
            Assert.assertFalse("Error: News Sources list should not be Empty or Null", newsSources.sources.isNullOrEmpty())

        } catch (e: Exception) {
            throw RuntimeException("This call should not fail")
        }
    }
}