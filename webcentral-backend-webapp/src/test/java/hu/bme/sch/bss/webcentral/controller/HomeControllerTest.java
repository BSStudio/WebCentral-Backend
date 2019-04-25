package hu.bme.sch.bss.webcentral.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class HomeControllerTest {

    private HomeController underTest;

    @Test
    void testHome() {
        // GIVEN
        underTest = new HomeController();

        // WHEN
        String result = underTest.home();

        // THEN
        assertEquals("Hello", result);

    }

}
