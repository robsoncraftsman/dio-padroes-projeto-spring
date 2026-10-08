package com.robsoncraftsman.dio.pps.service;

import com.robsoncraftsman.dio.pps.model.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

    public Cliente add(Cliente cliente);

    public Optional<Cliente> get(UUID id);

    public Cliente update(Cliente cliente);

    public void delete(Cliente cliente);
}
