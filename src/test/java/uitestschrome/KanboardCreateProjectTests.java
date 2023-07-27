package uitestschrome;
import apitests.CreateProject;
import com.codeborne.selenide.Configuration;
import dataproviders.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
public class KanboardCreateProjectTests {
    private final String CREATE_PROJECT_ENDPOINT = "https://localhost:80/jsonrpc.php/projects";
    private ProjectPage projectPage;
    private String authToken;


    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        RestAssured.baseURI = "https://localhost:80";
        RestAssured.basePath = "/jsonrpc.php";

        projectPage = new ProjectPage();
    }
    @Test(dataProvider = "projectCreate", dataProviderClass = TestDataProvider.class)
    public void createProjectTest(String name, String description, String identifier) {
        Response loginResponse = given()
                .basePath("/auth")
                .body("{\"username\":\"annav\",\"password\":\"999999\"}")
                .post();
        String authToken = loginResponse.getBody().jsonPath().getString("token");

        projectPage.createProject(name, description, identifier);

        int projectId = Integer.parseInt($("#project_id").text());

        Assert.assertTrue(projectId > 0);

        CreateProject createProject = CreateProject.builder()
                .name(name)
                .description(description)
                .identifier(identifier)
                .build();

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(createProject)
                .post(CREATE_PROJECT_ENDPOINT);

        response.then().statusCode(200)
                .body("project_id", equalTo(projectId));

        response.prettyPrint();
    }
}


