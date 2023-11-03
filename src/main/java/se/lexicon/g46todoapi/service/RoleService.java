package se.lexicon.g46todoapi.service;

import se.lexicon.g46todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;

import java.util.List;
import java.util.Optional;

public interface RoleService {

  List<RoleDTOView> getAll();
  Optional<RoleDTOView> findById(Long id);
  Optional<RoleDTOView> create(RoleDTOForm form);
  Optional<RoleDTOView> update(RoleDTOForm form);

}
