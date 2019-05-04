package whopays.groupexpenses.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import whopays.groupexpenses.commands.UserCommand;
import whopays.groupexpenses.models.User;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {
    @Override
    public UserCommand convert(User source) {
        if (source == null) {
            return null;
        }
        final UserCommand userCommand = new UserCommand();
        userCommand.setId(source.getId());
        userCommand.setUsername(source.getUsername());
        userCommand.setPassword(source.getPassword());
        userCommand.setGroupsId(source.getGroupsId());
        return userCommand;
    }
}
