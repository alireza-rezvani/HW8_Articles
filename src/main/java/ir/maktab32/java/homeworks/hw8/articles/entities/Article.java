package ir.maktab32.java.homeworks.hw8.articles.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String brief;
    private String content;
    private Date createDate;
    private Date lastUpdateDate;
    private Date publishDate;
    private Boolean isPublished;


    @ManyToOne
    private User author;

    @ManyToOne
    private Category category;
}
