package com.wikiblank.article.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity 
@Table(name = "articles")
@Getter 
@Setter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (unique = true, nullable = false)
    private long pageId; 

    @Column (unique = true, nullable = false)
    private long lastRevId; 
    
    @Column (nullable = false)
    private String title; 

    @Column (nullable = false, columnDefinition = "TEXT")
    private String text; 

    @Column (nullable = false)
    @Enumerated (EnumType.STRING)
    private ArticleCategory category; 

    
}
