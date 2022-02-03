package org.greenpay.utility;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.TimeoutHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.greenpay.utility.GenericErrorType.ASYNC_TIMEOUT;

@Slf4j
public class ApiTimeoutHandler implements TimeoutHandler {

    private static final long DEFAULT_TIMEOUT = 10;
    private final String endpoint;

    private ApiTimeoutHandler(final String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void handleTimeout(final AsyncResponse asyncResponse) {
          log.error("reached timeout for "+endpoint);
          asyncResponse.resume(ApplicationException.builder()
                  .errorType(ASYNC_TIMEOUT)
                  .build());
    }

    public static void setTimeout(final AsyncResponse asyncResponse, final String endpoint) {
        setTimeout(asyncResponse, DEFAULT_TIMEOUT, TimeUnit.SECONDS, endpoint);
    }

    public static void setTimeout(final AsyncResponse asyncResponse, final long time, final TimeUnit timeUnit, final String endpoint) {
        asyncResponse.setTimeout(time, timeUnit);
        asyncResponse.setTimeoutHandler(new ApiTimeoutHandler(endpoint));
    }


}
