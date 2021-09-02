package org.training360.com.moviesquerypractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio,Long> {

    @Query("select s from Studio s where s.movies.size > 1")
    List<Studio> listStudioWithMoreMovies();

    @Query("select s from Studio s join fetch s.movies m join fetch m.actors a where a.name = :name")
    List<Studio> listStudioByActor(@Param("name") String name);

    @Query("select distinct s from Studio s join fetch s.movies m join fetch m.actors a where a.movies.size > 1")
    List<Studio> listStudioByActorsWithMoreMovies();
}
