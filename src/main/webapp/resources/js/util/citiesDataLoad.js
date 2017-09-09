/**
 * Created by Кира on 09.09.2017.
 */


var indexOptions = {
    url: "/retrieve_city_names",
    getValue: "name",
    list: {
        match: {
            enabled: true
        },
        maxNumberOfElements: 5
    },

    theme: "plate-dark"
};


var primaryFilterOptions = {
    url: "/retrieve_city_names",
    getValue: "name",
    list: {
        match: {
            enabled: true
        },
        maxNumberOfElements: 4
    },

    theme: "primary-filter-plate-dark"
};


var sideFilterOptions = {
    url: "/retrieve_city_names",
    getValue: "name",
    list: {
        match: {
            enabled: true
        },
        maxNumberOfElements: 4
    },

    theme: "side-filter-plate-dark"
};



$(".index-cities-input").easyAutocomplete(indexOptions);
$(".primary-cities-input").easyAutocomplete(primaryFilterOptions);
$(".side-cities-input").easyAutocomplete(sideFilterOptions);