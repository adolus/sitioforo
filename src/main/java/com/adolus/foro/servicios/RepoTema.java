package com.adolus.foro.servicios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adolus.foro.entidades.Tema;

public interface RepoTema extends CrudRepository<Tema, Integer> {
	@Query(value = "SELECT * FROM temas", nativeQuery = true)
	ArrayList<Tema> todos();
}
