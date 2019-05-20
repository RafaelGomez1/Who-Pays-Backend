package whopays.groupexpenses.models.GroupExpenses;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "Groups")
public class Group {

    @Id
    private ObjectId id;
    private String groupName;
    private Date creationDate;
    private List<GroupUser> members;
    private List<GroupUser> admins;
    private List<GroupExpense> groupExpenses;
    private String imageURl;

    public Group() {
        this.id = ObjectId.get();
        this.admins = new ArrayList<>();
        this.members = new ArrayList<>();
        this.groupExpenses = new ArrayList<>();
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<GroupUser> getMembers() {
        return members;
    }

    public void setMembers(List<GroupUser> members) {
        this.members = members;
    }

    public List<GroupUser> getAdmins() {
        return admins;
    }

    public void setAdmins(List<GroupUser> admins) {
        this.admins = admins;
    }

    public List<GroupExpense> getGroupExpenses() {
        return groupExpenses;
    }

    public void setGroupExpenses(List<GroupExpense> groupExpenses) {
        this.groupExpenses = groupExpenses;
    }
}