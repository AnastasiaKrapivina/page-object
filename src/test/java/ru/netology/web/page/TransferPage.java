package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement transferAmount = $("[data-test-id='amount']");
    private SelenideElement transferFrom = $("[data-test-id='from']");
    private SelenideElement transferTo = $("[data-test-id='to']");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement transferErrorMassage = $("[data-test-id='error-notification']");

    public TransferPage() {

        transferHead.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void transfer(DataHelper.CardInfo cardInfo, String amount) {
        transferAmount.setValue(amount);
        transferFrom.setValue(cardInfo.getNumber());
//        transferTo.setValue(cardInfo.getNumber());
        transferButton.click();
    }

    public DashboardPage validTransfer(DataHelper.CardInfo cardInfo, String amount) {
        transfer(cardInfo, amount);
        return new DashboardPage();
    }

    public void findErrorMassage(String expectedText) {
        transferErrorMassage.shouldHave(exactText(expectedText)).shouldBe(visible, Duration.ofSeconds(15));
    }
}
