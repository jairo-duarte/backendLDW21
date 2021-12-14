package br.pucrio.ldw.aloLdw21.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Integer> {
    <T> Set<T> findBy(Class<T> type);

    @Query("select p from Post p where p.title like %?1%")
    List<Post> buscarPorTitulo(@NonNull String title, Pageable pageable);

    @Query("select p from Post p where p.title like %?1%")
    List<Post> buscarPorTitulo(@NonNull String title);

    List<Post> findByTitleIgnoreCase(String title);

    List<PostInfo> findByUserIdOrderByUserIdAsc(@NonNull Integer userId);

    List<PostInfo> findByTitleAndBodyContains(String title,String body);
    @Query("select p from Post p where p.body like concat('%', ?1, '%') and p.title = ?2")
    List<PostInfo> findByBodyContainsAndTitle(String body,String title);



}