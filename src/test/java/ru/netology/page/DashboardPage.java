package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement header = $("[data-test-id=dashboard]");

    public DashboardPage() {
        header.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Личный кабинет"));
    }
}
