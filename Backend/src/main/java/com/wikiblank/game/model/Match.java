package com.wikiblank.game.model;

import com.wikiblank.user.model.User;
import com.wikiblank.article.model.Article;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Entity 
@Table (name = "matches")
@Getter 
@Setter 
@NoArgsConstructor 
public class Match {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /* 
    * Molte partite appertengono ad un Utente oppure ad un articolo quindi per qusta
    * relazione si utilizza ManyToOne. I
    * Impostiamo il caricamento "pigro" con FetchType.LAZY così Hibernate non carica l'intero utente
    * in memoria a meno che non sia necessario
    */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // Utilizziamo JoinColumn per specificare il nome della colonna di FK sul DB
    @JoinColumn(name = "user_id", nullable = false)
    private User user; 

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status; 

    @Column(nullable = false)
    private long elapsedSeconds; 

    @Column(nullable = false) 
    private Instant lastSeenAt; 

    @Column(nullable = false)
    private Instant startedAt; 

    @Column
    private Instant endedAt; 

    @Column(nullable = false)
    private boolean ranked; 

}
