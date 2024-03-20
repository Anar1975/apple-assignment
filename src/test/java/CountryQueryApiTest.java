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

public class CountryQueryApiTest extends MainApiHelper {

    private static final String COUNTRY_DATA = "countryData.json";

    @DataProvider(name = "country", parallel = true)
    private Object[][] testDataProvider() throws Exception {
        return getTestDataToDataProvider(COUNTRY_DATA);
    }

    @Test(groups = {"regression", "country"}, dataProvider = "country")
    @Description("Test Country Queries with different parameters.")
    @Owner("Anar Gurbanov")
    @Severity(CRITICAL)
    @Epic("iTunes Search API")
    @Feature("Parameter Key - Country")
    @Story("Country Queries automation.")
    @Issue("Jira-002")
    public void countryTest(Object requestParams, String statusCode) throws JsonProcessingException {
        Allure.step("Execute GET request based on dataProvider's parameters.");
        Response httpResponse = httpGetWithParams((Map<?, ?>) requestParams);

        Allure.step("Verify statusCode.");
        Assert.assertEquals(httpResponse.getStatusCode(), Integer.parseInt(statusCode), "Status code validation.");
        ResponseEntity responseEntity = parseResponseEntity(httpResponse.asString());

        if (httpResponse.getStatusCode() != 200) {
            Allure.step("Verify error message.");
            Assert.assertTrue(responseEntity.getErrorMessage().startsWith("Invalid value(s) for key(s): "), "'errorMessage' validation.");
        }

        System.out.println("getLimitParam(requestParams) = " + getLimitParam(requestParams));
        if (getLimitParam(requestParams) != null && responseEntity.getResultCount() != null) {
            Allure.step("Verify resultCount.");
            Assert.assertEquals(responseEntity.getResultCount(), getLimitParam(requestParams), "'resultCount' validation.");
        }
    }
}
