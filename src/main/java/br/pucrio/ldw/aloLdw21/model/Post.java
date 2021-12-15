package br.pucrio.ldw.aloLdw21.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    private Long userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_gen")
    //@SequenceGenerator(name = "post_gen", sequenceName = "seq_post")
    @Column(nullable = false)
    private Long id;
    @Column(unique = true,length = 100 )
    private String title;
    @Column(length = 1000)
    private String body;
    @OneToMany(mappedBy="post")
    private Set<Comment> comments;
    @ColumnDefault("1")
    private Boolean publico = true;


    public Post(Long userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Post() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }
}
