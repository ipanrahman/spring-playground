package com.github.ipan97.springdatajpa.repository;

import com.github.ipan97.springdatajpa.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ipan Taupik Rahman.
 * Date: 05/01/18
 * Time: 14:12
 * Project spring-data-jpa
 * Package com.github.ipan97.springdatajpa.repository
 */
public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    List<Komentar> findAllByEmail(String email);
}
