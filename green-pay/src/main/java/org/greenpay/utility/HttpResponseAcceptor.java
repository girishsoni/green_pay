package org.greenpay.utility;

import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;

public class HttpResponseAcceptor implements BiFunction<Response,Throwable, Boolean> {

    private AsyncResponse asyncResponse;

    public HttpResponseAcceptor(final AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    @Override
    public Boolean apply(final Response response, final Throwable throwable) {
        if (throwable == null){
            return asyncResponse.resume(response);
        } else if (throwable instanceof CompletionException){
            return asyncResponse.resume(throwable.getCause());
        } else {
            return asyncResponse.resume(throwable);
        }
    }

    public static HttpResponseAcceptor create(final AsyncResponse asyncResponse) {
        return new HttpResponseAcceptor(asyncResponse);
    }
}
