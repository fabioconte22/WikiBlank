package com.wikiblank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class WordNormalizerTest {
  
    @Test 
    void upperCaseIsIgnored() {
        assertEquals(WordNormalizer.normalize("Messi"),
                    WordNormalizer.normalize("messi")
        );
    }

    @Test
    void accentDirectionIsIgnored() {
        assertEquals(WordNormalizer.normalize("perché"), 
                      WordNormalizer.normalize("perchè")
        );
    }

    @Test 
    void missingAccentMakesADifferentWord() {
        assertNotEquals(WordNormalizer.normalize("perche"), 
                        WordNormalizer.normalize("perché")
        );
    }

    @Test 
    void decomposedAccentEqualsComposedOne() {
        assertEquals(WordNormalizer.normalize("e\u0300"), 
                    WordNormalizer.normalize("\u00E8")
        );
    }

    @Test 
    void nullIsRejected() {
        NullPointerException e = assertThrows(NullPointerException.class,
            () -> WordNormalizer.normalize(null));

        assertEquals("word must not be null", e.getMessage());
        
    }
    
}
