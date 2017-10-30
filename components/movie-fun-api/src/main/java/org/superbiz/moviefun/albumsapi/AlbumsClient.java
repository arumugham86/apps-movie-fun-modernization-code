package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class AlbumsClient {

    String albumsUrl;
    RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        System.out.println("Album Url:" + albumsUrl);
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        restOperations.postForEntity(albumsUrl + "/add", albumInfo, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl + "/find/" + id, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {

        return restOperations.exchange(albumsUrl + "/", HttpMethod.GET, null, albumListType).getBody();
    }

    public void deleteAlbum(AlbumInfo albumInfo) {

        restOperations.delete(albumsUrl + "/delete", albumInfo, AlbumInfo.class);
    }

    public void updateAlbum(AlbumInfo albumInfo) {

        restOperations.postForEntity(albumsUrl + "/update", albumInfo, AlbumInfo.class);
    }
}
