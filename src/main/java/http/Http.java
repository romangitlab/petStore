package http;

import helpers.CommonHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Model;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Http {

    static final Logger logger = Logger.getLogger(Http.class);

    public static Response post(String url, Model body) {
        Response response =
                given().
                        header("Content-Type", "application/json").
                        header("Accept", "application/json").
                body(body).
                when().
                    post(url);

        logger.info("REQUEST URL: " + url);
        logger.info("POST REQUEST BODY: " + CommonHelper.extractPOJO(body));
        logger.info("POST RESPONSE BODY: " + response.asString());

        return response;
    }

    public static Response delete(String url) {

        Response response =
                given().
                        header("Content-Type", "application/json").
                        header("Accept", "application/json").
                when().
                delete(url);

        logger.info("REQUEST URL: " + url);
        logger.info("DELETE RESPONSE BODY: " + response.asString());

        return response;
    }

    public static Response get(String url) {

        Response response =
                given().
                        header("Content-Type", "application/json").
                        header("Accept", "application/json").
                when().
                get(url);

        logger.info("REQUEST URL: " + url);
        logger.info("GET RESPONSE BODY: " + response.asString());

        return response;
    }
}
