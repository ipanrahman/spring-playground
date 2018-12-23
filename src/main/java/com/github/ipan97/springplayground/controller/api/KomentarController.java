package com.github.ngubex.spring.playground.controller.api;

import com.github.ngubex.spring.playground.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Ipan Taupik Rahman
 */
@RestController
@RequestMapping("/api")
public class KomentarController {

    private final KomentarRepository komentarRepository;

    @Autowired
    public KomentarController(KomentarRepository komentarRepository) {
        this.komentarRepository = komentarRepository;
    }

    @RequestMapping(
            value = "/komentars",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> komentars(Pageable pageable) {
        return ResponseEntity.ok(komentarRepository.findAll(pageable));
    }

    @RequestMapping(
            value = "/komentars/{komentarId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> komentars(@PathVariable("komentarId") Long komentarId) {
        return ResponseEntity.ok(komentarRepository.findById(komentarId));
    }
}
