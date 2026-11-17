package com.wikiblank.service;

import com.wikiblank.dto.Token;
import com.wikiblank.dto.TokenType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import org.springframework.stereotype.Service;


@Service 
public class TextMaskingService {

    private static final Set<String> ARTICLES = Set.of("il", "lo", "la", "i", "gli", "le","un", "uno", "una", "l");
    private static final Set<String> SIMPLE_PREPOSITIONS = Set.of("di", "a", "da", "in", "con", "su", "per", "tra", "fra");
    private static final Set<String> ARTICULATED_PREPOSITIONS = buildArticulatePrepositions(); 
    private static final Set<String> OTHER_VISIBLE_WORDS = Set.of("è", "e", "o"); 
    private static final Set<String> ALWAYS_VISIBLE_WORDS; 

    static {
        Set<String> temp = new HashSet<>(); 
        temp.addAll(ARTICLES); 
        temp.addAll(SIMPLE_PREPOSITIONS); 
        temp.addAll(OTHER_VISIBLE_WORDS); 
        temp.addAll(ARTICULATED_PREPOSITIONS); 
        temp.addAll(OTHER_VISIBLE_WORDS); 

        ALWAYS_VISIBLE_WORDS = Set.copyOf(temp);
    }
    
    public List<Token> mask(String text, Set<String> guessedWords) {
        Set<String> normalizedGuessedWords = new HashSet<>();
        Objects.requireNonNull(text);
        Objects.requireNonNull(guessedWords);
        
        for (String word : guessedWords) {
            if (word != null && !word.isBlank()){
                word = WordNormalizer.normalize(word);
                normalizedGuessedWords.add(word);
            }
            
        }

        List<Piece> splitText = splitText(text);
        List<Token> tokens = new ArrayList<>();

        for (Piece piece : splitText) {
            if (!piece.isWord()) {
                tokens.add(new Token(piece.text().length(), piece.text(), TokenType.SEPARATOR));
            } else {
                String normalizedPiece = WordNormalizer.normalize(piece.text());

                if (isAlwaysVisible(normalizedPiece) || normalizedGuessedWords.contains(normalizedPiece)) {
                    tokens.add(new Token(piece.text().length(), piece.text(), TokenType.VISIBLE));
                } else {
                    tokens.add(new Token(piece.text().length(), null, TokenType.MASKED));
                }
            }
        }

        return tokens; 
    }

    private record Piece(String text, boolean isWord) {}

    private boolean isAlwaysVisible(String normalizedWord) {
        return normalizedWord != null && ALWAYS_VISIBLE_WORDS.contains(normalizedWord); 
    }
        

    private List<Piece> splitText(String s) {
        Objects.requireNonNull(s);
        if (s.isEmpty())
            return List.of();
        
        List<Piece> piece = new ArrayList<>(); 
        StringBuilder current = new StringBuilder(); 
        char first = s.charAt(0);
        boolean currentIsWord = Character.isLetter(first); 
        current.append(first);
        
        
        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i); 
            boolean charIsWord = Character.isLetter(c); 

            if (charIsWord == currentIsWord) {
                current.append(c);
            } else {
                piece.add(new Piece(current.toString(), currentIsWord));
                current.setLength(0);
                currentIsWord = charIsWord; 
                current.append(c);
            }
        }

        piece.add(new Piece(current.toString(), currentIsWord));

        return piece; 

    }

    private static Set<String> buildArticulatePrepositions() {
        Set<String> articulatedPreposition = new HashSet<>(); 
        for (String colunm: List.of("de", "a", "da", "ne", "su")) {
            for (String row: List.of("l", "llo", "lla", "i", "gli", "lle", "ll")){
                articulatedPreposition.add(colunm + row);
            }
        }
        return Set.copyOf(articulatedPreposition); 
    }
}

