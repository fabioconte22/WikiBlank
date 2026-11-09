package com.wikiblank.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenTest {
     
    @Test
    void muskedTokenWithoutTextIsAccepted() {
       Token token = new Token(6, null, TokenType.MASKED_WORD);

       assertEquals(6, token.length());
       assertNull(token.text());
       assertEquals(TokenType.MASKED_WORD, token.type());
       
    }

    @Test 
    void muskedTokenWhitTextIsRejected() {

        assertThrows(IllegalArgumentException.class, 
            () -> new Token(5, "Lionel", TokenType.MASKED_WORD));

    }

}
