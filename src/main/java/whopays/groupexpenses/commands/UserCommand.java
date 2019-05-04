package whopays.groupexpenses.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class UserCommand {
    private String id;
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
