package se.lexicon.g46todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.User;

import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {
    @Autowired
    private RoleConverter roleConverter;

    @Override
    public UserDTOView toUserDTOView(User user) {
        return new UserDTOView(user.getEmail(), user.getRoles().stream().map(roleConverter::toRoleDTOView).collect(Collectors.toSet()));
    }

    @Override
    public User toUserEntity(UserDTOView view) {
        User user = new User();
        user.setEmail(view.getEmail());

        for (RoleDTOView roleView : view.getRoles()) {
            user.addRole(roleConverter.toRoleEntity(roleView));
        }

        return user;
    }
}
