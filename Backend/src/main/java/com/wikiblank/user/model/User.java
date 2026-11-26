package com.wikiblank.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity 
@Table(name = "users")
@Getter 
@Setter 
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username; 

    @Column(nullable = false)
    private String passwordHash; 

}
