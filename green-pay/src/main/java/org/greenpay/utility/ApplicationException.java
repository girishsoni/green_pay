package org.greenpay.utility;

import com.twitter.util.Future;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Getter
public class ApplicationException extends RuntimeException {
    private final long timestamp;
    private final ErrorType errorType;
    private final transient Map<String, String> parameters;
    private final String source;
    private final String target;

    @Builder
    protected ApplicationException(
            final ErrorType errorType,
            final Throwable throwable, @Singular final Map<String, String> parameters,
            final String source, final String target){
        super(throwable);
        if(errorType == null){
            throw new NullPointerException("ErrorType cannot be null");
        }
        this.timestamp = LocalDateTime.now(Clock.systemUTC()).toEpochSecond(ZoneOffset.UTC);
        this.errorType = errorType;
        this.parameters = parameters;
        this.source = source;
        this.target = target;
    }

    public long getTimestamp() { return timestamp; }

    public ErrorType getErrorType() { return errorType; }

    public Map<String, String> getParameters() { return parameters; }

    public String getParameter(final String name){ return parameters.get(name); }

    @Override
    public String getMessage() {
        return String.format("code=%s; message=%s; source=%s; target=%s",
                errorType.getCode(), errorType.getMessage(), source, target) +
                parameters.entrySet().stream()
                        .map(entry -> String.format(" %s=%s;", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining(""));
    }

    public <T> CompletableFuture<T> toFuture() {
        final CompletableFuture<T> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(this);
        return completableFuture;
    }

    public <T> Future<T> toFinagleFuture() { return Future.exception(this); }

    //public <T> Mono<T> toMono() { return Mono.error(this); }

    //public <T> Flux<T> toFlux() { return Flux.error(this); }
}
