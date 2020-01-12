package com.tdcr.graphql.directives;

import graphql.introspection.Introspection;
import graphql.schema.GraphQLDirective;

public class AppDirectives {

    public static final GraphQLDirective UpperDirective;

    static {
        UpperDirective = GraphQLDirective.newDirective()
                .name("upper").description("UPPER_CASE")
                .validLocations(new Introspection.DirectiveLocation[]{
                        Introspection.DirectiveLocation.FIELD_DEFINITION,
                        Introspection.DirectiveLocation.FIELD}).build();
    }

}
