package pl.maciejcp.util.concurrent;

import org.springframework.web.context.request.async.DeferredResult;
import scala.PartialFunction;
import scala.concurrent.ExecutionContext;
import scala.runtime.AbstractPartialFunction;

public class ScalaFutureAdapterFactory {
    private final ExecutionContext executionContext;

    public ScalaFutureAdapterFactory(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public <T> DeferredResult<T> convert(scala.concurrent.Future<T> target) {
        return new DeferredResult<T>() {
            {
                target.onSuccess(ScalaCallback.aCallback(this::setResult), executionContext);
                target.onFailure(ScalaCallback.aCallback(this::setErrorResult), executionContext);
            }
        };
    }
}

@FunctionalInterface
interface ScalaCallback<T> {
    void apply(T result);

    static <T> PartialFunction<T, Void> aCallback(ScalaCallback<T> callback) {
        return new AbstractPartialFunction<T, Void>() {
            @Override
            public boolean isDefinedAt(T x) {
                return true;
            }

            @Override
            public Void apply(T result) {
                callback.apply(result);
                return null;
            }
        };
    }
}