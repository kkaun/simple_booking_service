/**
 * Created by Кира on 15.08.2017.
 */

var ajaxUrl = "/object/chart/";

$("#ganttTable").gantt({
    source: ajaxUrl,
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

<!--
   jQuery(function () {
       jQuery(".gantt").gantt({
           source: "<url-to-data.json>"
           itemsPerPage: 5,
           months: [
               "January", "February", "March",
               "April", "May", "June", "July",
               "August", "September", "October",
               "November", "December"],
           dow: ["S", "M", "T", "W", "Th", "F", "Sa"],
           navigate: 'scroll',
           scale: 'hours',
           maxScale: 'days',
           minScale: 'hours',
           holidays: [
               "\/Date(1293836400000)\/",
               ...,
               "\/Date(1351724400000)\/"
           ]
       })
   });
-->