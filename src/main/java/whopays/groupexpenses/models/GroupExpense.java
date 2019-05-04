package whopays.groupexpenses.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Document("GroupExpenses")
public class GroupExpense {

    @Id
    private String id;
    private String concept;
    private long totalQuantity;

    private Map<User, Integer> debtors;

    private Map<User, Integer> payers;
    private Date date;
    private Category categoryId;

}
