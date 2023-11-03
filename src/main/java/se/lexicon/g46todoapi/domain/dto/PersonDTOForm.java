package se.lexicon.g46todoapi.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTOForm {
    private Long id;
    private String name;
    private List<TaskDTOForm> tasks;

}
