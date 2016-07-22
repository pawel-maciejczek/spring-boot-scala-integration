package pl.maciejcp.util;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.async.DeferredResult;

public class ListenableFutureAdapter<T> extends DeferredResult<T> {

    public ListenableFutureAdapter(final ListenableFuture<T> target) {
        target.addCallback(this::setResult, this::setErrorResult);
    }
}
