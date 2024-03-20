import apihelper.MainApiHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import responseentity.ResponseEntity;

import java.util.Map;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class LangQueryApiTest extends MainApiHelper {

    private static final String LANG_DATA = "langData.json";

    @DataProvider(name = "lang", parallel = true)
    private Object[][] testDataProvider() throws Exception {
        return getTestDataToDataProvider(LANG_DATA);
    }

    @Test(groups = {"regression", "lang"}, dataProvider = "lang")
    @Description("Test Language Queries with different parameters.")
    @Owner("Anar Gurbanov")
    @Severity(CRITICAL)
    @Epic("iTunes Search API")
    @Feature("Parameter Key - Lang")
    @Story("Language Queries automation.")
    @Issue("Jira-03")
    public void languageTest(Object requestParams, String statusCode) throws JsonProcessingException {
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
