package com.wikiblank.article.repository;

import com.wikiblank.article.model.Article;
import com.wikiblank.article.model.ArticleCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository  extends JpaRepository<Article, UUID>{
    boolean existsByPageId(long pageId); 
    Optional<Article> findByPageId(long pageId);
    List<Article> findByCategory(ArticleCategory category);
    
}
