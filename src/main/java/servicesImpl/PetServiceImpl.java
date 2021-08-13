package servicesImpl;

import constants.PetStatus;
import helpers.PetHelper;
import http.Http;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Pet.Category;
import models.Pet.Pet;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import services.PetService;

import java.util.Random;

public class PetServiceImpl implements PetService {

    static final Logger logger = Logger.getLogger(PetServiceImpl.class);
    private final String endpoint_pet = "pet/";
    private final String endpoint_petByStatus = endpoint_pet + "findByStatus?status=";


    public Pet createPet() {

        logger.info("- - - CREATE PET - - - -");

        Pet pet = PetHelper.generatePet(
                Math.abs(new Random().nextLong()) % 1000,
                new Category(),
                RandomStringUtils.randomAlphabetic(5),
                PetStatus.available
        );

        Response response = Http.post(URI+endpoint_pet, pet);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.contentType().contains(ContentType.JSON.toString()));

        return response.as(Pet.class);
    }

    public Pet updatePet(Pet pet) {
        logger.info("- - - UPDATE PET - - - -");

        Pet updatedPet = PetHelper.generatePet(
                pet.getId(),
                new Category(),
                pet.getName(),
                PetStatus.sold
        );

        Response response = Http.post(URI+endpoint_pet, updatedPet);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.contentType().contains(ContentType.JSON.toString()));

        return response.as(Pet.class);
    }

    public String deletePet(Pet pet) {
        logger.info("- - - DELETE PET - - - -");

        Response response = Http.delete(URI+endpoint_pet+pet.getId());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.contentType().contains(ContentType.JSON.toString()));

        return response.asString();
    }

    public String getPetByStatus(PetStatus petStatus) {
        logger.info("- - - GET BY STATUS PET - - - -");

        Response response = Http.get(URI+endpoint_petByStatus+petStatus);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.contentType().contains(ContentType.JSON.toString()));

        return response.asString();
    }

    public Pet getPet(Pet pet) {
        logger.info("- - - GET PET - - - -");

        Response response = Http.get(URI+endpoint_pet+pet.getId());

        if(response.statusCode() == 404){ //retry attempt
            response = Http.get(URI+endpoint_pet+pet.getId());
        }

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.contentType().contains(ContentType.JSON.toString()));

        return response.as(Pet.class);
    }
}
