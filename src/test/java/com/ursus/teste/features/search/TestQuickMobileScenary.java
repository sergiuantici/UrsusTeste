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
public class TestQuickMobileScenary {
    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public EndUserSteps echipa_ursus;

    @Issue("#Login")
    @Test
    public void T1_login_to_page() {
        echipa_ursus.login();
        echipa_ursus.check_logged_in();
    }

    @Issue("#Search Item")
    @Test
    public void T2_search_for_drone(){
        echipa_ursus.search_drone();
        echipa_ursus.check_drone();
    }

    @Issue("#Add Item to Cart")
    @Test
    public void T3_add_backpack_to_cart(){
        echipa_ursus.add_item_to_cart(1);
        echipa_ursus.check_cart_count(1);
    }

    @Issue("#Remove Item from Cart")
    @Test
    public void T4_remove_from_cart(){
        // div.col-xs-3.col-sm-1.col-lg-1.text-right.cart-padd-top-prod.cart-item-remove-btn > a > img
        echipa_ursus.remove_item_from_cart(1);
        echipa_ursus.check_cart_count(0);
    }

    @Issue("Logout")
    @Test
    public void T5_logout(){
        echipa_ursus.login();
        echipa_ursus.logout();
        echipa_ursus.check_logged_out();
    }

//    @Issue("#Check watches")
//    @Test
//    public void look_for_watches(){
//        echipa_ursus.looks_for("Clamber Watch");
//        echipa_ursus.should_see_definition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");
//    }
//
//
//    @Issue("#WIKI-1")
//    @Test
//    public void searching_by_keyword_apple_should_display_the_corresponding_article() {
//        echipa_ursus.open_home_page();
//        echipa_ursus.looks_for("apple");
//        echipa_ursus.should_see_definition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");
//
//    }
//
//    @Test
//    public void searching_by_keyword_banana_should_display_the_corresponding_article() {
//        echipa_ursus.open_home_page();
//        echipa_ursus.looks_for("pear");
//        echipa_ursus.should_see_definition("An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.");
//    }
//
//    @Pending
//    @Test
//    public void searching_by_ambiguious_keyword_should_display_the_disambiguation_page() {
//    }
}
