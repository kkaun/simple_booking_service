package com.kirak.web.abstr;

import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.to.PlaceTo;
import com.kirak.util.model.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
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
    private ApplicationContext appContext;

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
        return region != null && !region.isEmpty() ?
                RegionUtil.getPlaceTos(RegionUtil.getCitiesByRegionName(region, cityService.getAll()))
                : RegionUtil.getPlaceTos(cityService.getAll());
    }

    public String getImagePath(Integer id){
        return cityService.get(id).getImgPath();
    }

    public void setImage(Integer id, MultipartFile multipartFile) {

//        Resource resource = appContext.getResource("");
//
//        URL sqlScriptUrl = MyServletContextListener.class
//                .getClassLoader().getResource("sql/script.sql");
//
//        File file = new File("/opt/stuff/uploads", multipartFile.getOriginalFilename());
    }
}
