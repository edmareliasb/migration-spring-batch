package com.data.migration.batch;

import com.data.migration.legacy.entities.Client;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class LoadDataFromLegacyReader implements ItemReader<Client> {

    @Override
    public Client read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}