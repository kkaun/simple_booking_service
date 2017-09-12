/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/hotel_manager/object/chart/";


$(".gantt").gantt({
    source: ajaxUrl + "?hotelId=" + objectId,
    months: ["January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"],
    dow:	["S", "M", "T", "W", "T", "F", "S"],
    scale: "days",
    minScale: "hours",
    maxScale: "months",
    navigate: "scroll",
    itemsPerPage: 8,
    // onItemClick: function(data) {
    //     alert("Item clicked - show some details");
    // },
    // onAddClick: function(dt, rowId) {
    //     alert("Empty space clicked - add an item!");
    // },
    onRender: function() {
        console.log("chart rendered");
    }
});
