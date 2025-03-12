package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Error response.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class ErrorResponse {
    private int statusCode;
    private String error;
    private String message;
    private String details;

    public static ErrorResponse of(int statusCode, String error, String message, String details) {
        return new ErrorResponse()
                .setStatusCode(statusCode)
                .setError(error)
                .setMessage(message)
                .setDetails(details);
    }
}
