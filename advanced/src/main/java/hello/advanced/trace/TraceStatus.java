package hello.advanced.trace;


/*
* 특정 로그가 시작하고 끝날때 사용됨
* 한 로그가 시작되고 끝날 때까지의 소요 시간 : startTimeMs을 통해서 구함
* */
public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
