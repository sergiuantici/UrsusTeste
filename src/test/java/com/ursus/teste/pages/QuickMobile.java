package com.ursus.teste.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.ArrayList;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

@DefaultUrl("https://www.quickmobile.ro/")
public class QuickMobile extends PageObject {

    public void insert_credentials(String username, String password) {
        WebElementFacade username_field = get_username_field();
        WebElementFacade password_field = get_password_field();

        username_field.type(username);
        password_field.type(password);
    }

    public void press_login_button() {
        WebElementFacade button = get_login_button();
        button.click();
    }

    private WebElementFacade get_username_field() {
        return find(By.name("email"));
    }

    private WebElementFacade get_password_field() {
        return find(By.name("password"));
    }

    private WebElementFacade get_login_button() {
        return find(By.cssSelector("body > div.wrapper.wrapper-sidebar-left > div.container > div > div:nth-child(2) > div > div:nth-child(1) > form > div.card-footer > div > button"));
    }

    public String get_login_message() {
        WebElementFacade titleElement = get_login_message_element();
        return titleElement.getTextContent();
    }
    public String get_login_message_fail() {
        WebElementFacade titleElement = get_login_message_element_fail();
        return titleElement.getTextContent();
    }
    private WebElementFacade get_login_message_element() {
        return find(By.cssSelector("#account_page > div.title-myinfo > h2"));
    }
    private WebElementFacade get_login_message_element_fail() {
        return find(By.cssSelector("div.alert.alert-with-icon.alert-danger > b"));
    }

    public void open_shop_menu() {
        WebElementFacade shop_menu_button = get_shop_menu_button();
        shop_menu_button.click();
    }

    private WebElementFacade get_shop_menu_button() {
        return find(By.cssSelector("#main-menu-toggle"));
    }

    public List<String> get_results() {
        List<WebElementFacade> elements = get_all_results();
        return elements.stream()
                .map(element -> element.findElement(By.cssSelector("div > div.card-body.card-product-body > a > div.card-product-title")).getText() + " "
                 + element.findElement(By.cssSelector("div > div.card-body.card-product-body > a > div.card-product-description")).getText())
                .collect(Collectors.toList());
    }

    private List<WebElementFacade> get_all_results() {
        return new ArrayList<>(findAll(".col-xs-6.col-sm-6.sort.col-md-3.col-lg-3.padd-mobile-card-product"));
    }

    public void select_drones() {
        WebElement drone_button = get_drone_button();
        drone_button.click();
    }

    private WebElement get_drone_button() {
        return find(By.cssSelector("#bs-example-navbar-collapse-1 > ul > li.dropdown.mega-dropdown.open > ul > li > ul > li:nth-child(5) > ul > li:nth-child(12) > a"));
    }

    public void select_ith_item(int i) {
        WebElement element = get_all_results()
                .get(i - 1)
                .findElement(
                        By.cssSelector("div > div.card-header > a > img")
                );
        element.click();
    }

    public void add_to_cart() {
        WebElementFacade add_to_cart_button = get_add_to_cart_button();
        add_to_cart_button.click();
    }

    private WebElementFacade get_add_to_cart_button() {
        return find(By.cssSelector("#AddToCart"));
    }

    public void click_cart_icon() {
        WebElementFacade cart_button = get_cart_button();
        cart_button.click();
    }

    private WebElementFacade get_cart_button() {
        return find(By.cssSelector("body > div.wrapper > div.visible-md.visible-lg > div.menu_background_slim > div > div > div.col-xs-2.col-sm-2.col-md-2.col-lg-2 > div.box-cart-home > div > div.dropdown-cart > div > a"));
    }

    public void check_view_cart_details() {
        WebElementFacade view_cart_details_button = get_view_cart_details_button();
        view_cart_details_button.click();
    }

    private WebElementFacade get_view_cart_details_button() {
        return find(By.cssSelector("#cart-dropdown > div.dropdown-panel-footer.text-center > a"));
    }

