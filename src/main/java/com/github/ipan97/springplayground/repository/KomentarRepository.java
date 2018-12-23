package com.github.ipan97.springplayground.repository;

import com.github.ipan97.springplayground.entity.Komentar;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ipan Taupik Rahman.
 * Date: 05/01/18
 * Time: 14:12
 * Project spring-data-jpa
 * Package com.github.ipan97.springdatajpa.repository
 */
@Repository
public interface KomentarRepository extends PagingAndSortingRepository<Komentar, Long> {
    List<Komentar> findAllByEmail(String email);
}
