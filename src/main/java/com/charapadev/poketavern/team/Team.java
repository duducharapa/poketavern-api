package com.charapadev.poketavern.team;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.charapadev.poketavern.slot.Slot;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Team {
    private final int TEAM_SIZE = 6;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String author = "Unknown";

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Slot> slots = new HashSet<>();

    public Team() {}

    public Team(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Team(String title) {
        this.title = title;
    }

    public void addSlot(Slot slot) throws RuntimeException {
        if (slots.size() >= TEAM_SIZE) throw new RuntimeException();
        slots.add(slot); 
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString() {
        return String.format(
            "[id=%s, title=%s, author=%s, createdAt=%s, slots=%s]",
            id, title, author, createdAt, slots
        );
    }

}
