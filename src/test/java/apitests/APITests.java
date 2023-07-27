package apitests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITests {
    private static final String BASE_URL = "http://localhost:80/jsonrpc.php";
    private static final String CREATE_USER_ENDPOINT = BASE_URL + "/createUser";
    private static final String DELETE_USER_ENDPOINT = BASE_URL + "/removeUser";
    private static final String CREATE_PROJECT_ENDPOINT = BASE_URL + "/dashboard/2/projects";
    private static final String DELETE_PROJECT_ENDPOINT = BASE_URL + "/removeProject";
    private static final String CREATE_TASK_ENDPOINT = BASE_URL + "/tasks";
    private static final String DELETE_TASK_ENDPOINT = BASE_URL + "/tasks/1";
    private static final String USERNAME = "annav";
    private static final String PASSWORD = "999999";
    private String authToken;
    private int userId;


    @BeforeMethod
    public void authorizeTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", USERNAME);
        requestBody.put("password", PASSWORD);

        Response authorize = RestAssured.given()
                .basePath("/auth")
                .body(requestBody.toString())
                .post();
        authorize.then().statusCode(200);
        authToken = authorize.getBody().jsonPath().getString("token");
    }

    @Test
    public void createUserTest() {
        CreateUser createUser = CreateUser.builder()
                .username("annav")
                .password("999999")
                .name("Anna")
                .email("volovyk.anna@gmail.com")
                .role("Kanboard User")
                .build();

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(createUser)
                .post(CREATE_USER_ENDPOINT);

        response.then().statusCode(200)
                .body("user_id", equalTo(1));
        response.prettyPrint();

        userId = response.getBody().jsonPath().getInt("user_id");
    }

    @Test
    public void createProjectTest() {
        CreateProject createProject = CreateProject.builder()
                .name("NewProject")
                .description("This is my first project")
                .identifier("999")
                .build();

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(createProject)
                .post(CREATE_PROJECT_ENDPOINT);

        response.then().statusCode(200)
                .body("project_id", equalTo(999));
        response.prettyPrint();
    }

    @Test
    public void createTaskTest() {
        CreateTask createTask = CreateTask.builder()
                .title("NewTask")
                .project_id(999)
                .build();

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(createTask)
                .post(CREATE_TASK_ENDPOINT);

        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteTaskTest() {
        int taskToDelete = 1;

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .param("task_id", taskToDelete)
                .post(DELETE_TASK_ENDPOINT);

        response.then().statusCode(200)
                .body("result", equalTo(true));
        response.prettyPrint();
    }
    @Test
    public void deleteProjectTest() {
        int projectIdToDelete = 999;

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .param("project_id", projectIdToDelete)
                .when()
                .post(DELETE_PROJECT_ENDPOINT);

        response.then().statusCode(200)
                .body("result", equalTo(true));
        response.prettyPrint();
    }
    @Test
    public void deleteUserTest() {
        int userIdToDelete = 1;

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .param("user_id", userIdToDelete)
                .post(DELETE_USER_ENDPOINT);

        response.then().statusCode(200)
                .body("result", equalTo(true));
        response.prettyPrint();
    }
}



