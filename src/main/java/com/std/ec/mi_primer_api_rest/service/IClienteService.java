package com.std.ec.mi_primer_api_rest.service;
import com.std.ec.mi_primer_api_rest.model.dto.ClienteDto;
import com.std.ec.mi_primer_api_rest.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listAll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);

}