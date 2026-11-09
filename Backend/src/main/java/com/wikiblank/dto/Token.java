package com.wikiblank.dto;

public record Token(int length, String text, TokenType type) {
    public Token {
        if (type == null)
            throw new IllegalArgumentException("type is required"); 
        
        switch(type) {
            case MASKED_WORD -> {
                if (length <= 0)
                    throw new IllegalArgumentException("a masked token must have a positive length ");

                if (text != null)
                    throw new IllegalArgumentException("a masked token must not have text");
            }
            case VISIBLE_WORD -> {
                if (text == null || text.isBlank())
                    throw new IllegalArgumentException("a visible token must not be null or blank ");

                if (length != text.length())
                    throw new IllegalArgumentException("in a visible token the length must be equal to that of the text");   
            }
            case SEPARATOR -> {
                if (text == null || text.isEmpty())
                    throw new IllegalArgumentException("in a separator token the text must not be null or empty");

                if (length != text.length())
                    throw new IllegalArgumentException("in a separator token the length must be equal to that of the text");
            }
        }
    }
}
