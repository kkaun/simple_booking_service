package com.kirak.web.rest.manager;

import com.kirak.model.Hotel;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.web.abstr.HotelAbstractController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by Kir on 24.06.2017.
 */

@Controller
@RequestMapping(value = HotelRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelRestController extends HotelAbstractController {

    public static final String REST_URL = "/hotels";

    private HotelRestController(HotelService hotelService) {
        super(hotelService);
    }

    @PostMapping(value = "/{cityId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> createWithLocation(@RequestBody Hotel hotel, @PathVariable("cityId") int cityId) {
        Hotel created = super.create(hotel, cityId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @PutMapping(value = "/{id}&{cityId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Hotel update(@RequestBody Hotel hotel, @PathVariable("id") int id, @PathVariable("cityId")int cityId) {
        return super.update(hotel, id, cityId);
    }

//    @Override
//    public void delete(Integer id, int cityId) throws NotFoundException {
//        super.delete(id, cityId);
//    }

    @GetMapping(value = "/{id}&{cityId}")
    @Override
    public Hotel get(@PathVariable("id")Integer id,@PathVariable("cityId") int cityId) {
        return super.get(id,  cityId);
    }

    @GetMapping(value = "/by_city", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<HotelTo> getAllByCity(@RequestParam int cityId) {
        return super.getAllByCity(cityId);
    }

    @GetMapping(value = "/between", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<HotelTo> getBetweenRatings(
            @RequestParam(value = "minRating") double minRating,
            @RequestParam(value = "maxRating") double maxRating) {
        return super.getBetweenRatings(minRating, maxRating);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<HotelTo> getAll() {
        return super.getAll();
    }
}
