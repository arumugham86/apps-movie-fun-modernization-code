package org.superbiz.moviefun.moviesapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class MoviesClient {

    String moviesUrl;
    RestOperations restOperations;

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };

    public MoviesClient(String moviesUrl, RestOperations restOperations){

        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public void addMovie(MovieInfo movie) {

        restOperations.postForEntity(moviesUrl + "/add", movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {

        restOperations.delete(moviesUrl + "/" + id);
    }

    public int countAll() {

        return restOperations.getForObject(moviesUrl + "/countAll", Integer.class);
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("key", searchTerm)
                .queryParam("start", firstResult)
                .queryParam("pageSize", maxResults);

        return restOperations.exchange(builder.toUriString(), HttpMethod.GET, null, movieListType).getBody();
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("start", firstResult)
                .queryParam("pageSize", maxResults);

        return restOperations.exchange(builder.toUriString(), HttpMethod.GET, null, movieListType).getBody();
    }

    public int count(String field, String key) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl + "/count")
                .queryParam("field", field)
                .queryParam("key", key);

        return restOperations.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> getMovies() {

        return restOperations.exchange(moviesUrl, HttpMethod.GET, null, movieListType).getBody();
    }
}