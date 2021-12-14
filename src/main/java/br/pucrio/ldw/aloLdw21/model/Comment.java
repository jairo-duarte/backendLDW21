package br.pucrio.ldw.aloLdw21.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comment_seq_gen")
    //@SequenceGenerator(name = "comment_seq_gen", sequenceName = "seq_comment", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private String name;
    private String email;
    private String body;

    public Comment() {
    }

    public Comment(Post post, String name, String email, String body) {
        this.post = post;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Post getPost() {
        return post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
