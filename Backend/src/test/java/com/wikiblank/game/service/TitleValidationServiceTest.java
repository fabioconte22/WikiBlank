package com.wikiblank.game.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TitleValidationServiceTest {
    
    private final TitleValidationService service = new TitleValidationService();

    @Test 
    void ignoredParentheses() {
        assertTrue(service.matches("Mercurio", "Mercurio (pianeta)"));
    }

    @Test 
    void ignoredSeparator() {
        assertTrue(service.matches("Bosnia Erzegovina", "Bosnia-Erzegovina"));
    }

    @Test 
    void caseInsensitivityAccepted() {
        assertTrue(service.matches("mercurio", "MERCURIO"));
    }

    @Test 
    void distinctAccentsRejected() {
        assertFalse(service.matches("citta", "città"));
    }

    @Test
    void accentDirectionIgnored() {
        assertTrue(service.matches("perchè", "perché"));
    }

    @Test 
    void distinctPluralRejected() {
        assertFalse(service.matches("gatti", "gatto"));
    }

    @Test 
    void partialOrErrorTitleRejected() {
        assertFalse(service.matches("Bosnia", "Bosnia-Erzegovina"));
    }

    @Test 
    void nullOrVoidInputRejected() {
        assertFalse(service.matches(null, "Roma"));
        assertFalse(service.matches("", "Roma"));
    }
}
