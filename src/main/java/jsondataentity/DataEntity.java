package jsondataentity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataEntity {

    private ParamEntity param;
    private String statusCode;
}
