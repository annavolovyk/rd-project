package pageobjects;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class KanboardAddComments {
    private final SelenideElement commentInput = $("#comment");
    private final SelenideElement addCommentButton = $("#add_comment");

    public void addComment(String comment) {
        commentInput.setValue(comment);
        addCommentButton.click();
    }
}
