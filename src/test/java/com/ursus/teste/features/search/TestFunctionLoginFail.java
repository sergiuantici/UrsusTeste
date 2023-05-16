package com.ursus.teste.features.search;

import com.ursus.teste.steps.serenity.EndUserSteps;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/user_fail.csv")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFunctionLoginFail {
    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public EndUserSteps echipa_ursus;

    private String username;
    private String password;

    @Issue("#Login Test Non-valid")
    @Test
    public void login_to_page_non_valid() {
        echipa_ursus.login_1(username,password);
        echipa_ursus.check_logged_in_fail();
    }

}
