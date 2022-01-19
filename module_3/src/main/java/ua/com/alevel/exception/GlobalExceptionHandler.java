package ua.com.alevel.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ModelAndView defaultErrorHandler(EntityNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }
}