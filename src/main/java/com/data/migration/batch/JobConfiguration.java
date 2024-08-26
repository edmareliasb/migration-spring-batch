package com.data.migration.batch;

import com.data.migration.destination.entities.Cliente;
import com.data.migration.legacy.entities.Client;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
@Slf4j
public class JobConfiguration {

    @Autowired
    @Qualifier("postgresqlDataSource")
    private final DataSource postgresqlDataSource;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    private final ImportDataTasklet importData;

    private final WriteDataToDestinationWriter writeDataToDestinationWriter;

    @Bean
    public Job migrationJob(){
        return new JobBuilder("migrationJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .build();
    }

    private Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(importData, platformTransactionManager)
                .build();
    }

    private Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<Client, Cliente>chunk(10, platformTransactionManager)
                .reader(customReader(postgresqlDataSource))
                .processor(new DataProcessor())
                .writer(writeDataToDestinationWriter)
                .build();
    }

    @Bean
    public ItemReader<Client> customReader(DataSource postgresqlDataSource) {
        return new JdbcCursorItemReaderBuilder<Client>()
                .name("customReader")
                .dataSource(postgresqlDataSource)
                .sql("SELECT * FROM client")
                .rowMapper(new BeanPropertyRowMapper<>(Client.class))
                .build();
    }

    /*
    Outra alternativa de writer. Funciona foi testado.
    @Bean
    public RepositoryItemWriter<Cliente> write() {
        RepositoryItemWriter<Cliente> writer = new RepositoryItemWriter<>();
        writer.setRepository(clienteRepository);
        writer.setMethodName("save");
        return writer;
    }*/

}
