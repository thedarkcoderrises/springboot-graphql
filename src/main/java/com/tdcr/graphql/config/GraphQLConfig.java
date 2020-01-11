package com.tdcr.graphql.config;

import com.tdcr.graphql.directives.UpperCaseDirective;
import graphql.GraphQL;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Configuration
@ConditionalOnProperty(
        value = {"graphql.schema.enable"},
        havingValue = "true",
        matchIfMissing = true
)
public class GraphQLConfig {

    @Value("classpath:/graphql/vehicleql.graphqls")
    Resource resource;

    @Bean
    public DataLoaderRegistry dataLoaderRegistry(){
        return new DataLoaderRegistry();
    }

    @Bean
    Instrumentation instrumentation(DataLoaderRegistry dataLoaderRegistry) {
        return new DataLoaderDispatcherInstrumentation(dataLoaderRegistry);
    }

   @Bean
   public GraphQL graphQL() throws IOException{
       GraphQLSchema graphQLSchema;
       InputStream inputStream = resource.getInputStream();
       InputStreamReader streamReader = new InputStreamReader(inputStream);
       TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(streamReader);
       typeDefinitionRegistry.getDirectiveDefinitions();

       SchemaDirectiveWiring upperDirective = new UpperCaseDirective();
       RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
               .directive("upper", upperDirective)
               .build();

       graphQLSchema =new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
       GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    return graphQL;
   }

//   @Bean
//   public GraphQL graphQL(GraphQLSchema schema){
//       GraphQL graphQL = GraphQL.newGraphQL(schema).build();
//       return  graphQL;
//   }



}

