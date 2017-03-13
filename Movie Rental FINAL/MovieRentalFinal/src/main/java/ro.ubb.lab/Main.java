package ro.ubb.lab;

import ro.ubb.lab.domain.Movie;
import ro.ubb.lab.domain.validators.MovieValidator;
import ro.ubb.lab.domain.validators.Validator;
import ro.ubb.lab.repository.InMemoryRepository;
import ro.ubb.lab.repository.Repository;
import ro.ubb.lab.service.MovieService;
import ro.ubb.lab.ui.Console;

/**
 * Created by horatiu on 13.03.2017.
 */
public class Main {
    public static void main(String args[]) {
        Validator<Movie> movieValidator = new MovieValidator();
        Repository<Long, Movie> movieRepository = new InMemoryRepository<>(movieValidator);
        MovieService studentService = new MovieService(movieRepository);
        Console console = new Console(studentService);
        console.runConsole();
    }
}