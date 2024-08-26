package com.data.migration.batch;

import com.data.migration.destination.entities.Cliente;
import com.data.migration.legacy.entities.Client;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataProcessor implements ItemProcessor<Client, Cliente> {
    @Override
    public Cliente process(Client item) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf(item.getCpf());
        cliente.setNome(item.getName());
        cliente.setRua(item.getStreet_name());
        cliente.setNumero(item.getNumber());
        cliente.setComplemento(item.getComplement());
        cliente.setCidade(item.getCity());
        cliente.setEstado(item.getState());
        cliente.setTelefone(item.getPhone_number());
        cliente.setEmail(item.getEmail());
        return cliente;
    }
}
