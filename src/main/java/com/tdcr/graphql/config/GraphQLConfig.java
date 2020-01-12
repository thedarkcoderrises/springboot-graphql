package com.tdcr.graphql.config;

import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.BaseDao;
import com.tdcr.graphql.dao.repository.PersonRepository;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import com.tdcr.graphql.directives.AppDirectives;
import com.tdcr.graphql.directives.UpperCaseDirective;
import com.tdcr.graphql.mutation.PersonMutation;
import com.tdcr.graphql.query.BaseQuery;
import com.tdcr.graphql.query.PersonResolver;
import com.tdcr.graphql.service.PersonService;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.introspection.Introspection;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLDirective;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.coxautodev.graphql.tools.SchemaParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashSet;

import static graphql.introspection.Introspection.DirectiveLocation.*;


@Configuration
@ConditionalOnProperty(
        value = {"graphql.schema.enable"},
        havingValue = "true",
        matchIfMissing = true
)
public class GraphQLConfig {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private AddressRepository addrsRepo;

    @Autowired
    private BaseDao baseDao;

    @Value("classpath:/graphql/vehicleql.graphqls")
    Resource resource;

    @Bean
    public DataLoaderRegistry dataLoaderRegistry(){
        return new DataLoaderRegistry();
    }

    @Bean
    Instrumentation instrumentation() {
        DataLoaderDispatcherInstrumentation dldi = new DataLoaderDispatcherInstrumentation();
        return dldi;
    }

//    @Bean
    /*public GraphQLSchema schema() throws IOException{
        GraphQLSchema graphQLSchema;
        InputStream inputStream = resource.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(streamReader);

        SchemaDirectiveWiring upperDirective = new UpperCaseDirective();
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
//                .type("Query", typeWiring -> typeWiring.dataFetcher("person",baseQuery)))
                .directive("upper", upperDirective)
                .build();
        graphQLSchema =new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        return graphQLSchema;
    }*/

  @Bean
  public BaseQuery baseQuery(PersonService personService,
                             PersonRepository personRepo,
                             VehicleRepository vehicleRepo,
                             AddressRepository addrsRepo){
      return new BaseQuery(personService,personRepo,vehicleRepo,addrsRepo);
  }

  @Bean
  public PersonMutation personMutation(PersonService personService){
      return new PersonMutation(personService);
  }

  @Bean
  public PersonResolver personResolver(AddressRepository addressRepository,
                                       VehicleRepository vehicleRepository,
                                       BaseDao baseDao,
                                       DataLoaderRegistry dataLoaderRegistry){
     return new PersonResolver(addressRepository,vehicleRepository,baseDao,dataLoaderRegistry);
  }

   @Bean
   public GraphQLSchema schema(BaseQuery baseQuery,
                          PersonMutation personMutation,
                          PersonResolver personResolver){


       GraphQLSchema schema = SchemaParser.newParser().file("graphql/vehicleql.graphqls")
               .resolvers(baseQuery)
               .resolvers(personResolver)
               .resolvers(personMutation)
               .directive("upper", new UpperCaseDirective())
               .build().makeExecutableSchema();

       return  schema;
   }

   @Bean
    public GraphQL graphQL(GraphQLSchema schema){
        return GraphQL.newGraphQL(schema)
                .build();
   }

}

