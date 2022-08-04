package com.example.flightadvisor.config;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.model.Route;
import com.example.flightadvisor.repository.AirportRepository;
import com.example.flightadvisor.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private AirportRepository airportRepository;
    @Autowired
    private RouteRepository routeRepository;


    //AIRPORTS
    @Bean
    public FlatFileItemReader<Airport> reader(){
        FlatFileItemReader<Airport> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/airports.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<Airport> lineMapper(){
        DefaultLineMapper<Airport> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        //lineTokenizer - will extract the data from the csv file
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("airport_id","name","city","country","iata_code","icao_code");

        BeanWrapperFieldSetMapper<Airport> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        //fieldSetMapper - will map the value to the target class - Airport class
        fieldSetMapper.setTargetType(Airport.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public AirportProcessor processor(){
        return new AirportProcessor();
    }

    @Bean
    public RepositoryItemWriter<Airport> writer(){
        RepositoryItemWriter<Airport> writer = new RepositoryItemWriter<>();
        writer.setRepository(airportRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("csv-step1").<Airport,Airport>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }




    //ROUTES
    @Bean
    public FlatFileItemReader<Route> routeReader(){
        FlatFileItemReader<Route> routeItemReader = new FlatFileItemReader<>();
        routeItemReader.setResource(new FileSystemResource("src/main/resources/routes.csv"));
        routeItemReader.setName("csvRouteReader");
        routeItemReader.setLinesToSkip(1);
        routeItemReader.setLineMapper(routeLineMapper());

        return routeItemReader;
    }

    private LineMapper<Route> routeLineMapper(){
        DefaultLineMapper<Route> routeLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer routeLineTokenizer = new DelimitedLineTokenizer();
        routeLineTokenizer.setDelimiter(",");
        routeLineTokenizer.setStrict(false);
        routeLineTokenizer.setNames("airline_code","airline_id","source_airport_code","source_airport_id","destination_airport_code","destination_airport_id","codeshare","stops","equipment","price");

        BeanWrapperFieldSetMapper<Route> routeFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        routeFieldSetMapper.setTargetType(Route.class);

        routeLineMapper.setLineTokenizer(routeLineTokenizer);
        routeLineMapper.setFieldSetMapper(routeFieldSetMapper);
        return routeLineMapper;
    }

    @Bean
    public RouteProcessor routeProcessor(){
        return new RouteProcessor();
    }

    @Bean
    public RepositoryItemWriter<Route> routeWriter(){
        RepositoryItemWriter<Route> routeWriter = new RepositoryItemWriter<>();
        routeWriter.setRepository(routeRepository);
        routeWriter.setMethodName("save");
        return routeWriter;
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("csv-step2").<Route,Route>chunk(10)
                .reader(routeReader())
                .processor(routeProcessor())
                .writer(routeWriter())
                .build();
    }


    @Bean
    public Job job(){
        return jobBuilderFactory.get("importData")
                .flow(step1())
                .next(step2())
                .end().build();
    }

//    @Bean
//    public Job job(){
//        return jobBuilderFactory.get("importData")
//                .flow(step1())
//                .end().build();
//    }
}
