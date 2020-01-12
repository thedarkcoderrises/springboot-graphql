package com.tdcr.graphql.directives;

import graphql.language.IntValue;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition field = (GraphQLFieldDefinition)environment.getElement();
        DataFetcher originalDataFetcher = field.getDataFetcher();
        DataFetcher timeoutDataFetcher = new DataFetcher(){
            @Override
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
                IntValue timeoutInMillis = (IntValue) dataFetchingEnvironment.getField()
                        .getDirective("timeout")
                        .getArgument("timeInMillis").getValue();
                try {
                    return   CompletableFuture.supplyAsync(()->{
                        Object result = originalDataFetcher.get(dataFetchingEnvironment);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }).get(timeoutInMillis.getValue().intValue(),TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };



        return field.transform( builder -> builder.dataFetcher(timeoutDataFetcher) );
    }
}
