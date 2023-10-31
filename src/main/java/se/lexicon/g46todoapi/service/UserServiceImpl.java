package se.lexicon.g46todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.lexicon.g46todoapi.converter.RoleConverter;
import se.lexicon.g46todoapi.converter.UserConverter;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;
import se.lexicon.g46todoapi.domain.dto.UserDTOForm;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.domain.entity.User;
import se.lexicon.g46todoapi.exception.DataDuplicateException;
import se.lexicon.g46todoapi.exception.DataNotFoundException;
import se.lexicon.g46todoapi.repository.RoleRepository;
import se.lexicon.g46todoapi.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private final RoleConverter roleConverter;
  private final UserConverter userConverter;

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(RoleConverter roleConverter, UserConverter userConverter, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.roleConverter = roleConverter;
    this.userConverter = userConverter;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDTOView register(UserDTOForm userDTOForm) {
    // check params
    if (userDTOForm == null) throw new IllegalArgumentException("user form is null.");
    // check if email exist
    boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
    if (isExistEmail) throw new DataDuplicateException("Email is already exist.");

    // Retrieve and validate roles
    Set<Role> roleList = userDTOForm.getRoles()
            .stream()
            .map(roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                 .orElseThrow(() -> new DataNotFoundException("Role is not valid.")))
            .collect(Collectors.toSet());


    // Convert dto to entity
    User user = new User(userDTOForm.getEmail(), passwordEncoder.encode(userDTOForm.getPassword()));
    user.setRoles(roleList);

    // Create a new User entity
    User savedUser = userRepository.save(user);

    // convert saved user to dto with converting roles
    UserDTOView dtoView = new UserDTOView();
    dtoView.setEmail(savedUser.getEmail());

    dtoView.setRoles(user.getRoles().stream().map(roleConverter::toRoleDTOView).collect(Collectors.toSet()));

    //& return the result
    return dtoView;
  }

  @Override
  public UserDTOView getByEmail(String email) {
    User foundUser = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("No user found by that email!"));

    return userConverter.toUserDTOView(foundUser);
  }

  @Override
  public void disableByEmail(String email) {
    if (!userRepository.existsByEmail(email)) {
      throw new DataNotFoundException("User with that email does not exist!");
    }
    userRepository.updateExpiredByEmail(email, true);
  }

  @Override
  public void enableByEmail(String email) {
    if (!userRepository.existsByEmail(email)) {
      throw new DataNotFoundException("User with that email does not exist!");
    }
    userRepository.updateExpiredByEmail(email, false);
  }
}
