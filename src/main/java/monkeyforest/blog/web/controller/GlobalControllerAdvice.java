package monkeyforest.blog.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Value("${application.version}")
    private String version;
    @Value("${application.group}")
    private String group;

    @ModelAttribute("version")
    public String getVersion() {
        return version;
    }
    @ModelAttribute("group")
    public String getGroup() {
        return group;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ModelAndView handleConflict(HttpServletRequest request, Exception e) {
        var modelAndView = new ModelAndView("error/409");
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }
}
