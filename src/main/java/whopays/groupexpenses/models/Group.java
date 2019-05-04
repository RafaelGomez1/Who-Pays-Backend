package whopays.groupexpenses.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Document(collection = "Groups")
public class Group {

    @Id
    private String id;
    private String groupName;
    private Date creationDate;
    private Set<User> members;
    private Set<User> admins;

    @DBRef
    private Set<GroupExpense> groupExpenses;
}
