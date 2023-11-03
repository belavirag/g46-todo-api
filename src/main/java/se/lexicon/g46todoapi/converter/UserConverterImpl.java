package se.lexicon.g46todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.UserDTOForm;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.User;

import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {
    @Autowired
    private RoleConverter roleConverter;
    @Override
    public UserDTOView toUserDTO(User user) {
        return UserDTOView.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(roleConverter::toRoleDTOView).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User toUserEntity(UserDTOView view) {
        return User.builder()
                .email(view.getEmail())
                .roles(view.getRoles().stream().map(roleConverter::toRoleEntity).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User toUserEntity(UserDTOForm form) {
        return User.builder()
                .email(form.getEmail())
                .roles(form.getRoles().stream().map(roleConverter::toRoleEntity).collect(Collectors.toSet()))
                .build();
    }
}
