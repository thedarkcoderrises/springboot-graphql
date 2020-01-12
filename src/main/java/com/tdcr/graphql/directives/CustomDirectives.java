package com.tdcr.graphql.directives;

import graphql.Scalars;
import graphql.introspection.Introspection;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLDirective;
import graphql.schema.GraphQLNonNull;

public class CustomDirectives {

    public static final GraphQLDirective UpperDirective;

    public static final GraphQLDirective TimeoutDirective;

    static {
        UpperDirective = GraphQLDirective.newDirective()
                .name("upper").description("UPPER_CASE")
                .argument(GraphQLArgument.newArgument().name("isActive").
                        type(GraphQLNonNull.nonNull(Scalars.GraphQLBoolean))
                        .description("Included when true."))
                .validLocations(new Introspection.DirectiveLocation[]{
                        Introspection.DirectiveLocation.FIELD_DEFINITION,
                        Introspection.DirectiveLocation.FIELD}).build();

        TimeoutDirective = GraphQLDirective.newDirective()
                .name("timeout").description("TIME_OUT")
                .argument(GraphQLArgument.newArgument().name("timeInMillis").
                        type(GraphQLNonNull.nonNull(Scalars.GraphQLInt))
                        .description("request time's out, when time taken is greater than set value"))
                .validLocations(new Introspection.DirectiveLocation[]{
                        Introspection.DirectiveLocation.FIELD_DEFINITION,
                        Introspection.DirectiveLocation.FIELD}).build();
    }

}
