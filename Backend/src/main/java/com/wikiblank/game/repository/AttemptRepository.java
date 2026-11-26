package com.wikiblank.game.repository;

import com.wikiblank.game.model.Attempt;
import com.wikiblank.game.model.Match;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;


public interface AttemptRepository extends JpaRepository<Attempt, UUID>{
    List<Attempt> findByMatch(Match match); 
    boolean existsByMatchAndValue(Match match, String value); 
}
