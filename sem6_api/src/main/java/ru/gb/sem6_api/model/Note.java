package ru.gb.sem6_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false, name="creation_time")
    private LocalDateTime creationTime;

    public Note(String title, String text, LocalDateTime creationTime) {
        this.title = title;
        this.text = text;
        this.creationTime = creationTime;
    }
}
