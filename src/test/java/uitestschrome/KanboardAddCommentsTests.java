package uitestschrome;
import com.codeborne.selenide.Configuration;
import dataproviders.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class KanboardAddCommentsTests {
    private final String BASE_URL = "http://localhost/login";
    private final String ADD_COMMENT_ENDPOINT = "https://localhost:80/jsonrpc.php/tasks/comment";
    private TaskPage taskPage;
    private String authToken;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        RestAssured.baseURI = "https://localhost:80";
        RestAssured.basePath = "/jsonrpc.php";

        taskPage = new TaskPage();

    }

    @Test(dataProvider = "commentAdd", dataProviderClass = TestDataProvider.class)
    public void addCommentTest(String comment) {
        open("http://localhost/dashboard/2/project_id/tasks/task_id");
        taskPage.addComment(comment);

        // Verify comment addition through API
        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body("{\"task_id\": task_id, \"comment\": \"" + comment + "\"}")
                .post(ADD_COMMENT_ENDPOINT);

        response.then().statusCode(200);
        response.prettyPrint();
    }
}
