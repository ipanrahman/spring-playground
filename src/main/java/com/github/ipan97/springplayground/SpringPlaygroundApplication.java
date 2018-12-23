package com.github.ngubex.spring.playground;

import com.github.ngubex.spring.playground.repository.BeritaRepository;
import com.github.ngubex.spring.playground.repository.KomentarRepository;
import com.github.ngubex.spring.playground.entity.Berita;
import com.github.ngubex.spring.playground.entity.Komentar;
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
public class SpringPlaygroundApplication implements CommandLineRunner {

    private final BeritaRepository beritaRepository;

    private final KomentarRepository komentarRepository;

    @Autowired
    public SpringPlaygroundApplication(BeritaRepository beritaRepository, KomentarRepository komentarRepository) {
        this.beritaRepository = beritaRepository;
        this.komentarRepository = komentarRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPlaygroundApplication.class, args);
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
