package com.side.naruto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.side.naruto.models.Jutsu;

@Repository
public interface JutsuRepository extends CrudRepository<Jutsu, Long> {
	List<Jutsu> findAll();
}
