package com.ursus.teste.features.search;

import com.ursus.teste.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFunctionLogin {
    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public EndUserSteps echipa_ursus;

    @Issue("#Login Test Valid")
    @Test
    public void login_to_page_valid() {
        echipa_ursus.login();
        echipa_ursus.check_logged_in();
    }
    @Issue("#Login Test Non-valid")
    @Test
    public void login_to_page_non_valid() {
        echipa_ursus.login_fail();
        echipa_ursus.check_logged_in_fail();
    }

}
