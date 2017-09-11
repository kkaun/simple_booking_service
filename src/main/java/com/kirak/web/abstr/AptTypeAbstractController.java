package com.kirak.web.abstr;

import com.kirak.service.ApartmentService;
import com.kirak.service.AptTypeService;
import com.kirak.service.HotelService;
import com.kirak.to.AptTypeTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.exception.model.cross_model.DemoEntityModificationException;
import com.kirak.util.exception.model.apt_type.AptTypeHasApartmentsException;
import com.kirak.util.model.AptTypeUtil;
import com.kirak.web.ExceptionViewHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kir on 03.08.2017.
 */
public abstract class AptTypeAbstractController {

    public static final String EXCEPTION_APT_TYPE_HAS_APARTMENTS = "exception.aptType.hasApartments";
    public static final String EXCEPTION_APT_TYPE_IS_DEMO_TYPE = "exception.aptType.iDemoAptType";
    public static final String EXCEPTION_APT_TYPE_MODIFICATION_RESTRICTION = "exception.aptType.modificationRestriction";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final AptTypeService aptTypeService;

    private final HotelService hotelService;

    private final ApartmentService apartmentService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public AptTypeAbstractController(AptTypeService aptTypeService, HotelService hotelService, ApartmentService apartmentService) {
        this.aptTypeService = aptTypeService;
        this.hotelService = hotelService;
        this.apartmentService = apartmentService;
    }

    public void save(AptTypeTo aptTypeTo){
        LOG.info("Saving {}", aptTypeTo);
        aptTypeService.save(aptTypeTo);
    }

    public void update(AptTypeTo aptTypeTo){
        LOG.info("Updating {}", aptTypeTo);
        checkAllBusinessRestrictions(aptTypeTo.getId());
        aptTypeService.update(aptTypeTo, aptTypeService.get(aptTypeTo.getId()), hotelService.getAll());
    }

    public void delete(Short id){
        LOG.info("Deleting aptType {}", id);
        checkAllBusinessRestrictions(id);
        aptTypeService.delete(id);
    }

    public AptTypeTo get(Short id){
        LOG.info("Getting aptType {}", id);
        return AptTypeUtil.asTo(aptTypeService.get(id), hotelService.getAll());
    }

    public List<AptTypeTo> getAll(){
        LOG.info("Getting all aptTypes");
        return AptTypeUtil.getToList(aptTypeService.getAll(), hotelService.getAll());
    }


    public void checkAllBusinessRestrictions(short id){
        if(id >= 1 && id < 29)
            throw new DemoEntityModificationException(EXCEPTION_APT_TYPE_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
        if(apartmentService.getAll().stream()
                .filter(apartment -> Objects.equals(apartment.getType().getId(), id)).count() > 0)
            throw new AptTypeHasApartmentsException(EXCEPTION_APT_TYPE_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AptTypeHasApartmentsException.class)
    public ResponseEntity<ErrorInfo> aptTypeHasApartments(HttpServletRequest req, AptTypeHasApartmentsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APT_TYPE_HAS_APARTMENTS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DemoEntityModificationException.class)
    public ResponseEntity<ErrorInfo> aptTypeIsDemo(HttpServletRequest req, DemoEntityModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APT_TYPE_IS_DEMO_TYPE, HttpStatus.CONFLICT);
    }
}
