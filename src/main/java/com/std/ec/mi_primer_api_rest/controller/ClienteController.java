package com.std.ec.mi_primer_api_rest.controller;


import com.std.ec.mi_primer_api_rest.model.dto.ClienteDto;
import com.std.ec.mi_primer_api_rest.model.entity.Cliente;
import com.std.ec.mi_primer_api_rest.model.payload.MensajeResponse;
import com.std.ec.mi_primer_api_rest.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClienteController
{
    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll()
    {
       List<Cliente> getList=clienteService.listAll();
        if(getList==null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build(),HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getList)
                        .build(),HttpStatus.OK);
    }



    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto)
    {
        Cliente clienteSave=null;
        try
        {
            clienteSave=clienteService.save(clienteDto);
            clienteDto=  ClienteDto.builder()
                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();

            return new ResponseEntity<>( MensajeResponse.builder().mensaje("Guardado Correctamente").object(clienteDto).build(),HttpStatus.CREATED);
        } catch (DataAccessException exDT)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }


    }

    @PutMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto,@PathVariable Integer id)
    {
        Cliente clienteUpdate=null;
        try
        {
            if(clienteService.existsById(id)){
            clienteDto.setIdCliente(id);
            clienteUpdate=clienteService.save(clienteDto);
            clienteDto=  ClienteDto.builder()
                    .idCliente(clienteUpdate.getIdCliente())
                    .nombre(clienteUpdate.getNombre())
                    .apellido(clienteUpdate.getApellido())
                    .correo(clienteUpdate.getCorreo())
                    .fechaRegistro(clienteUpdate.getFechaRegistro())
                    .build();

            return new ResponseEntity<>( MensajeResponse.builder().mensaje("Guardado Correctamente").object(clienteDto).build(),HttpStatus.CREATED);}
            else{return  new ResponseEntity<>( MensajeResponse.builder().mensaje("El registro que intenta actualizar no se encuentra en la base de datos").object(null).build(),HttpStatus.NOT_FOUND);}
        } catch (DataAccessException exDT)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {

        try
        {
            Cliente clieteDelete=clienteService.findById(id);
            clienteService.delete(clieteDelete);
            return new ResponseEntity<>(clieteDelete,HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDT)
        {

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .object(null)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id)
    {
        Cliente cliente=clienteService.findById(id);
        if(cliente==null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar no exite")
                            .object(null)
                            .build(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDto.builder()
                                .idCliente(cliente.getIdCliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .correo(cliente.getCorreo())
                                .fechaRegistro(cliente.getFechaRegistro())
                                .build())
                        .build(),HttpStatus.OK);
    }
}
