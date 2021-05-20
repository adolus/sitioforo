package com.adolus.foro.servicios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.adolus.foro.entidades.Replica;

public interface RepoReplica extends CrudRepository<Replica, Integer> {
	@Query(value = "SELECT * FROM replica WHERE idtema = ?1", nativeQuery = true)
	ArrayList<Replica> filtrarPorTema(int idtema);
}
