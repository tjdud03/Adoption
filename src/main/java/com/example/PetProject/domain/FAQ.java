package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "FAQ", catalog = "pet_data")
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id", nullable = false)
    private Integer faq_id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "delete_date", length = 50)
    private String delete_date;

    @Column(name = "member_id", length = 50, nullable = false)
    private Integer member_id;

    @Builder
    public FAQ(Integer faq_id, String title, String content, String add_date, String delete_date, Integer member_id) {
        this.faq_id = faq_id;
        this.title = title;
        this.content = content;
        this.add_date = add_date;
        this.delete_date = delete_date;
        this.member_id = member_id;
    }
/*    public Integer getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public String getAddDate(){
        return addDate;
    }
    public String getDeleteDate(){
        return deleteDate;
    }
    public Integer getMemberId(){
        return Memberid;
    }*/
}
