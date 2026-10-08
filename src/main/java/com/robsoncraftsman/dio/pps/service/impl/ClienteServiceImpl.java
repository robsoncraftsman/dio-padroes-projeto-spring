package com.robsoncraftsman.dio.pps.service.impl;

import com.robsoncraftsman.dio.pps.model.Cliente;
import com.robsoncraftsman.dio.pps.model.ClienteRepository;
import com.robsoncraftsman.dio.pps.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente add(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> get(UUID id) {
        return this.clienteRepository.findById(id);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        this.clienteRepository.delete(cliente);
    }


}
