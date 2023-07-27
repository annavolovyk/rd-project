package dataproviders;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "positiveLoginTests")
    public Object[][] provideLoginCredentials() {
        return new Object[][]{
                {"annav", "999999"},
        };
    }

    @DataProvider(name = "negativeLoginTests2")
    public Object[][] provideInvalidUsername() {
        return new Object[][]{
                {"invalidname", "999999"},
        };
    }

    @DataProvider(name = "negativeLoginTests1")
    public Object[][] provideInvalidCredentials() {
        return new Object[][]{
                {"invalidname", "password123"},
        };
    }

    @DataProvider(name = "projectCreate")
    public static Object[][] projectCreate() {
        return new Object[][]{
                {"Project1", "This is first project", "001"},
                {"Project2", "This is second project", "002"},
        };
    }

    @DataProvider(name = "taskCreate")
    public static Object[][] taskCreate() {
        return new Object[][]{
                {"Task1", "This is first task"},
                {"Task2", "This is second task"},
        };
    }

    @DataProvider(name = "taskClose")
    public static Object[][] taskClose() {
        return new Object[][]{
                {1},
                {2},
        };
    }
    @DataProvider(name = "commentAdd")
    public static Object[][] commentAdd() {
        return new Object[][]{
                {"This is the first comment"},
                {"This is the second comment"},
        };
    }
}