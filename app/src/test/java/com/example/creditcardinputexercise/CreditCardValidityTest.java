package com.example.creditcardinputexercise;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardValidityTest {
    @Test
    public void checkLuhn() {
        String card_name= "371449635398431";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.checkLuhn(card_name);
        assertEquals(true, b);
    }

    @Test
    public void checkLuhn2() {
        String card_name= "671449635398436";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.checkLuhn(card_name);
        assertEquals(false, b);
    }

    @Test
    public void checkLuhn3() {
        String card_name= "4532421174341278";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.checkLuhn(card_name);
        assertEquals(true, b);
    }

    @Test
    public void checkLuhn4() {
        String card_name= "5569755825672968";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.checkLuhn(card_name);
        assertEquals(true, b);
    }

    @Test
    public void check_length() {
        String card_name= "5569755825672968";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_length(card_name);
        assertEquals(true, b);
    }

    @Test
    public void check_length2() {
        String card_name= "453242117434127890";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_length(card_name);
        assertEquals(false, b);
    }

    @Test
    public void check_length3() {
        String card_name= "67144963539";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_length(card_name);
        assertEquals(false, b);
    }

    @Test
    public void check_substring4() {
        String card_name_one = "371449635398431";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_substring(card_name_one);
        assertEquals(true, b);
    }

    @Test
    public void check_substring5() {
        String card_name_one = "5569755825672968";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_substring(card_name_one);
        assertEquals(true, b);
    }

    @Test
    public void check_substring() {
        String card_name_one = "4532421174341278";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_substring(card_name_one);
        assertEquals(true, b);
    }

    @Test
    public void check_substring2() {
        String card_name_one = "1532421174341278";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_substring(card_name_one);
        assertEquals(false, b);
    }

    @Test
    public void check_substring3() {
        String card_name_one = "75324211743412";
        CreditCardValidity validity = new CreditCardValidity();
        boolean b = validity.check_substring(card_name_one);
        assertEquals(false, b);
    }

    @Test
    public void get_card_name_am() {
        String card_name_one = "371449635398436";
        CreditCardValidity validity = new CreditCardValidity();
        String card_name = validity.get_card_name(card_name_one);
        assertEquals("American", card_name);
    }

    @Test
    public void get_card_name_discover() {
        String card_name_one = "671449635398436";
        CreditCardValidity validity = new CreditCardValidity();
        String card_name = validity.get_card_name(card_name_one);
        assertEquals("Discover", card_name);
    }

    @Test
    public void get_card_name_visa() {
        String card_name_one = "4532421174341278";
        CreditCardValidity validity = new CreditCardValidity();
        String card_name = validity.get_card_name(card_name_one);
        assertEquals("Visa", card_name);
    }

    @Test
    public void get_card_name_master() {
        String card_name_one = "5569755825672968";
        CreditCardValidity validity = new CreditCardValidity();
        String card_name = validity.get_card_name(card_name_one);
        assertEquals("Master", card_name);
    }
}