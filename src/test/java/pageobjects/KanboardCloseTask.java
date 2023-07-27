package pageobjects;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;


public class KanboardCloseTask {
    private final SelenideElement closeTaskButton = $("#close_task");

    public void closeTask() {
        closeTaskButton.click();
    }
}
