package com.kirak.web.abstr;

import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.to.PlaceTo;
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

    private final CountryService countryService;

    @Autowired
    public RegionAbstractController(CityService cityService, CountryService countryService){
        this.cityService = cityService;
        this.countryService = countryService;
    }

    public void create(PlaceTo placeTo){
        LOG.info("Saving {}", placeTo);
        checkNew(placeTo);
        cityService.save(placeTo, countryService.getAll());
    }

    public void update(PlaceTo placeTo, int id){
        LOG.info("Updating {}", placeTo);
        checkId(placeTo, id);
        cityService.update(placeTo);
    }

    public PlaceTo get(Integer id) {
        LOG.info("Getting city {}", id);
        return RegionUtil.asPlaceTo(cityService.get(id));
    }

    public List<PlaceTo> getAll(){
        LOG.info("Getting all cities");
        return RegionUtil.getPlaceTos(cityService.getAll());
    }

    public List<PlaceTo> getAllByRegion(String region){
        LOG.info("Getting cities by region {}", region);
        return RegionUtil.getPlaceTos(RegionUtil.getCitiesByRegionName(region, cityService.getAll()));
    }


}
