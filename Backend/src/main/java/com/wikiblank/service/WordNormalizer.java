package com.wikiblank.service;

import java.util.Locale;
import java.util.Objects;
import java.text.Normalizer;


public final class WordNormalizer {

    private WordNormalizer() {   
    }
    
    public static String normalize(String word) {
      
      Objects.requireNonNull(word, "word must not be null");
      word = Normalizer.normalize(word, Normalizer.Form.NFD); 

      // converte l'accento acuto (\u0301) in accento grave (\u0300)
      word = word.replace("\u0301", "\u0300");

      return Normalizer.normalize( word, Normalizer.Form.NFC).toLowerCase(Locale.ROOT);
  
    }
}
