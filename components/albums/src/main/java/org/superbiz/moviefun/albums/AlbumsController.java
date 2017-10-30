package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AlbumsRepository albumsRepository;

    public AlbumsController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping("/add")
    public void add(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Album album) {
        albumsRepository.deleteAlbum(album);
    }

    @PostMapping("/update")
    public void update(@RequestBody Album album) {
        albumsRepository.updateAlbum(album);
    }

    @GetMapping("/")
    public List<Album> getAlbums() { return albumsRepository.getAlbums(); }

    @GetMapping("/find/{albumid}")
    public Album find(@PathVariable Long albumid) { return albumsRepository.find(albumid); }
}
