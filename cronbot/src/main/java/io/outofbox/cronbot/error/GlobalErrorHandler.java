package io.outofbox.cronbot.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Global error handler controller
 * @author ahelmy
 */
@Controller
public class GlobalErrorHandler {


    /**
     * Not found exception handling
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ApiError handleNotFound(HttpServletRequest req, Exception ex){
        return new ApiError(HttpStatus.NOT_FOUND,ex.getMessage(),ex);
    }

    /**
     * General exception handling
     * @param req HttpServletRequest
     * @param ex Exception
     * @return An error in {@link ApiError}
     */
    @ExceptionHandler(Exception.class)
    public ApiError handleError(HttpServletRequest req, Exception ex) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),ex);
    }
}
