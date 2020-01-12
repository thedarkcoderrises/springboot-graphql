package com.tdcr.graphql.directives;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

public class UpperCaseDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition field = (GraphQLFieldDefinition)environment.getElement();

        DataFetcher originalFetcher = field.getDataFetcher();
        DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(originalFetcher, ((dataFetchingEnvironment, value) -> {
            return value.toString().toUpperCase();
        }));
         return field.transform( builder -> builder.dataFetcher(dataFetcher) );
    }
}


//https://github.com/graphql-java/graphql-java/blob/master/src/test/groovy/readme/DirectivesExamples.java
// https://www.graphql-java-kickstart.com/tools/directives/
//https://github.com/graphql-java/graphql-java-extended-validation#the-supplied-directive-constraints
//https://github.com/graphql-java-kickstart/samples/blob/master/directives/src/main/java/directives/UppercaseDirective.java
//9527606697 Jayshree