package ro.ubb.lab.ui;

import ro.ubb.lab.service.MovieService;
import ro.ubb.lab.domain.Movie;
import ro.ubb.lab.domain.validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;


public class Console
{
    private MovieService movieService;

    public Console(MovieService studentService)
    {
        this.movieService = movieService;
    }

    public void runConsole()
    {
        boolean working = true;

        while(working)
        {
            printMenu();

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            int cmd;
            try
            {
                System.out.println("Enter command:");
                cmd = Integer.parseInt(bufferRead.readLine());

            }
            catch(IOException e)
            {
                //e.printStackTrace();
                System.out.println("Command should be an integer!");
                continue;
            }

            switch(cmd)
            {
                case 0 : working = false; break;
                case 1 : addMovie(); break;
                case 2 : deleteMovie(); break;
                case 3 : updateMovie(); break;
                case 4 : printAllMovies(); break;
                default : System.out.println("Invalid command!");
            }
        }

    }

    private void printMenu()
    {
        String cmd = "Commands:\n";

        cmd += "\t 0) Exit.\n";
        cmd += "\t 1) Add a movie.\n";
        cmd += "\t 2) Delete a movie.\n";
        cmd += "\t 3) Update a movie.\n";
        cmd += "\t 4) Show all movies.";

        System.out.println(cmd);
    }


    private void printAllMovies()
    {
        Set<Movie> movies = movieService.getAllMovies();
        movies.stream().forEach(System.out::println);
    }

    private void addMovie()
    {
        Movie movie = readMovie();
        if(movie != null)
            try
            {
                movieService.addMovie(movie);
            }
            catch (ValidatorException | IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }

    }

    private Movie readMovie()
    {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.println("Give the ID:");
            Long id = Long.valueOf(bufferRead.readLine());



            System.out.println("Give the name:");
            String name = bufferRead.readLine();

            System.out.println("Give the genre:");
            String genre = bufferRead.readLine();

            Movie movie = new Movie(name, genre);
            movie.setId(id);

            return movie;
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private void deleteMovie()
    {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Long id = null;
        System.out.println("Give the ID:");
        try
        {
            id = Long.valueOf(bufferRead.readLine());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

        if(id != null) {
            movieService.deleteMovie(id);
            //TODO:message if no movie deleted
        }
    }

    private void updateMovie()
    {
        Movie movie = readMovie();

        if(movie != null)
            try
            {
                movieService.updateMovie(movie);
                //TODO:message if no movie affected?
            }
            catch (ValidatorException | IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }

    }


}
