package apihelper;

import apicore.HttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsondataentity.DataEntity;
import jsondataentity.MainEntity;
import responseentity.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MainApiHelper extends HttpRequest {

    public Object[][] getTestDataToDataProvider(String fileName) throws Exception {
        MainEntity entity = getTestData(fileName);
        int count = 0;
        Object[][] result = new Object[entity.getData().size()][];
        Map<?, ?> requestMap;
        for (DataEntity eachData : entity.getData()) {
            requestMap = requestParam(eachData);
            result[count] = new Object[]{requestMap, eachData.getStatusCode()};
            count++;
        }
        return result;
    }

    /**
     * Get Limit parameter value from DataProvider
     */
    public Integer getLimitParam(Object requestParams) {
        for (Map.Entry<?, ?> eachEntry : ((Map<?, ?>) requestParams).entrySet()) {
            if ((eachEntry.getKey().equals("limit"))) {
                return Integer.parseInt((String) eachEntry.getValue());
            }
        }
        return null;
    }

    protected ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    public MainEntity getTestData(String fileName) throws Exception {
        return getObjectMapper().readValue(this.getClass().getClassLoader().getResourceAsStream(fileName), MainEntity.class);
    }

    protected Map<?, ?> requestParam(DataEntity dataEntity) {
        return getObjectMapper().convertValue(dataEntity.getParam(), HashMap.class);
    }

    protected TypeReference<ResponseEntity> getResponseEntity() {
        return new TypeReference<>() {
        };
    }

    public ResponseEntity parseResponseEntity(String response) throws JsonProcessingException {
        return getObjectMapper().readValue(response, getResponseEntity());
    }
}
