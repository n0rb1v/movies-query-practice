package org.training360.com.moviesquerypractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m join fetch m.actors a where a.name = :name")
    List<Movie> ListMoviesWithActors(@Param("name") String name);

    @Query("select m from Movie m join fetch m.studio s where s.name = :name and m.year = :year")
    List<Movie> ListMoviesWithStudioByYear(@Param("name") String name, @Param("year") int year);

}
