package hsos.swa.anzeige.boundry;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import io.restassured.response.Response;

import java.io.StringReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnzeigeRestTest {

    JsonObject itemToCheckFor;

    @BeforeEach
    void setUp() {
        JsonObject beschreibung = Json.createObjectBuilder()
                .add("beschreibung", "Eine wunderschöne Halskette, sehr zu empfehlen")
                .build();

        itemToCheckFor = Json.createObjectBuilder()
                .add("name", "Choker")
                .add("preis", "1998")
                .add("beschreibung", beschreibung)
                .build();
    }

    @Test
    @Order(1)
    void createAnzeige() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "NameRandom")
                .add("preis", "1998")
                .build();

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/api/v1/anzeige")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    void getAnzeigeById() {
        // Erstelle zuerst eine Anzeige, um eine gültige ID zu erhalten
        Response createResponse = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemToCheckFor.toString())
                .when()
                .post("/api/v1/anzeige")
                .then()
                .statusCode(201)
                .extract()
                .response();

        // Extrahiere die ID aus der erstellten Anzeige
        long id = createResponse.jsonPath().getLong("id");

        // Rufe die Anzeige über die ID ab und erhalte die JSON-Antwort als String
        String responseString = given()
                .pathParam("id", id)
                .when()
                .get("/api/v1/anzeige/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        // Wandle den JSON-String in ein JsonObject um
        JsonObject anzeigeEntity = Json.createReader(new StringReader(responseString)).readObject();

        // Überprüfe die ID des extrahierten Objekts
        long responseId = anzeigeEntity.getJsonNumber("id").longValue();
        assertEquals(id, responseId);
    }
/*
    @Test
    @Order(3)
    void getAnzeigeByName() {
        // Erstelle zuerst eine Anzeige, um eine gültigen Namen zu erhalten
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemToCheckFor.toString())
                .when()
                .post("/api/v1/anzeige")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        // Rufe die Anzeige über den Namen ab
        given()
                .queryParam("name", "Choker")
                .when()
                .get("/api/v1/anzeige")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .assertThat()
                .body("name", hasItem("Choker"));
    }

*/
    @Test
    void updateAnzeige() {
    }

    @Test
    void deleteAnzeige() {
    }
}
