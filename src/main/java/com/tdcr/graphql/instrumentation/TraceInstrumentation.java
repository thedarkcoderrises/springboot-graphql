package com.tdcr.graphql.instrumentation;

import com.tdcr.graphql.instrumentation.state.TraceInstrumentationState;
import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters;
import graphql.schema.DataFetcher;

import java.util.concurrent.CompletableFuture;

public class TraceInstrumentation extends SimpleInstrumentation {
    @Override
    public InstrumentationState createState() {
        //
        // instrumentation state is passed during each invocation of an Instrumentation method
        // and allows you to put stateful data away and reference it during the query execution
        //
        return new TraceInstrumentationState();
    }

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
        long startNanos = System.nanoTime();
        return new SimpleInstrumentationContext<ExecutionResult>() {
            @Override
            public void onCompleted(ExecutionResult result, Throwable t) {
                TraceInstrumentationState state = parameters.getInstrumentationState();
                state.recordTiming(parameters.getQuery(), System.nanoTime() - startNanos);
            }
        };
    }

    @Override
    public DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher, InstrumentationFieldFetchParameters parameters) {
        //
        // this allows you to intercept the data fetcher used to fetch a field and provide another one, perhaps
        // that enforces certain behaviours or has certain side effects on the data
        //
        return dataFetcher;
    }

    @Override
    public CompletableFuture<ExecutionResult> instrumentExecutionResult(ExecutionResult executionResult, InstrumentationExecutionParameters parameters) {
        //
        // this allows you to instrument the execution result some how.  For example the Tracing support uses this to put
        // the `extensions` map of data in place
        //
        return CompletableFuture.completedFuture(executionResult);
    }
}
