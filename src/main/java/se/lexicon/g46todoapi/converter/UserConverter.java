package se.lexicon.g46todoapi.converter;

import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.User;

public interface UserConverter {
    UserDTOView toUserDTOView(User user);
    User toUserEntity(UserDTOView view);
}
