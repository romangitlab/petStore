package steps;

import constants.PetStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Pet.Pet;
import servicesImpl.PetServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;
import static utils.RunContext.*;

public class PetStoreDef {
    PetServiceImpl petServiceImpl = new PetServiceImpl();

    @Given("I Get available pets")
    public void iGetAvailablePets() {
        String availablePetsInStore = petServiceImpl.getPetByStatus(PetStatus.available);

        put("availablePetsInStore", availablePetsInStore);
    }

    @Then("I assert expected result")
    public void iAssertExpectedResult() {
        String availablePetsInStore = get("availablePetsInStore", String.class);

        assertThat(availablePetsInStore, not(equalTo("[]")));
        assertThat(availablePetsInStore, containsString("name"));
    }

    @When("I post a new available pet to the store")
    public void iPostANewAvailablePetToTheStore() {
        Pet createdPet = petServiceImpl.createPet();

        put("createdPet", createdPet);
    }

    @Then("I assert new pet added")
    public void iAssertNewPetAdded() {
        Pet createdPet = get("createdPet", Pet.class);

        assertThat(createdPet.getName(), notNullValue());
        assertThat(createdPet.getStatus(), equalTo(PetStatus.available));
    }

    @When("I update this pet status to sold")
    public void iUpdateThisPetStatusToSold() {
        Pet updatedPet = petServiceImpl.updatePet(get("createdPet", Pet.class));

        put("updatedPet", updatedPet);
    }

    @Then("I assert status updated")
    public void iAssertStatusUpdated() {
        Pet updatedPet = get("updatedPet", Pet.class);

        assertThat(updatedPet.getId(), is(not(0L)));
        assertThat(updatedPet.getName(), notNullValue());
        assertThat(updatedPet.getStatus(), equalTo(PetStatus.sold));

        Pet gotUpdatedPet = petServiceImpl.getPet(updatedPet);

        assertThat(updatedPet.getStatus(), equalTo(gotUpdatedPet.getStatus()));

    }

    @When("I delete this pet")
    public void iDeleteThisPet() {
        String responseOfDelete = petServiceImpl.deletePet(get("createdPet", Pet.class));

        put("responseOfDelete", responseOfDelete);
    }

    @Then("I assert deletion")
    public void iAssertDeletion() {
        String responseOfDelete = get("responseOfDelete", String.class);

        assertThat(responseOfDelete, containsString("200"));
    }
}
