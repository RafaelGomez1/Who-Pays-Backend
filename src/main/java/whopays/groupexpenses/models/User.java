package whopays.groupexpenses.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "Users")
public class User {

    @Id
    private String id;
    @NotNull
    @NotBlank
    @Size(max = 35)
    @Indexed
    private String username;
    private String password;
    private List<String> groupsList;

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

    public List<String> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<String> groupsList) {
        this.groupsList = groupsList;
    }
}
