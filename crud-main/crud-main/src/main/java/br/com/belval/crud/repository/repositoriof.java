package br.com.belval.crud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.belval.crud.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	
	  List<Cliente> findByEmail(String email);

	  Cliente findById(int id);

}
