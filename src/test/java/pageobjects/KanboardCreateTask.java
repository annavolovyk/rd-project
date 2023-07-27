package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class KanboardCreateTask {
    private final SelenideElement taskTitleInput = $("#task_title");
    private final SelenideElement taskDescriptionInput = $("#task_description");
    private final SelenideElement createTaskButton = $("#create_task");

    public void createTask(String title, String description) {
        taskTitleInput.setValue(title);
        taskDescriptionInput.setValue(description);
        createTaskButton.click();
    }
}
