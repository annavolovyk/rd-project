package uitestschrome;
import apitests.CreateTask;
import com.codeborne.selenide.Configuration;
import dataproviders.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;

public class KanboardCreateTaskTest {
    private final String BASE_URL = "http://localhost/login";
    private final String CREATE_TASK_ENDPOINT = "https://localhost:80/jsonrpc.php/tasks";
    private TaskPage taskPage;
    private String authToken;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        RestAssured.baseURI = "https://localhost:80";
        RestAssured.basePath = "/jsonrpc.php";

        taskPage = new TaskPage();
    }

    @Test(dataProvider = "taskCreate", dataProviderClass = TestDataProvider.class)
    public void createTaskTest(String title, String description) {

        taskPage.createTask(title, description);

        String taskId = $("#task_id").text();

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
}
