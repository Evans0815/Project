package com.evans.project.common.enums;

/**
 * @author Evans
 * @date 2026/3/14
 */
public enum StatusCode {

    OK(200, StatusCode.Series.SUCCESSFUL, "OK"),
    NO_CONTENT(204, StatusCode.Series.SUCCESSFUL, "No Content"),
    BAD_REQUEST(400, StatusCode.Series.CLIENT_ERROR, "Bad Request"),
    UNAUTHORIZED(401, StatusCode.Series.CLIENT_ERROR, "Unauthorized"),
    FORBIDDEN(403, StatusCode.Series.CLIENT_ERROR, "Forbidden"),
    NOT_FOUND(404, StatusCode.Series.CLIENT_ERROR, "Not Found"),
    METHOD_NOT_ALLOWED(405, StatusCode.Series.CLIENT_ERROR, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, StatusCode.Series.CLIENT_ERROR, "Not Acceptable"),
    TOO_MANY_REQUESTS(429, StatusCode.Series.CLIENT_ERROR, "Too Many Requests"),
    INTERNAL_SERVER_ERROR(500, StatusCode.Series.SERVER_ERROR, "Internal Server Error"),
    NOT_IMPLEMENTED(501, StatusCode.Series.SERVER_ERROR, "Not Implemented"),
    BAD_GATEWAY(502, StatusCode.Series.SERVER_ERROR, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, StatusCode.Series.SERVER_ERROR, "Service Unavailable"),
    ;

    private static final StatusCode[] VALUES = values();
    private final int value;
    private final Series series;
    private final String reasonPhrase;


    StatusCode(int value, Series series, String reasonPhrase) {
        this.value = value;
        this.series = series;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public Series series() {
        return this.series;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public enum Series {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        public static Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            for (Series series : values()) {
                if (series.value == seriesCode) {
                    return series;
                }
            }

            return null;
        }
    }

}
