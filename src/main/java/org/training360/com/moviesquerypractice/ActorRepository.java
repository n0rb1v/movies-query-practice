package org.training360.com.moviesquerypractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("select a from Actor a where a.dateOfBirth > :date")
    List<Actor> listActorWithBirthdate(@Param("date") LocalDate date);

    @Query("select a from Actor a where a.movies.size > 1")
    List<Actor> listActorWithMoreMovies();

    @Query("select a from Actor a join fetch a.movies m where m.title = :title")
    List<Actor> listActorByMovie(@Param("title") String title);

}
