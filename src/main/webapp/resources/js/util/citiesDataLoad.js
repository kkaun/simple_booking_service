/**
 * Created by Кира on 09.09.2017.
 */


var indexOptions = {
    url: "/retrieve_regions/cities",
    getValue: "name",
    template: {
        type: "description",
        fields: {
            description: "countryName"
        }
    },
    list: {
        match: {
            enabled: true
        },
        sort: {
            enabled: true
        },
        maxNumberOfElements: 5
    },
    theme: "plate-dark"
};


var primaryFilterOptions = {
    url: "/retrieve_regions/cities",
    getValue: "name",
    template: {
        type: "description",
        fields: {
            description: "countryName"
        }
    },
    list: {
        match: {
            enabled: true
        },
        sort: {
            enabled: true
        },
        maxNumberOfElements: 4
    },
    theme: "primary-filter-plate-dark"
};


var sideFilterOptions = {
    url: "/retrieve_regions/cities",
    getValue: "name",
    template: {
        type: "description",
        fields: {
            description: "countryName"
        }
    },
    list: {
        match: {
            enabled: true
        },
        sort: {
            enabled: true
        },
        maxNumberOfElements: 4
    },
    theme: "side-filter-plate-dark"
};


var objectCountriesOptions = {

    url: "/retrieve_regions/countries",
    getValue: "name",
    list: {
        match: {
            enabled: true
        },
        sort: {
            enabled: true
        },
        maxNumberOfElements: 4
    },
    theme: "form-plate-dark"
};


var objectPlacesOptions = {

    url: "/retrieve_regions/cities",
    getValue: "name",
    template: {
        type: "description",
        fields: {
            description: "countryName"
        }
    },
    list: {
        match: {
            enabled: true
        },
        sort: {
            enabled: true
        },
        maxNumberOfElements: 4
    },
    theme: "form-plate-dark"
};


$("#countryNamesList").easyAutocomplete(objectCountriesOptions);
$("#cityNamesList").easyAutocomplete(objectPlacesOptions);
$(".index-cities-input").easyAutocomplete(indexOptions);
$(".primary-cities-input").easyAutocomplete(primaryFilterOptions);
$(".side-cities-input").easyAutocomplete(sideFilterOptions);