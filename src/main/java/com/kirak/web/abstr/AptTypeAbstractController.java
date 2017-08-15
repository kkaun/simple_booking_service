package com.kirak.web.abstr;

import com.kirak.model.AptType;
import com.kirak.service.AptTypeService;
import com.kirak.service.HotelService;
import com.kirak.to.AptTypeTo;
import com.kirak.util.exception.NotFoundException;
import com.kirak.util.model.AptTypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */
public abstract class AptTypeAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final AptTypeService aptTypeService;

    private final HotelService hotelService;

    @Autowired
    public AptTypeAbstractController(AptTypeService aptTypeService, HotelService hotelService) {
        this.aptTypeService = aptTypeService;
        this.hotelService = hotelService;
    }

    public void save(AptTypeTo aptTypeTo){
        LOG.info("Saving {}", aptTypeTo);
        aptTypeService.save(aptTypeTo);
    }

    public void update(AptTypeTo aptTypeTo){
        LOG.info("Updating {}", aptTypeTo);
        aptTypeService.update(aptTypeTo, aptTypeService.get(aptTypeTo.getId()), hotelService.getAll());
    }

    public void delete(Short id){
        LOG.info("Deleting aptType {}", id);
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

}
