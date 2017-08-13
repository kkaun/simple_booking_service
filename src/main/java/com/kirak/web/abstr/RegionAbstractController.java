package com.kirak.web.abstr;

import com.kirak.model.City;
import com.kirak.service.CityService;
import com.kirak.util.model.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkNew;

/**
 * Created by Kir on 03.08.2017.
 */
public abstract class RegionAbstractController {
    
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final CityService cityService;

    @Autowired
    public RegionAbstractController(CityService cityService){
        this.cityService = cityService;
    }

    public City create(City city){
        LOG.info("Saving {}", city);
        checkNew(city);
        return cityService.save(city);
    }

    public void update(City city, int id){
        LOG.info("Updating {}", city);
        checkId(city, id);
        cityService.update(city);
    }

    public City get(Integer id) {
        LOG.info("Getting city {}", id);
        return cityService.get(id);
    }

    public List<City> getAll(){
        LOG.info("Getting all cities");
        return cityService.getAll();
    }

    public List<City> getAllByRegion(String region){
        LOG.info("Getting cities by region {}", region);
        return RegionUtil.getCitiesByRegionName(region, cityService.getAll());
    }


}
