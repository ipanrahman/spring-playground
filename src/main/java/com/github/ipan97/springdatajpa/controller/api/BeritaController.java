package com.github.ipan97.springdatajpa.controller.api;

import com.github.ipan97.springdatajpa.entity.Berita;
import com.github.ipan97.springdatajpa.repository.BeritaRepository;
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
public class BeritaController {
    private final BeritaRepository beritaRepository;

    @Autowired
    public BeritaController(BeritaRepository beritaRepository) {
        this.beritaRepository = beritaRepository;
    }

    @RequestMapping(
            value = "/beritas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Object> beritas(Pageable pageable) {
        return ResponseEntity.ok(beritaRepository.findAll(pageable));
    }

    @RequestMapping(
            value = "/beritas/{beritaId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Berita> getBeritaById(@PathVariable("beritaId") Long beritaId) {
        return ResponseEntity.ok(beritaRepository.findOneById(beritaId));
    }
}
