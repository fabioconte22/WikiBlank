package com.wikiblank.game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.time.Instant;

@Entity 
@Table(name = "attempts")
@Getter 
@Setter 
@NoArgsConstructor 
public class Attempt {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match; 

    @Column(nullable = false)
    private String value; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttemptType type; 

    @Column(nullable = false)
    private Instant createdAt; 

}
