package com.data.migration.batch;

import com.data.migration.destination.entities.Cliente;
import com.data.migration.destination.repository.ClienteRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WriteDataToDestinationWriter implements ItemWriter<Cliente> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void write(Chunk<? extends Cliente> chunk) throws Exception {
        chunk.forEach(repository::save);
    }
}
