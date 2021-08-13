package services;


import constants.PetStatus;
import models.Pet.Pet;

public  interface PetService extends BasicService {
    Pet createPet();
    Pet updatePet(Pet pet);
    String deletePet(Pet pet);
    String getPetByStatus(PetStatus status);
    Pet getPet(Pet pet);
}
