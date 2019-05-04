package whopays.groupexpenses.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document(collection = "Users")
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private Set<String> groupsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(Set<String> groupsId) {
        this.groupsId = groupsId;
    }
}
