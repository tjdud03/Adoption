package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "breed", catalog = "pet_data")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breed_id", nullable = false)
    private Integer breed_id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", length = 255)
    private String content;

        @Column(name = "img", length = 255)
    private String img;

    @Column(name = "breed", length = 255)
    private String breed;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "member_id", length = 50, nullable = false)
    private String member_id;

    @Column(name = "state", length = 20)
    private String state;
}
