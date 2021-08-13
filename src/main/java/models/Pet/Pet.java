package models.Pet;

import constants.PetStatus;
import lombok.*;
import models.Model;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pet implements Model {

    private long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Category[] tags;
    private PetStatus status;
}
