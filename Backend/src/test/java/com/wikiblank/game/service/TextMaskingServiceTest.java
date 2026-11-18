package com.wikiblank.game.service;

import java.util.Set;
import java.util.List;
import com.wikiblank.game.dto.Token;
import com.wikiblank.game.dto.TokenType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextMaskingServiceTest {
    
    private final TextMaskingService service = new TextMaskingService();

    @Test
    void noGuessedWords() {
       List<Token> tokens =  service.mask("Il gatto", Set.of());

       assertEquals(3, tokens.size());
       assertEquals(new Token(2, "Il", TokenType.VISIBLE), tokens.get(0));
       assertEquals(new Token(1, " ", TokenType.SEPARATOR), tokens.get(1));
       assertEquals(new Token(5, null, TokenType.MASKED), tokens.get(2));
       
    }

    @Test 
    void prepositionsWithApostrophe(){
        List<Token> tokens = service.mask("dell'Italia", Set.of());

        assertEquals(3, tokens.size());
        assertEquals(new Token(4, "dell", TokenType.VISIBLE), tokens.get(0));
        assertEquals(new Token(1, "'", TokenType.SEPARATOR), tokens.get(1));
        assertEquals(new Token(6, null, TokenType.MASKED), tokens.get(2));
    }

    @Test 
    void guessedWordAndCaseInsensitivity() {
        List<Token> tokens = service.mask("GATTO", Set.of("gatto"));

        assertEquals(1, tokens.size());
        assertEquals(new Token(5, "GATTO", TokenType.VISIBLE), tokens.get(0));
    }

    @Test 
    void numberAndSeparator() {
      List<Token> tokens = service.mask("nel 1960", Set.of());
      
      assertEquals(2, tokens.size());
      assertEquals(new Token(3, "nel", TokenType.VISIBLE), tokens.get(0));
      assertEquals(new Token(5, " 1960", TokenType.SEPARATOR), tokens.get(1));
    }
}
