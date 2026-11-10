package com.wikiblank.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenTest {
     
    @Test
    void maskedTokenWithoutTextIsAccepted() {
       Token token = new Token(6, null, TokenType.MASKED);

       assertEquals(6, token.length());
       assertNull(token.text());
       assertEquals(TokenType.MASKED, token.type());
       
    }

    @Test 
    void maskedTokenWhithTextIsRejected() {

        assertThrows(IllegalArgumentException.class, 
            () -> new Token(5, "Lionel", TokenType.MASKED)
        );

    }

    @Test 
    void maskedTokenWhithoutLengthRejected() {

        assertThrows(IllegalArgumentException.class,
            () -> new Token(0, null, TokenType.MASKED)
        );
    }

    @Test 
    void visibleTokenWhithoutTextRejected() {

        assertThrows(IllegalArgumentException.class,
            () -> new Token(4, null, TokenType.VISIBLE)
        );
    }

    @Test 
    void visibleTokenBlankTextRejected() {

        assertThrows(IllegalArgumentException.class, 
            () -> new Token(0, "", TokenType.VISIBLE)
        );
    }

    @Test 
    void separatorTokenWhithSpaceAccepted() {
        Token token = new Token(1, " ", TokenType.SEPARATOR); 

        assertEquals(1, token.length());
        assertEquals(" ", token.text());
        assertEquals(TokenType.SEPARATOR, token.type());
    }

    @Test 
    void tokenTypeNullRejected() {

        assertThrows(IllegalArgumentException.class, 
            () -> new Token(8, "Pippo", null)
        ); 
    }

}
