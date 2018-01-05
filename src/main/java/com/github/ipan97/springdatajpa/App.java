package com.github.ipan97.springdatajpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ipan97.springdatajpa.entity.Berita;
import com.github.ipan97.springdatajpa.entity.Komentar;
import com.github.ipan97.springdatajpa.repository.BeritaRepository;
import com.github.ipan97.springdatajpa.repository.KomentarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    private final BeritaRepository beritaRepository;

    private final KomentarRepository komentarRepository;

    private final ObjectMapper mapper;

    @Autowired
    public App(BeritaRepository beritaRepository, KomentarRepository komentarRepository, ObjectMapper mapper) {
        this.beritaRepository = beritaRepository;
        this.komentarRepository = komentarRepository;
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Berita newBerita = Berita.builder()
                .isi("hari ini panas")
                .judul("Cuaca hari ini")
                .waktuPublikasi(new Date())
                .build();

        Komentar newKomentar1 = Komentar.builder()
                .email("ipan.rahman@wgs.co.id")
                .nama("hari ini tidak panas tapi hujan")
                .isi("anda kurang beruntung")
                .berita(newBerita)
                .waktuPublikasi(new Date())
                .build();

        Komentar newKomentar2 = Komentar.builder()
                .email("fazar@wgs.co.id")
                .nama("Lorem Ipsum")
                .isi("Lorem Ipsum")
                .berita(newBerita)
                .waktuPublikasi(new Date())
                .build();
        List<Komentar> addKomentarBerita = new ArrayList<>();
        addKomentarBerita.add(newKomentar1);
        addKomentarBerita.add(newKomentar2);

        newBerita.setDaftarKomentar(addKomentarBerita);
        beritaRepository.save(newBerita);

        komentarRepository.findAllByEmail("ipan.rahman@wgs.co.id")
                .forEach((komentar) -> {
                    try {
                        log.info("Data Komentar {}", mapper.writeValueAsString(komentar));
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage(), e);
                    }
                });

        Berita findBeritaById = beritaRepository.findOne(newBerita.getId());

        if (findBeritaById != null) {
            findBeritaById.getDaftarKomentar().forEach((komentar) -> {
                try {
                    log.info("Daftar Komentar Berdasarkan Berita {}", mapper.writeValueAsString(komentar));
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
    }
}
