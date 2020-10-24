package com.example.creditcardinputexercise;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.Test;
import org.w3c.dom.Text;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void field_empty() {
        String name = "";
        MainActivity activity = new MainActivity();
        boolean b = activity.field_empty(name);
        assertEquals(false, b);
    }

    @Test
    public void field_empty2() {
        String name = "234234234234";
        MainActivity activity = new MainActivity();
        boolean b = activity.field_empty(name);
        assertEquals(true, b);
    }

    @Test
    public void name_matches() {
        String name = "teretertertert";
        MainActivity activity = new MainActivity();
        boolean b = activity.name_matches(name);
        assertEquals(true, b);
    }

    @Test
    public void name_matches2() {
        String name = "t ";
        MainActivity activity = new MainActivity();
        boolean b = activity.name_matches(name);
        assertEquals(true, b);
    }

    @Test
    public void name_matches3() {
        String name = "34546456456";
        MainActivity activity = new MainActivity();
        boolean b = activity.name_matches(name);
        assertEquals(false, b);
    }

    @Test
    public void date_matches() {
        String name = "10/20";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(true, b);
    }

    @Test
    public void date_matches1() {
        String name = "10+0020";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(false, b);
    }

    @Test
    public void date_matches2() {
        String name = "fgkjdfhgkjdfg";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(false, b);
    }

    @Test
    public void date_matches4() {
        String name = "10/99";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(true, b);
    }

    @Test
    public void date_field_length() {
        String name = "";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(false, b);
    }

    @Test
    public void date_field_length2() {
        String name = "10";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(false, b);
    }

    @Test
    public void date_field_length3() {
        String name = "10/1999";
        MainActivity activity = new MainActivity();
        boolean b = activity.date_matches(name);
        assertEquals(false, b);
    }

    @Test
    public void check_expiration() {
        String name = "08/20";
        MainActivity activity = new MainActivity();
        boolean b = activity.check_expiration(name);
        assertEquals(false, b);
    }

    @Test
    public void check_expiration2() {
        String name = "10/20";
        MainActivity activity = new MainActivity();
        boolean b = activity.check_expiration(name);
        assertEquals(true, b);
    }

    @Test
    public void check_expiration3() {
        String name = "10/19";
        MainActivity activity = new MainActivity();
        boolean b = activity.check_expiration(name);
        assertEquals(false, b);
    }
}