package se.lexicon.g46todoapi.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g46todoapi.converter.RoleConverter;
import se.lexicon.g46todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleConverter roleConverter;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
    this.roleRepository = roleRepository;
    this.roleConverter = roleConverter;
  }

  @Override
  public List<RoleDTOView> getAll() {
    List<Role> roles = roleRepository.findAll();
    List<RoleDTOView> roleDTOList = new ArrayList<>();
    for (Role entity : roles) {
      roleDTOList.add(roleConverter.toRoleDTOView(entity));
    }
    return roleDTOList;
  }

  @Override
  public Optional<RoleDTOView> findById(Long id) {
    return roleRepository.findById(id).map(roleConverter::toRoleDTOView);
  }

  @Override
  public Optional<RoleDTOView> create(@NonNull RoleDTOForm form) {
    Role role = roleRepository.save(roleConverter.toRoleEntity(form));
    return Optional.of(roleConverter.toRoleDTOView(role));
  }

  @Override
  public Optional<RoleDTOView> update(@NonNull RoleDTOForm form) {
    Role role = roleRepository.update(roleConverter.toRoleEntity(form));
    return Optional.of(roleConverter.toRoleDTOView(role));
  }

}
