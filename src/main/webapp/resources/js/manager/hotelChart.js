/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/hotel_manager/object/chart/";

//
// jQuery.ajax({
//     url: ajaxUrl,
//     type: "GET",
//
//     contentType: 'application/json; charset=utf-8',
//     success: function(resultData) {
//         //here is your json.
//         // process it
//         arr = resultData;
//     },
//     error : function(jqXHR, textStatus, errorThrown) {
//     },
//
//     timeout: 12000
// });

// $(function(){
//     $.getJSON('/api/rest/abc', function(data) {
//         console.log(data);
//     });
// });


$(".ganttTable").gantt({
    source: $.getJSON(ajaxUrl, function(data){
        return data;
    }),
    scale: "weeks",
    minScale: "weeks",
    maxScale: "months",
    onItemClick: function(data) {
        alert("Item clicked - show some details");
    },
    onAddClick: function(dt, rowId) {
        alert("Empty space clicked - add an item!");
    },
    onRender: function() {
        console.log("chart rendered");
    }
});
