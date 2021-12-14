package br.pucrio.ldw.aloLdw21.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CommentInfo {

    Long getId();

    String getName();

    String getEmail();

    String getBody();

    Long getPostId();

}
