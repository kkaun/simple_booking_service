package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;

/**
 * Created by Kir on 23.08.2017.
 */

//TODO: add photo uploading feature to entity and business logic
/**
 * Интерфейс MultipartResolver используется для загрузки файлов.
 * Существуют две реализации: CommonsMultipartResolver и StandardServletMultipartResolver,
 * которые позволяют фреймворку загружать файлы.
 * По умолчанию этот интерфейс не включается в приложении и необходимо указывать его в файле конфигурации.
 * После настройки любой запрос о загрузке будет отправляться этому интерфейсу.

 <beans:bean id="multipartResolver"
 class="org.springframework.web.multipart.commons.CommonsMultipartResolver">

 //setting maximum upload size
 <beans:property name="maxUploadSize" value="100000" />

 </beans:bean>
 */

@Entity
@Table(name = "object_image")
public class ObjectImage extends BaseIntEntity {

    @Column(name = "img_path")
    private String imgPath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public ObjectImage(String imgPath, Apartment apartment, Hotel hotel) {
        this(null, imgPath, apartment, hotel);
    }

    public ObjectImage(Integer id, String imgPath, Apartment apartment, Hotel hotel) {
        super(id);
        this.imgPath = imgPath;
        this.apartment = apartment;
        this.hotel = hotel;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "ObjectImage{" +
                "imgPath='" + imgPath + '\'' +
                ", apartment=" + apartment +
                ", hotel=" + hotel +
                '}';
    }
}
