package org.training360.com.moviesquerypractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    StudioRepository studioRepository;
    Actor actor1;
    Actor actor2;
    Actor actor3;
    Movie movie1;
    Movie movie2;
    Movie movie3;
    Studio studio1;
    Studio studio2;

    @BeforeEach
    void init() {
        actor1 = new Actor("John Doe", LocalDate.of(1991,8,11));
        actor2 = new Actor("Arnold S.", LocalDate.of(1951,7,12));
        actor3 = new Actor("Bruce W.", LocalDate.of(1961,6,13));
        movie1 = new Movie("Titanic",1997);
        movie2 = new Movie("Terminator",1984);
        movie3 = new Movie("Star Wars",1978);
        studio1 = new Studio("Disney");
        studio2 = new Studio("Warner");

        movie1.addActor(actor1);
        movie1.addActor(actor3);
        movie2.addActor(actor1);
        movie2.addActor(actor2);
        movie3.addActor(actor1);
        movie3.addActor(actor3);
        studio1.addMovie(movie3);
        studio1.addMovie(movie1);
        studio2.addMovie(movie2);

        studioRepository.save(studio1);
        studioRepository.save(studio2);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);
    }

    @Test
    void test1(){
        List<Movie> movies = movieRepository.ListMoviesWithActors("Arnold S.");
        assertThat(movies)
                .extracting(Movie::getTitle)
                .containsOnly("Terminator");
    }
    @Test
    void test2() {
        List<Actor> actors = actorRepository.listActorWithBirthdate(LocalDate.of(1971, 1, 1));
        assertThat(actors)
                .extracting(Actor::getName)
                .containsOnly("John Doe");
    }
    @Test
    void test3(){
        List<Actor> actors = actorRepository.listActorWithMoreMovies();
        assertThat(actors)
                .extracting(Actor::getName)
                .containsOnly("John Doe","Bruce W.");
    }
    @Test
    void test4(){
        List<Studio> studios = studioRepository.listStudioWithMoreMovies();
        assertThat(studios)
                .extracting(Studio::getName)
                .containsOnly("Disney");
    }
    @Test
    void test5(){
        List<Actor> actors = actorRepository.listActorByMovie("Terminator");
        assertThat(actors)
                .extracting(Actor::getName)
                .containsOnly("John Doe","Arnold S.");
    }
    @Test
    void test6(){
        List<Movie> movies = movieRepository.ListMoviesWithStudioByYear("Warner",1984);
        assertThat(movies)
                .extracting(Movie::getTitle)
                .containsOnly("Terminator");
    }
    @Test
    void test7(){
        List<Studio> studios = studioRepository.listStudioByActor("Arnold S.");
        assertThat(studios)
                .extracting(Studio::getName)
                .containsOnly("Warner");
    }

}