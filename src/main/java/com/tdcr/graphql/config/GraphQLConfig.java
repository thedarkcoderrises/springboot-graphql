package com.tdcr.graphql.config;

import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GraphQLConfig {

    @Bean
    public DataLoaderRegistry dataLoaderRegistry(){
        return new DataLoaderRegistry();
    }

   /* @Bean
    DataLoaderRegistry dataLoaderRegistry(List<DataLoader<?, ?>> loaderList) {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        for (DataLoader<?, ?> loader : loaderList) {
            registry.register(loader.getClass().getSimpleName(), loader);
        }
        return registry;
    }*/


    @Bean
    Instrumentation instrumentation(DataLoaderRegistry dataLoaderRegistry) {
        return new DataLoaderDispatcherInstrumentation(dataLoaderRegistry);
    }

}
