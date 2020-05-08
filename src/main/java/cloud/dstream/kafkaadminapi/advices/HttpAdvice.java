package cloud.dstream.kafkaadminapi.advices;

import cloud.dstream.kafkaadminapi.dtos.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class HttpAdvice {
    private Logger logger = LoggerFactory.getLogger(HttpAdvice.class);

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseDTO notFoundHandler(NoHandlerFoundException ex) {
        return new ResponseDTO(404, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseDTO internalErrorHandler(Exception ex, WebRequest request) {
        logger.error("Unhandled error", ex);
        return new ResponseDTO(500, ex.getMessage());
    }
}
