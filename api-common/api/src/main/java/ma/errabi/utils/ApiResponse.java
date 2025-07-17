package ma.errabi.utils;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse {
    private String errorCode;
    private String errorDescription;
    private Instant timestamp;
}
