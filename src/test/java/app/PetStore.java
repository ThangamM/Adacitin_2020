package petStoreWithBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PetStore {
    static final String URL = "https://petstore3.swagger.io/";
    static final String PET_ENDPOINT = "/api/v3/pet";
    static final String ORDER_ENDPOINT = "api/v3/store/order";
    @Test(dataProvider = "newEntries")
    public void createEntries(String name, int id, String status) {
        RestAssured.baseURI = URL;
        RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(new Pet(name, id, status))
                .when().log().all().post(PET_ENDPOINT)
                .then().statusCode(200).extract().response().toString();
        verifyPetsWithIdNameStatus(name, id, status);
    }

    public void verifyPetsWithIdNameStatus(String name, int id, String status) {
        RestAssured.baseURI = URL;
        Pet myPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/pet/" + id)
                .as(Pet.class);
        Assert.assertEquals(id, myPet.getId());
        Assert.assertEquals(name, myPet.getName());
        Assert.assertEquals(status, myPet.getStatus());
    }

    @DataProvider(name = "newEntries")
    public Object[][] newPetWithNames() {
        return new Object[][]{
                {"Pegasus", 10, "available"},
                {"Unicorn", 11, "available"},
                {"Snake-Turtle", 12, "available"},
                {"Ferret", 20, "pending"},
                {"Parrot", 21, "pending"},
                {"Brontosaurus", 400, "available"},
                {"Rabbit", 400, "available"},
                {"Fish", 400, "available"},
                {"Octopus", 400, "available"},
                {"StarFish", 400, "available"},
                {"T-Rex", 500, "available"}
        };
    }

    @Test(dataProvider = "newEntriesWithCategoriesTag")
    public void petsWithNamesAndCategories(String name, int id, String status, Category category, Tag[] tags) {
        RestAssured.baseURI = URL;
        Pet myPet = new Pet(name, id, status).petWithCategory(category).petWithTag(tags);
        Pet createPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(myPet)
                .when().log().all().post(PET_ENDPOINT)
                .as(Pet.class);
        Pet getPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().get("/api/v3/pet/" + myPet.id)
                .as(Pet.class);
        Assert.assertEquals(createPet.getCategory().getName(), getPet.getCategory().getName());

    }

    @DataProvider(name = "newEntriesWithCategoriesTag")
    public Object[][] newPetWithNamesCategoriesAndTag() {
        return new Object[][]{
                {"Fairy", 100, "available", new Category("Imaginary", 901), new Tag[]{new Tag("Tale", 901), new Tag("Movie", 902)}},
                {"SuperDoggie", 101, "available", new Category("Imaginary", 902), new Tag[]{new Tag("Childhood", 903)}},
                {"Snake", 102, "available", new Category("Real", 903), new Tag[]{new Tag("Scary", 904)}},
                {"Peacock", 103, "available", new Category("Real", 903), new Tag[]{new Tag("Beautiful", 905)}},
                {"Cat", 104, "available", new Category("Real", 903), new Tag[]{new Tag("Kitty", 906)}},
        };
    }

    @Test
    public void retrievePetsWithStatusPending() {
        RestAssured.baseURI = URL;
        Pet pet1 = new Pet("Parrot", 21, "pending");
        Pet pet2 = new Pet("Ferret", 20, "pending");
        Pet[] newPets = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/pet/findByStatus?status=pending")
                .as(Pet[].class);
        Assert.assertTrue(Arrays.stream(newPets).filter(pet -> pet.getName().equals(pet1.getName())).count() > 0);
        Assert.assertTrue(Arrays.stream(newPets).filter(pet -> pet.getName().equals(pet2.getName())).count() > 0);
    }

    @Test
    public void updatePetByRenaming() {
        RestAssured.baseURI = URL;
        Pet updatePet = new Pet("Diplodocus", 400, "available");
        String response = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(updatePet)
                .when().log().all().put(PET_ENDPOINT)
                .then().statusCode(200).extract().response().toString();
        System.out.println(response);
        verifyUpdatedPet(updatePet);
    }

    public void verifyUpdatedPet(Pet pet) {
        RestAssured.baseURI = URL;
        Pet myPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/pet/" + pet.id)
                .as(Pet.class);
        Assert.assertTrue(pet.name.contains("Diplodocus"));
    }

    @Test
    public void delete() {
        RestAssured.baseURI = "http://localhost:8080/";
        Pet deletePet = new Pet("T-Rex", 500, "available");
        String response = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(deletePet)
                .when().log().all().delete("/api/v3/pet/" + deletePet.id)
                .then().statusCode(200).extract().response().toString();
        System.out.println(response);
        verifyDeletedPet(deletePet);
    }

    public void verifyDeletedPet(Pet pet) {
        RestAssured.baseURI = URL;
        Pet myPet = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/pet/" + pet.id)
                .as(Pet.class);
        Assert.assertEquals(myPet.getId(), "Pet not found");
    }

    @Test(dataProvider = "createOrder")
    public void addFivePetsToNewOrder(Order order) {
        RestAssured.baseURI = URL;
        Order myOrder = new Order(order.id, order.petId, order.quantity, order.shipDate, order.status, order.complete);
        RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(myOrder)
                .when().log().all().post(ORDER_ENDPOINT)
                .then().statusCode(200).extract().response().toString();
        verifyOrder(myOrder);
    }

    public void verifyOrder(Order order) {
        RestAssured.baseURI = URL;
        Order myOrder = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/store/order/" + order.id)
                .as(Order.class);
        Assert.assertEquals(myOrder.getPetId(), order.getPetId());
    }

    @DataProvider(name = "createOrder")
    public Object[][] newPetsToOrder() {
        return new Object[][]{
                {new Order(5, (Integer) newPetWithNamesCategoriesAndTag()[0][1], 5, "2021-09-03T18:57:18.572Z", "approved", true)},
                {new Order(6, (Integer) newPetWithNamesCategoriesAndTag()[1][1], 2, "2021-09-04T18:57:18.572Z", "approved", true)},
                {new Order(7, (Integer) newPetWithNamesCategoriesAndTag()[2][1], 3, "2021-09-03T18:57:18.572Z", "approved", true)},
                {new Order(8, (Integer) newPetWithNamesCategoriesAndTag()[3][1], 3, "2021-09-05T18:57:18.572Z", "approved", true)},
                {new Order(9, (Integer) newPetWithNamesCategoriesAndTag()[4][1], 3, "2021-09-03T18:57:18.572Z", "approved", true)},
        };
    }

    @Test(dataProvider = "multiOrder")
    public void multipleOrders(Order order) {
        RestAssured.baseURI = URL;
        Order myOrder = new Order(order.id, order.petId, order.quantity, order.shipDate, order.status, order.complete);
        RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .body(myOrder)
                .when().log().all().post(ORDER_ENDPOINT)
                .then().statusCode(200).extract().response().toString();
        retrieveInventoryOfStatus(myOrder);
    }

    public void retrieveInventoryOfStatus(Order order) {
        RestAssured.baseURI = URL;
        Inventory inventory = RestAssured
                .given()
                .log().all().contentType(ContentType.JSON)
                .when().log().all().get("/api/v3/store/inventory")
                .as(Inventory.class);
        System.out.println(inventory.getPendingCount());
        System.out.println(inventory.getActiveCount());
        System.out.println(inventory.getSoldCount());
    }

    @DataProvider(name = "multiOrder")
    public Object[][] multipleOrdersWithDifferentPets() {
        return new Object[][]{
                {new Order(15, (Integer) newPetWithNames()[0][1], 6, "2021-09-03T18:57:18.572Z", "sold", true)},
                {new Order(16, (Integer) newPetWithNames()[1][1], 4, "2021-09-04T18:57:18.572Z", "pending", true)},
                {new Order(17, (Integer) newPetWithNames()[2][1], 3, "2021-09-03T18:57:18.572Z", "active", true)},
                {new Order(18, (Integer) newPetWithNames()[3][1], 3, "2021-09-05T18:57:18.572Z", "sold", true)},
                {new Order(19, (Integer) newPetWithNames()[4][1], 3, "2021-09-03T18:57:18.572Z", "pending", true)},
                {new Order(20, (Integer) newPetWithNames()[5][1], 3, "2021-09-03T18:57:18.572Z", "active", true)},
                {new Order(21, (Integer) newPetWithNames()[6][1], 3, "2021-09-03T18:57:18.572Z", "pending", true)},
                {new Order(22, (Integer) newPetWithNames()[7][1], 3, "2021-09-03T18:57:18.572Z", "active", true)},
                {new Order(23, (Integer) newPetWithNames()[8][1], 3, "2021-09-03T18:57:18.572Z", "pending", true)},
                {new Order(24, (Integer) newPetWithNames()[9][1], 3, "2021-09-03T18:57:18.572Z", "sold", true)}
        };
    }

}
