package com.wikiblank.game.repository;

import com.wikiblank.article.model.ArticleCategory;
import com.wikiblank.game.model.Match;
import com.wikiblank.game.model.MatchStatus;
import com.wikiblank.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Collection;
import java.util.List; 

public interface MatchRepository extends JpaRepository<Match, UUID>{
    long countByUserAndStatusAndArticle_Category(User user, MatchStatus status, ArticleCategory category); 
    List<Match> findByUserAndStatusAndArticle_Category(User user, MatchStatus status, ArticleCategory category);
    List<Match> findByUserAndStatus(User user, MatchStatus status); 
    List<Match> findByStatusIn(Collection<MatchStatus> statuses); 
}
