package exceptions;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class ErrorPayload {
    private String message;
    private Date timestamp;

