package jsondataentity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MainEntity {

    protected List<DataEntity> data;
}
