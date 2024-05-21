package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "question", catalog = "pet_data")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "q_id", nullable = false)
    private Integer q_id;

    @Column(name = "member_id", nullable = false)
    private Integer member_id;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Builder
    public Question(Integer q_id, Integer member_id, String content, String add_date, String change_date) {
        this.q_id = q_id;
        this.member_id = member_id;
        this.content = content;
        this.add_date = add_date;
        this.change_date = change_date;
    }
}
