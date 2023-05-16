package com.ursus.teste.steps.serenity;

import com.ursus.teste.pages.QuickMobile;
import groovy.lang.Tuple2;
import net.thucydides.core.annotations.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EndUserSteps {

    QuickMobile quick_mobile_page;

    @Step
    public void open_home_page() {
        quick_mobile_page.open();
    }


    @Step
    public void go_to_login_page() {
        quick_mobile_page.check_user_menu();
    }

    @Step
    public void insert_credentials() {
        Tuple2<String, String> tuple = getCredentials();
        assert tuple != null;
        String username = tuple.getFirst();
        String password = tuple.getSecond();

        quick_mobile_page.insert_credentials(username, password);
    }

    private Tuple2<String, String> getCredentials() {
        try {
            File myObj = new File("src/test/resources/user.csv");
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Tuple2<String, String> ret = new Tuple2<String, String>(data.split(",")[0], data.split(",")[1]);
                myReader.close();
                return ret;
            }
            else
                throw new Exception("There were no credentials found");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step
    public void sign_in() {
        quick_mobile_page.press_login_button();
    }

    @Step
    public void check_logged_in() {
        assertEquals(quick_mobile_page.get_login_message(), "Bun venit, Rachiu!");
    }

    public void search_drone() {
        quick_mobile_page.go_to_homepage();
        quick_mobile_page.open_shop_menu();
        quick_mobile_page.select_drones();
    }

    public void check_drone() {
        List<String> item_names = quick_mobile_page.get_results();
        assertEquals(item_names.size(), 15);

    }

    public void add_item_to_cart(int i) {
        quick_mobile_page.select_ith_item(i);
        quick_mobile_page.add_to_cart();
    }

    public void check_cart_count(int howMany) {
        go_to_cart();
        List<String> item_names = quick_mobile_page.get_cart_content();
        assertEquals(item_names.size(), howMany);
    }

    public void remove_item_from_cart(int i) {
        go_to_cart();
        quick_mobile_page.remove_ith_item(i);
    }

    private void go_to_cart() {
        if(!quick_mobile_page.in_cart_page()) {
            quick_mobile_page.click_cart_icon();
            if (!quick_mobile_page.in_cart_page())
                quick_mobile_page.check_view_cart_details();
        }
    }

    public void logout() {
        quick_mobile_page.check_user_menu();
        quick_mobile_page.click_logout();
    }

    public void login() {
        open_home_page();
        go_to_login_page();
        insert_credentials();
        sign_in();
    }

    public void check_logged_out() {
        quick_mobile_page.check_user_menu();
        assert quick_mobile_page.check_if_sign_in_page();
    }
//
//    @Step
//    public void should_see_second_item(String name) {
//        assertEquals(lumaPage.get_second_item(), name);
//    }
//
//    @Step
//    public void get_gear() {
//        lumaPage.click_gear();
//    }
//
//    @Step
//    public void add_second_item_to_cart() {
//        lumaPage.add_second_item_to_cart();
//        lumaPage.wait_for_cart_to_update();
//    }
//
//    @Step
//    public void check_cart_content() {
//        List<String> items = lumaPage.get_cart_content();
//    }
}