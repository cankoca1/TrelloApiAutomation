package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloSteps {

    private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
    private static final String API_KEY = "84990e6c23ea52cb11818a46cff7d7b1";
    private static final String API_TOKEN = "ATTAe4473f92e8c8ff496bc7a63a0cd805f5acfe70ab1975d51f5ab99a010b062b5f058039A7";
    private static final String BASE_URL = "https://api.trello.com/1";

    private static String token;
    private static Response response;


    @Then("I create a board on trello")
    public void ıCreateABoardOnTrello() {

        // CreateBoard
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .queryParam("key", "84990e6c23ea52cb11818a46cff7d7b1")
                .queryParam("token", "ATTAe4473f92e8c8ff496bc7a63a0cd805f5acfe70ab1975d51f5ab99a010b062b5f058039A7")
                .queryParam("name", "Test Board")
                        .post("/boards");

        //GET BoardId
        Response response2 = RestAssured.given()
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .header("Accept", "application/json")
                .get("/boards/{id}");

        //CreateList
        Response response3 = RestAssured.given()
                .queryParam("name", "Test Board")
                .queryParam("idBoard", response2.toString())
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .contentType(ContentType.JSON)
                .post("/lists");


        // Print response body
        System.out.println(response.getBody().asString());
    }

    @Then("I create two card on trello")
    public void ıCreateTwoCardOnTrello() {
        // GET List
        Response response4 = RestAssured.given()
                .queryParam("key", "APIKey")
                .queryParam("token", "APIToken")
                .header("Accept", "application/json")
                .get("/lists/{id}");
        String responseText = response.getBody().asString();

        //CreateCards(1)
        Response response5 = RestAssured.given()
                .queryParam("idList", "5abbe4b7ddc1b351ef961414")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .post("/cards");

        //CreateCards(2)
        Response response6 = RestAssured.given()
                .queryParam("idList", "5abbe4b7ddc1b351ef961414")
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .post("/cards");    }

    @And("I update on of these cards randomly")
    public void ıUpdateOnOfTheseCardsRandomly() {
        // UPDATE request

        Response response7 = RestAssured.given()
                .pathParam("id", "{id}")
                .queryParam("key", "APIKey")
                .queryParam("token", "APIToken")
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .put("/cards/{id}");
    }

    @Then("I delete the cards")
    public void ıDeleteTheCards() {
        String id = "card_id";
        Response response = RestAssured
                .given()
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .delete("https://api.trello.com/1/cards/{id}", id);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        
    }

    @Then("I delete the board")
    public void ıDeleteTheBoard() {
        String id = "board_id";
        Response response = RestAssured
                .given()
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .delete("https://api.trello.com/1/boards/{id}", id);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
