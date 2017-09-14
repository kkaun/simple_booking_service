package com.kirak.web.abstr;

import com.kirak.model.User;
import com.kirak.model.UserRole;
import com.kirak.service.UserService;
import com.kirak.to.UserTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.exception.model.user.*;
import com.kirak.util.model.UserUtil;
import com.kirak.web.ExceptionViewHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.kirak.util.ValidationUtil.*;

/**
 * Created by Kir on 15.06.2017.
 */


public abstract class UserAbstractController {

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";
    public static final String EXCEPTION_USER_IS_ANONYMOUS = "exception.user.isAnonymous";
    public static final String EXCEPTION_USER_HAS_BOOKINGS = "exception.user.hasActiveBookings";
    public static final String EXCEPTION_USER_IS_DEMO_MANAGER = "exception.user.isDemoManager";
    public static final String EXCEPTION_USER_IS_DEMO_ADMIN = "exception.user.isDemoAdmin";
    public static final String EXCEPTION_USER_IS_DEMO_USER = "exception.user.isDemoUser";
    public static final String EXCEPTION_USER_HAS_MANAGEABLE_HOTELS = "exception.user.hasManageableHotels";
    public static final String EXCEPTION_USER_MODIFICATION_RESTRICTION = "exception.user.modificationRestriction";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public UserAbstractController(UserService service) {
        this.userService = service;
    }

    public List<UserTo> getAll() {
        log.info("getAllHotelTos");
        return UserUtil.getAllTos(userService.getAll().stream()
        .filter(user -> !user.getRoles().contains(UserRole.ROLE_ADMIN))
                .collect(Collectors.toList()));
    }

    public UserTo getTo(int id) {
        log.info("getPlacementMap {}", id);
        return UserUtil.asTo(userService.get(id));
    }

    public User getUser(int id) {
        log.info("getPlacementMap {}", id);
        return userService.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userService.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkBusinessRestrictions(id);
        checkActivityRestrictions(id);
        userService.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        checkIdConsistency(user, id);
        checkBusinessRestrictions(id);
        userService.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        checkIdConsistency(userTo, id);
        checkBusinessRestrictions(id);
        userService.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info((enabled ? "enable " : "deactivate ") + id);
        checkBusinessRestrictions(id);
        userService.enable(id, enabled);
    }

    public void checkActivityRestrictions(int id){
        User user = userService.get(id);
        if (user.getRoles().contains(UserRole.ROLE_MANAGER) && !user.getHotels().isEmpty())
            throw new ManagerHasHotelsException(EXCEPTION_USER_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        if (user.getRoles().contains(UserRole.ROLE_USER) && !user.getBookings().isEmpty())
            throw new UserHasBookingsException(EXCEPTION_USER_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    public void checkBusinessRestrictions(int id){
        if(id == 100000 || id == 100001|| id == 100002)
            throw new DemoUserModificationException(EXCEPTION_USER_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        if (id == 100004)
            throw new AdminModificationException(EXCEPTION_USER_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        if (id == 100003)
            throw new DemoManagerModificationException(EXCEPTION_USER_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        if (userService.get(id).isAnon())
            throw new UserIsAnonymousException(EXCEPTION_USER_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }


    @ExceptionHandler(UserHasBookingsException.class)
    public ResponseEntity<ErrorInfo> hasBookingsException(HttpServletRequest req, UserHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_USER_HAS_BOOKINGS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserIsAnonymousException.class)
    public ResponseEntity<ErrorInfo> isAnon(HttpServletRequest req, UserIsAnonymousException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_USER_IS_ANONYMOUS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DemoUserModificationException.class)
    public ResponseEntity<ErrorInfo> isDemoUser(HttpServletRequest req, DemoUserModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_USER_IS_DEMO_USER, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DemoManagerModificationException.class)
    public ResponseEntity<ErrorInfo> isDemoManager(HttpServletRequest req, DemoManagerModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_USER_IS_DEMO_MANAGER, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AdminModificationException.class)
    public ResponseEntity<ErrorInfo> isDemoAdmin(HttpServletRequest req, AdminModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_USER_IS_DEMO_ADMIN, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ManagerHasHotelsException.class)
    public ResponseEntity<ErrorInfo> hasManageableHotels(HttpServletRequest req, ManagerHasHotelsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_USER_HAS_MANAGEABLE_HOTELS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_DUPLICATE_EMAIL, HttpStatus.CONFLICT);
    }


}
