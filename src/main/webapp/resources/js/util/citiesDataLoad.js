/**
 * Created by Кира on 09.09.2017.
 */


var options = {
    url: "/retrieve_city_names/",
    getValue: "name",
    list: {
        match: {
            enabled: true
        }
    }
};

$(".cities-input").easyAutocomplete(options);