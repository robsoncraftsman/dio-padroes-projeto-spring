package com.robsoncraftsman.dio.pps.controller.dto;

public record AddEnderecoDTO(String cep, String logradouro, String complemento,
                             String bairro, String cidade, String uf) {
}
