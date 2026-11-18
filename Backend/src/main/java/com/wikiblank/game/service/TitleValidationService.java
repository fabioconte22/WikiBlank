package com.wikiblank.game.service;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TitleValidationService {

    public boolean matches(String guess, String targetTitle) {
        if (targetTitle == null || guess == null || targetTitle.isBlank() || guess.isBlank())
            return false; 

        List<String> targetWords = extractWords(removeTrailingParentheses(targetTitle));
        List<String> guessWords = extractWords(removeTrailingParentheses(guess));

        return !targetWords.isEmpty() && targetWords.equals(guessWords); 
    }


    private String removeTrailingParentheses(String title) {
       Objects.requireNonNull(title);
        
       // Regex che trasforma una parentesi tonda(se presente) ed il suo contenuto, in una stringa vuota 
       String normalizedTitle = title.replaceAll("\\s*\\([^)]*\\)\\s*$", "");

       return normalizedTitle; 
    }

    private List<String> extractWords(String title) {
        Objects.requireNonNull(title); 

        Pattern p = Pattern.compile("\\P{L}+");
        String[] words = p.split(title);
        
        List<String> result = new ArrayList<>(); 

        for (String word : words) {
            if (!word.isBlank()){
                word = WordNormalizer.normalize(word);
                result.add(word);
            }
        }

        return result; 
    }


}
