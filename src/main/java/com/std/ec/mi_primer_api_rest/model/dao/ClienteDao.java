package com.std.ec.mi_primer_api_rest.model.dao;

import com.std.ec.mi_primer_api_rest.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
