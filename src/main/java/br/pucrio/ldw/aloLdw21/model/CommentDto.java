package br.pucrio.ldw.aloLdw21.model;

import java.io.Serializable;
import java.util.Objects;

public class CommentDto implements Serializable {
    private final Long id;
    private final Post post;
    private final String name;
    private final String email;
    private final String body;

    public CommentDto(Long id, Post post, String name, String email, String body) {
        this.id = id;
        this.post = post;
        this.name = name;
        this.email = email;
        this.body = body;
    }



    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return post.getId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto entity = (CommentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.post, entity.post) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.body, entity.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, post, name, email, body);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "post = " + post + ", " +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "body = " + body + ")";

    }
}
