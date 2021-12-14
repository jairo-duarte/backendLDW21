package br.pucrio.ldw.aloLdw21.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<CommentInfo> findAllByPostId(Long postId);
    <T> Set<T> findBy(Class<T> type);
}