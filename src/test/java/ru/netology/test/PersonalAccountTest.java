package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class PersonalAccountTest {

    @BeforeEach
    void setUp() {
        DataHelper.cleanDatabase();
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithValidCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var code = SQLHelper.getLastVerificationCode();
        verificationPage.validVerify(code);
    }

    @Test
    void shouldBlockUserAfterThreeFailedLoginAttempts() {
        var loginPage = new LoginPage();
        var validAuth = DataHelper.getAuthInfo();
        var invalidAuth = new DataHelper.AuthInfo(validAuth.getLogin(), "wrong");
        for (int i = 0; i < 3; i++) {
            loginPage = loginPage.invalidLogin(invalidAuth);
        }

        loginPage.invalidLogin(validAuth);
        loginPage.shouldStayOnLoginPage();
    }
}
