package com.tdcr.graphql.directives;

import graphql.Scalars;
import graphql.introspection.Introspection;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLDirective;
import graphql.schema.GraphQLNonNull;

public class AppDirectives {

    public static final GraphQLDirective UpperDirective;

    static {
        UpperDirective = GraphQLDirective.newDirective()
                .name("upper").description("UPPER_CASE")
                .argument(GraphQLArgument.newArgument().name("isActive").
                        type(GraphQLNonNull.nonNull(Scalars.GraphQLBoolean))
                        .description("Included when true."))
                .validLocations(new Introspection.DirectiveLocation[]{
                        Introspection.DirectiveLocation.FIELD_DEFINITION,
                        Introspection.DirectiveLocation.FIELD}).build();
    }

}
