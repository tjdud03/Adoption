package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "img")
    private String img;
    /*private byte[] img;*/

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

    @Builder
    public Breed(Integer breed_id, String title, String content, String img,
                 String breed, String add_date, String change_date, String member_id, String state){
        this.breed_id = breed_id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.breed = breed;
        this.add_date = add_date;
        this.change_date = change_date;
        this.state = state;
    }
}
