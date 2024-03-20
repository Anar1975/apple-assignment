package jsondataentity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamEntity {

    private String term;
    private String country;
    private String limit;
    private String entity;
    private String lang;
}
