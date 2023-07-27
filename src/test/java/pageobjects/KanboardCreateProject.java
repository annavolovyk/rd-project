package pageobjects;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class KanboardCreateProject {

    private final SelenideElement projectNameInput = $("#project_name");
    private final SelenideElement projectDescriptionInput = $("#project_description");
    private final SelenideElement projectIdentifierInput = $("#project_identifier");
    private final SelenideElement submitButton = $("#submit_project_creation");


    public void createProject(String name, String description, String identifier) {
        projectNameInput.setValue(name);
        projectDescriptionInput.setValue(description);
        projectIdentifierInput.setValue(identifier);
        submitButton.click();
    }
}
