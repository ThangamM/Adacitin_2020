package petStoreWithBDD;

import com.sun.xml.xsom.impl.scd.Iterators;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class MyStepdefs {
    static final String URL = "https://petstore3.swagger.io/";
    static final String PET_ENDPOINT = "/api/v3/pet";
    Response response;

    @Given("^The end point exists$")
    public void theEndPointExists() {
        RestAssured.baseURI = URL;
    }

    @When("^I create three new pets with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iCreateThreeNewPetsWith(String name, int id, String status) {
        response = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(new Pet(name, id, status))
                .when().log().all().post(PET_ENDPOINT)
                .then().extract().response();
    }

    @Then("^Response status code should be (\\d+)$")
    public void responseStatusCodeShouldBe(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @And("^Retrieve the pet and check the name is the same \"([^\"]*)\" \"([^\"]*)\"$")
    public void retrieveThePetAndCheckTheNameIsTheSame(String name, int id) throws Throwable {
        Pet myPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/pet/" + id)
                .as(Pet.class);
        Assert.assertEquals(id, myPet.getId());
        Assert.assertEquals(name, myPet.getName());
    }

    @When("^Pets are added successfully$")
    public void petsAreAddedSuccessfully() {

    }

    @Then("^Pets with name are added to the store$")
    public void petsWithNameAreAddedToTheStore() {
    }

    @Given("^When I create three new pets with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void whenICreateThreeNewPetsWith(String name, int id, String status, Category category, Tag[] tags) throws Throwable {
        RestAssured.baseURI = URL;
        Pet myPet = new Pet(name, id, status).petWithCategory(category).petWithTag(tags);
        Pet createPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(myPet)
                .when().log().all().post(PET_ENDPOINT)
                .as(Pet.class);
    }

    @Given("^When I create three new pets with$")
    public void whenICreateThreeNewPetsWith(DataTable table) {
        RestAssured.baseURI = URL;
        List<Map<Object, Object>> maps = table.asMaps(Object.class, Object.class);
        for (Map<Object, Object> mapList : maps
        ) {
            /*String name = (String) mapList.get("name");
            int id = 0;
            id = (int) mapList.get(id);
            String status = (String) mapList.get("status");
            Category category= (Category) mapList.get("categoryName");
            Tag[] tags= (Tag[]) mapList.get("tagName");*/
            Pet myPet = new Pet((String) mapList.get(0),(int) mapList.get(1), (String) mapList.get(2)).petWithCategory().petWithTag(tags);
            Pet createPet = RestAssured
                    .given()
                    .log().all().contentType(ContentType.JSON)
                    .body(myPet)
                    .when().log().all().post(PET_ENDPOINT)
                    .as(Pet.class);
        }

    }
}
