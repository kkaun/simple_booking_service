package com.kirak.web.abstr;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.to.PlaceTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.FileUploadUtil;
import com.kirak.util.exception.model.cross_model.DemoEntityModificationException;
import com.kirak.util.exception.model.region.RegionDuplNameException;
import com.kirak.util.exception.model.region.RegionHasHotelsException;
import com.kirak.util.model.RegionUtil;
import com.kirak.web.ExceptionViewHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkNew;

/**
 * Created by Kir on 03.08.2017.
 */
public abstract class RegionAbstractController {

    public static final String EXCEPTION_REGION_HAS_HOTELS = "exception.region.hasHotels";
    public static final String EXCEPTION_REGION_IS_DEMO_CITY = "exception.region.isDemoCity";
    public static final String EXCEPTION_REGION_DUPL_NAME = "exception.region.duplicateName";
    public static final String EXCEPTION_REGION_MODIFICATION_RESTRICTION = "exception.region.modificationRestriction";
    
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final CityService cityService;

    private final CountryService countryService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public RegionAbstractController(CityService cityService, CountryService countryService){
        this.cityService = cityService;
        this.countryService = countryService;
    }

    public void create(PlaceTo placeTo){
        LOG.info("Saving {}", placeTo);
        checkNew(placeTo);
        checkCreateBusinessRestrictions(placeTo);
        cityService.save(placeTo, countryService.getAll());
    }

    public void update(PlaceTo placeTo, int id){
        LOG.info("Updating {}", placeTo);
        checkId(placeTo, id);
        checkAllBusinessRestrictions(id);
        checkEditBusinessRestrictions(placeTo);
        City city = cityService.get(id);
        cityService.update(RegionUtil.updateCityFromPlaceTo(placeTo, city));
    }

    public PlaceTo get(Integer id) {
        LOG.info("Getting city {}", id);
        return RegionUtil.asPlaceTo(cityService.get(id));
    }

    public void delete(Integer id){
        LOG.info("Deleting city {}", id);
        checkAllBusinessRestrictions(id);
        checkDeleteBusinessRestrictions(id);
        cityService.delete(id);
    }

    public List<PlaceTo> getAll(){
        LOG.info("Getting all cities");
        return RegionUtil.getPlaceTos(cityService.getAll());
    }

    public List<Country> getAllCountries(){
        return countryService.getAll();
    }

    public List<PlaceTo> getAllByRegion(String region){
        LOG.info("Getting cities by region {}", region);
        return region != null && !region.isEmpty() ?
                RegionUtil.getPlaceTos(RegionUtil.getCitiesByRegionName(region, cityService.getAll()))
                : RegionUtil.getPlaceTos(cityService.getAll());
    }

    public void setImage(Integer id, MultipartFile multipartFile) {
        City city = cityService.get(id);
        Random random = new Random();
        String fileName = "region_img_id_" + String.valueOf(id) +
                "_" + String.valueOf(random.nextInt(10000) + 1) + ".jpg";
        if(FileUploadUtil.fileUploaded(multipartFile, fileName)) {
            city.setImgPath("/images/" + fileName);
            cityService.save(city);
        }
    }

    public Country getCountryByName(PlaceTo placeTo){

        return countryService.getAll().stream().filter(country ->
                Arrays.equals(country.getName().toLowerCase().replaceAll("^[,\\s]+", "").split("[,\\s]+"),
                        placeTo.getCountryName().toLowerCase().replaceAll("^[,\\s]+", "").split("[,\\s]+")))
                .findFirst().orElse(null);
    }


    public void checkAllBusinessRestrictions(int id){
        if(id >= 100000 && id < 100595)
            throw new DemoEntityModificationException(EXCEPTION_REGION_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    public void checkCreateBusinessRestrictions(PlaceTo placeTo){
        Country foundCountry = getCountryByName(placeTo);
        if(RegionUtil.isNewRegionNameDuplicated(cityService.getAllByRegionId(foundCountry.getId()), foundCountry, placeTo)){
            throw new RegionDuplNameException(EXCEPTION_REGION_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
        }
    }

    public void checkEditBusinessRestrictions(PlaceTo placeTo){
        Country foundCountry = getCountryByName(placeTo);
        if(RegionUtil.isEditedPlaceNameDuplicated(cityService.getAllByRegionId(foundCountry.getId()), foundCountry, placeTo)){
            throw new RegionDuplNameException(EXCEPTION_REGION_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
        }
    }

    public void checkDeleteBusinessRestrictions(int id){
        if(cityService.get(id).getHotels().size() > 0)
            throw new RegionHasHotelsException(EXCEPTION_REGION_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RegionHasHotelsException.class)
    public ResponseEntity<ErrorInfo> regionHasHotels(HttpServletRequest req, RegionHasHotelsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_REGION_HAS_HOTELS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DemoEntityModificationException.class)
    public ResponseEntity<ErrorInfo> regionIsDemoCity(HttpServletRequest req, DemoEntityModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_REGION_IS_DEMO_CITY, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RegionDuplNameException.class)
    public ResponseEntity<ErrorInfo> regionNmeDuplicated(HttpServletRequest req, RegionDuplNameException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_REGION_DUPL_NAME, HttpStatus.CONFLICT);
    }
}
