package whopays.groupexpenses.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import whopays.groupexpenses.commands.UserCommand;
import whopays.groupexpenses.models.User;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Override
    public User convert(UserCommand source) {
        if (source == null) {
            return null;
        }

        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
//        user.setGroupsList(source.getGroupsId());
        return user;
    }
}
