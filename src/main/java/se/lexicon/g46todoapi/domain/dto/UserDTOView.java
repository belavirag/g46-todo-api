package se.lexicon.g46todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOView {

  private String email;
  private boolean expired;
  private Set<RoleDTOView> roles;
}
