package helpers;

import constants.PetStatus;
import models.Pet.Category;
import models.Pet.Pet;

public class PetHelper extends CommonHelper {

    public static Pet generatePet(long id, Category category, String petName, PetStatus status){

        return Pet.builder()
                .id(id)
                .category(category)
                .name(petName)
                .status(status)
                .build();
    }
}
