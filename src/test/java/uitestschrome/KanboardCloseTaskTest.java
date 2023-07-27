package uitestschrome;
import com.codeborne.selenide.Configuration;
import dataproviders.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;


public class KanboardCloseTaskTest {
    private final String BASE_URL = "http://localhost/login";
    private final String CLOSE_TASK_ENDPOINT = "https://localhost:80/jsonrpc.php/tasks/close";
    private TaskPage taskPage;
    private String authToken;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        RestAssured.baseURI = "https://localhost:80";
        RestAssured.basePath = "/jsonrpc.php";

        taskPage = new TaskPage();
    }

    @Test(dataProvider = "taskClose", dataProviderClass = TestDataProvider.class)
    public void closeTaskTest(int taskId) {
        open("http://localhost/dashboard/2/project_id/tasks/" + taskId);
        taskPage.closeTask();

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .body("{\"task_id\":" + taskId + "}")
                .post(CLOSE_TASK_ENDPOINT);

        response.then().statusCode(200);
        response.prettyPrint();
    }

}
