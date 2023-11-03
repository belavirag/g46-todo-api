package se.lexicon.g46todoapi.converter;

import se.lexicon.g46todoapi.domain.dto.UserDTOForm;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.User;

public interface UserConverter {
    UserDTOView toUserDTO(User user);
    User toUserEntity(UserDTOView view);
    User toUserEntity(UserDTOForm form);
}
