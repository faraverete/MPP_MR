package ro.ubb.lab.service;

import ro.ubb.lab.domain.Movie;
import ro.ubb.lab.domain.validators.ValidatorException;
import ro.ubb.lab.repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by horatiu on 13.03.2017.
 */

public class MovieService {

    private Repository<Long, Movie> repository;

    public MovieService(Repository<Long, Movie> repository){
        this.repository = repository;
    }

    public void addMovie(Movie movie) throws ValidatorException {
        repository.save(movie);
    }

    public Set<Movie> getAllMovies(){
        Iterable<Movie> students = repository.findAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }

    //--------Diana:
    public Optional<Movie> deleteMovie(Long id) throws IllegalArgumentException
    {
        Optional<Movie> m = repository.delete(id);
        return m;
    }

    public Optional<Movie> updateMovie(Movie m)
    {
        return repository.update(m);
    }
}
