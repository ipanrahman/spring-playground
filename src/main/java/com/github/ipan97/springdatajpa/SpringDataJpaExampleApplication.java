package com.github.ipan97.springdatajpa;

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
public class SpringDataJpaExampleApplication implements CommandLineRunner {

    private final BeritaRepository beritaRepository;

    private final KomentarRepository komentarRepository;

    @Autowired
    public SpringDataJpaExampleApplication(BeritaRepository beritaRepository, KomentarRepository komentarRepository) {
        this.beritaRepository = beritaRepository;
        this.komentarRepository = komentarRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaExampleApplication.class, args);
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
                //.berita(newBerita)
                .waktuPublikasi(new Date())
                .build();

        Komentar newKomentar2 = Komentar.builder()
                .email("fazar@wgs.co.id")
                .nama("Lorem Ipsum")
                .isi("Lorem Ipsum")
                // .berita(newBerita)
                .waktuPublikasi(new Date())
                .build();
        List<Komentar> addKomentarBerita = new ArrayList<>();
        addKomentarBerita.add(newKomentar1);
        addKomentarBerita.add(newKomentar2);

        newBerita.setDaftarKomentar(addKomentarBerita);
        beritaRepository.save(newBerita);

        komentarRepository.findAllByEmail("ipan.rahman@wgs.co.id")
                .forEach((komentar) -> {
                    log.info("Data Komentar {}", komentar.getNama());
                });

        Berita findBeritaById = beritaRepository.findOneById(newBerita.getId());

        if (findBeritaById != null) {
            findBeritaById.getDaftarKomentar().forEach((komentar) -> {
                log.info("Daftar Komentar Berdasarkan Berita {}", komentar.getBerita());
            });
        }
    }
}
