package com.robsoncraftsman.dio.pps.controller;

import com.robsoncraftsman.dio.pps.controller.dto.AddClienteDTO;
import com.robsoncraftsman.dio.pps.controller.dto.AddEnderecoDTO;
import com.robsoncraftsman.dio.pps.model.Cliente;
import com.robsoncraftsman.dio.pps.model.Endereco;
import com.robsoncraftsman.dio.pps.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody AddClienteDTO addClienteDTO) {
        var cliente = new Cliente();
        cliente.setNome(addClienteDTO.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.add(cliente));
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") UUID id) {
        var clienteOptional = clienteService.get(id);
        if (clienteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cliente> excluaCliente(@PathVariable("id") UUID id) {
        var clienteOptional = clienteService.get(id);
        if (clienteOptional.isPresent()) {
            clienteService.delete(clienteOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("{id}/enderecos")
    public ResponseEntity<Cliente> addEndereco(@PathVariable("id") UUID id, @RequestBody AddEnderecoDTO addEnderecoDTO) {
        var clienteOptional = clienteService.get(id);
        if (clienteOptional.isPresent()) {
            var cliente = clienteOptional.get();
            var endereco = new Endereco();
            endereco.setCep(addEnderecoDTO.cep());
            endereco.setLogradouro(addEnderecoDTO.logradouro());
            endereco.setComplemento(addEnderecoDTO.complemento());
            endereco.setBairro(addEnderecoDTO.bairro());
            endereco.setCidade(addEnderecoDTO.cidade());
            endereco.setUf(addEnderecoDTO.uf());
            cliente.addEndereco(endereco);
            clienteService.update(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("{id}/enderecos/{id_endereco}")
    public ResponseEntity<Cliente> excluaEnderecoCliente(@PathVariable("id") UUID id, @PathVariable("id_endereco") UUID idEndereco) {
        var clienteOptional = clienteService.get(id);
        if (clienteOptional.isPresent()) {
            var cliente = clienteOptional.get();
            var enderecoOptional = cliente.getEnderecos().stream().filter(endereco -> endereco.getId().equals(idEndereco)).findAny();
            if (enderecoOptional.isPresent()) {
                cliente.getEnderecos().remove(enderecoOptional.get());
                clienteService.update(cliente);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
