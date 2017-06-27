package com.kirak.web.abstr;

import com.kirak.Profile;
import com.kirak.model.User;
import com.kirak.service.UserService;
import com.kirak.to.UserTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.exception.ApplicationException;
import com.kirak.web.ExceptionViewHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkNew;

/**
 * Created by Kir on 15.06.2017.
 */


public abstract class SystemAdminAbstractController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";
    public static final String EXCEPTION_MODIFICATION_RESTRICTION = "exception.user.modificationRestriction";

    private final UserService service;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    private boolean modificationRestriction;

    @Autowired
    public void setEnvironment(Environment environment) {
        modificationRestriction = environment.acceptsProfiles(Profile.HEROKU);
    }

    public SystemAdminAbstractController(UserService service) {
        this.service = service;
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkModificationAllowed(id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        checkId(user, id);
        checkModificationAllowed(id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        checkId(userTo, id);
        checkModificationAllowed(id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

//    public void enable(int id, boolean enabled) {
//        log.info((enabled ? "enable " : "disable ") + id);
//        checkModificationAllowed(id);
//        service.enable(id, enabled);
//    }

    public void checkModificationAllowed(int id) {
        if (modificationRestriction) {
            throw new ApplicationException(EXCEPTION_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_DUPLICATE_EMAIL, HttpStatus.CONFLICT);
    }
}
