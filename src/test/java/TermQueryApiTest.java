import apihelper.MainApiHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import responseentity.ResponseEntity;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static io.qameta.allure.SeverityLevel.NORMAL;

public class TermQueryApiTest extends MainApiHelper {

    private static final String TERM_DATA = "termData.json";

    @DataProvider(name = "term", parallel = true)
    private Object[][] testDataProvider() throws Exception {
        return getTestDataToDataProvider(TERM_DATA);
    }

    @Test(groups = {"regression", "term"}, dataProvider = "term")
    @Description("Test Term Queries with different parameters.")
    @Owner("Anar Gurbanov")
    @Severity(NORMAL)
    @Epic("iTunes Search API")
    @Feature("Parameter Key - Term")
    @Story("Term Queries automation.")
    @Issue("Jira-001")
    public void termTest(Object requestParams, String statusCode) throws JsonProcessingException {
        Allure.step("Execute GET request based on dataProvider's parameters.");
        Response httpResponse = httpGetWithParams((Map<?, ?>) requestParams);

        Allure.step("Verify statusCode.");
        Assert.assertEquals(httpResponse.getStatusCode(), Integer.parseInt(statusCode), "Status code validation.");
        ResponseEntity responseEntity = parseResponseEntity(httpResponse.asString());

        if (httpResponse.getStatusCode() != 200) {
            Allure.step("Verify error message.");
            Assert.assertTrue(responseEntity.getErrorMessage().startsWith("Invalid value(s) for key(s): "), "'errorMessage' validation.");
        }

        if (getLimitParam(requestParams) != null && responseEntity.getResultCount() != null) {
            Allure.step("Verify resultCount.");
            Assert.assertEquals(responseEntity.getResultCount(), getLimitParam(requestParams), "'resultCount' validation.");
        }
    }
}
