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
@Document
public class GroupExpense {

    @Id
    private String id;
    private String concept;
    private long totalQuantity;

    @DBRef
    private Map<User, Integer> debtors;

    @DBRef
    private Map<User, Integer> payers;
    private Date date;
    private Category category;

}
