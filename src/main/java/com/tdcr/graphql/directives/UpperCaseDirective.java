package com.tdcr.graphql.directives;

import graphql.language.BooleanValue;
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
        DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(originalFetcher,
                ((dataFetchingEnvironment, value) -> {
                    BooleanValue isActive;
                            if(dataFetchingEnvironment.getField()
                                    .getDirective("upper") == null){
                                isActive = BooleanValue.newBooleanValue().build();
                            }else{
                                isActive = (BooleanValue) dataFetchingEnvironment.getField()
                                        .getDirective("upper")
                                        .getArgument("isActive").getValue();
                            }

            return (isActive.isValue())? value.toString().toUpperCase(): value;
        }));
         return field.transform( builder -> builder.dataFetcher(dataFetcher) );
    }
}
//9527606697 Jayshree