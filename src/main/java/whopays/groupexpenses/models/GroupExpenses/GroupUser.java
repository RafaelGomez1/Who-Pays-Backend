package whopays.groupexpenses.models.GroupExpenses;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Objects;

@Getter
@Setter
public class GroupUser {

    private ObjectId id;
    private String username;
    private String imageURl;

    public GroupUser() {
        this.id = ObjectId.get();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupUser groupUser = (GroupUser) o;
        return Objects.equals(getId(), groupUser.getId()) &&
                Objects.equals(getUsername(), groupUser.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }
}
