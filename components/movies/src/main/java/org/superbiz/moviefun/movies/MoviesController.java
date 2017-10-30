package org.superbiz.moviefun.movies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    MoviesRepository moviesRepository;

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @PostMapping("/add")
    public void add(@RequestBody Movie movie) {
        moviesRepository.addMovie(movie);
    }

    @DeleteMapping("/delete/{movieId}")
    public void delete(@PathVariable Long movieId) {
        moviesRepository.deleteMovieId(movieId);
    }

    @GetMapping("/countAll")
    public int countAll() {

        System.out.println("Im here");
        return moviesRepository.countAll();
    }

    @GetMapping("/count")
    public int count(@RequestParam(name = "field") String field,
                      @RequestParam(name = "key") String searchTerm) {
        return moviesRepository.count(field, searchTerm);
    }

    @GetMapping
    public List<Movie> find(
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        if (field != null && key != null) {
            return moviesRepository.findRange(field, key, start, pageSize);
        } else if (start != null && pageSize != null) {
            return moviesRepository.findAll(start, pageSize);
        } else {
            return moviesRepository.getMovies();
        }
    }
}