    public List<String> get_cart_content() {
        List<WebElementFacade> elements = get_cart_elements();
        // .findElement(By.cssSelector("div.row.is-table-row.table-vmiddle.cart-item-info > div.col-xs-12.col-sm-6.col-lg-7.text-cart-mobile > a"))
        return elements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    private List<WebElementFacade> get_cart_elements() {
        return new ArrayList<>(findAll(".card.cart-item"));
    }

    private WebElementFacade findByCssSelector(String selector){
        return find(By.cssSelector(selector));
    }

    public void go_to_homepage() {
        WebElementFacade homePage = get_home_page();
        homePage.click();
    }

    private WebElementFacade get_home_page() {
        return find(By.cssSelector("body > div.wrapper > div.visible-md.visible-lg > div.menu_background_slim > div > div > div.col-xs-8.col-sm-8.col-md-2.col-lg-3 > div > a"));
    }

    public boolean in_cart_page() {
        return cart_title_present() || empty_cart_title();
    }

    private boolean empty_cart_title() {
        WebElementFacade element = find(By.cssSelector("#cart > div > h3"));
        return element.isPresent();
    }

    private boolean cart_title_present() {
        WebElementFacade element = find(By.cssSelector("#cartForm > div > div:nth-child(1) > div.page-title"));
        return element.isPresent();
    }

    public void remove_ith_item(int i) {
        WebElement element_remove_button = get_cart_elements()
                .get(i - 1)
                .findElement(By.cssSelector("div.col-xs-3.col-sm-1.col-lg-1.text-right.cart-padd-top-prod.cart-item-remove-btn > a > img"));
        element_remove_button.click();
        waitForUpdate();
    }

//    @FindBy(css="#ui-id-6")
//    private WebElementFacade gear_tab;
//
//    @FindBy(css="body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.authorization-link > a")
//    private WebElementFacade goToSignInButton;
//
//    @FindBy(css="#send2")
//    private WebElementFacade singInButton;
//
//    @FindBy(name="login[username]")
//    private WebElementFacade username_bar;
//
//    @FindBy(name="login[password]")
//    private WebElementFacade password_bar;
//
//    @FindBy(css="body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a")
//    private WebElementFacade cart_button;
//
//    @FindBy(css="body > div.page-wrapper > header > div.header.content > a")
//    private WebElementFacade home_button;

//
//    public String get_second_item() {
//        return get_element(2).findElement(By.cssSelector("div > div > strong > a")).getText();
//    }
//
//    private WebElementFacade get_element(int which) {
//        return  find(By.cssSelector(String.format("#maincontent > div.columns > div.column.main > div.widget.block.block-static-block > div.block.widget.block-products-list.grid > div > div > ol > li:nth-child(%s)", which)));
//    }
//
//    public void click_gear() {
//        gear_tab.click();
//    }
//
//    public void add_second_item_to_cart() {
//        WebElementFacade el = get_element(2);
//        withAction().moveToElement(el).perform();
//
//        WebElement button = find(By.cssSelector("#maincontent > div.columns > div.column.main > div.widget.block.block-static-block > div.block.widget.block-products-list.grid > div > div > ol > li:nth-child(2) > div > div > div.product-item-inner > div > div.actions-primary > form > button"));
//        button.click();
//    }
//
//    public void go_to_cart_page(){
//        cart_button.click();
//    }
//
//    public List<String> get_cart_content() {
//        return find(By.cssSelector("#mini-cart")).findElements(By.className("item product product-item")).stream()
//                .map(element -> element.findElement(By.cssSelector("div > div > strong > a")).getText())
//                .collect(Collectors.toList());
//    }
//
//    public void wait_for_cart_to_update() {
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a > span.counter.qty"));
//    }
//
//    public void wait_for_login_message() {
//        waitFor(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.greet.welcome > span"), "Welcome, Sergiu Antici!"));
//    }

    private void waitForUpdate() {
        waitABit(200);
    }

    public void check_user_menu() {
        WebElementFacade user_icon = get_user_icon();
        user_icon.click();
    }

    private WebElementFacade get_user_icon() {
        return find(By.cssSelector("body > div.wrapper > div.visible-md.visible-lg > div.menu_background_slim > div > div > div.col-xs-2.col-sm-2.col-md-2.col-lg-2 > div.box-cart-home > div > div.dropdown-user-menu > div > a"));
    }

    public void click_logout() {
        WebElementFacade logout_button = get_logout_button();
        logout_button.click();
    }

    private WebElementFacade get_logout_button() {
        return find(By.cssSelector("#user-dropdown > ul > li.dropdown-panel-footer.text-center > a"));
    }

    public boolean check_if_sign_in_page() {
        return find(
                By.cssSelector("body > div.wrapper.wrapper-sidebar-left > div.container > div > div.col-xs-12.sidebar.sidebar-left.visible-md.visible-lg > div.sidebar-title")
            ).getText()
            .equals("Autentificare");

    }
}