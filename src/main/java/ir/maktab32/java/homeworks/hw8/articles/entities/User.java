package ir.maktab32.java.homeworks.hw8.articles.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "nationalCode", nullable = false, length = 10)
    private String nationalCode;

    @Column(name = "birthday", nullable = false, length = 10)
    private String birthday;

    @OneToMany
    private List<Article> articleList = new ArrayList<>();
}
