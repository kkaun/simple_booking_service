package com.kirak.web.abstr;

import com.kirak.model.AptType;
import com.kirak.service.AptTypeService;
import com.kirak.util.exception.NotFoundException;
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

    @Autowired
    public AptTypeAbstractController(AptTypeService aptTypeService) {
        this.aptTypeService = aptTypeService;
    }

    public void save(AptType type){
        LOG.info("Saving {}", type);
        aptTypeService.save(type);
    }

    public void update(AptType type){
        LOG.info("Updating {}", type);
        aptTypeService.update(type);
    }

    public void delete(Short id){
        LOG.info("Deleting aptType {}", id);
        aptTypeService.delete(id);
    }

    public AptType get(Short id){
        LOG.info("Getting aptType {}", id);
        return aptTypeService.get(id);
    }

    public List<AptType> getAll(){
        LOG.info("Getting all aptTypes");
        return aptTypeService.getAll();
    }

}
