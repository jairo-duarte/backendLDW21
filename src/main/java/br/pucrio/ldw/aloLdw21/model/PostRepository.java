package br.pucrio.ldw.aloLdw21.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {
    <T> Set<T> findBy(Class<T> projection);

    <T> Optional<T> findById(Class<T> projection, Long id);

    @Query("select p from Post p where p.title like %?1%")
    List<Post> buscarPorTitulo(@NonNull String title, Pageable pageable);

    @Query("select p from Post p where p.title like %?1%")
    List<Post> buscarPorTitulo(@NonNull String title);

    List<Post> findByTitleIgnoreCase(String title);

    List<PostInfo> findByTitleAndBodyContains(String title,String body);

    List<PostInfo> findByUserIdOrderByUserIdAsc(@NonNull Long userId);

    @Query("select p from Post p where p.body like concat('%', ?1, '%') and p.title = ?2")
    List<PostInfo> findByBodyContainsAndTitle(String body,String title);



}
