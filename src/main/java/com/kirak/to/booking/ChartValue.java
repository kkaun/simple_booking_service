package com.kirak.to.booking;

/**
 * Created by Kir on 14.08.2017.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * TO-to-JSON array for ChartTo for view based on <a href="https://github.com/mbielanczuk/jQuery.Gantt/> Gantt chart:
 *
 *  to	            String  (Date)	-
    from	        String  (Date)	-
    desc	        String	Text that appears on hover, I think?
    label	        String	Appears on the gantt item.
    customClass	    String	Custom class to be applied to the gantt item.
    dataObj	        All	    A data object that is applied directly to the gantt item.
 *
 */

public class ChartValue implements Serializable {

    /**
     * to: "/Date(1328832000000)/",
     from: "/Date(1333411200000)/",
     desc: "Something",
     label: "Example Value",
     customClass: "ganttRed",
     dataObj: foo.bar[i]
     */

    private final String to;

    private final String from;

    private final String desc;

    private final String label;

    private final String customClass;

    private final String dataObj;

    public ChartValue(@JsonProperty("to") String to,
                      @JsonProperty("from") String from,
                      @JsonProperty("desc") String desc,
                      @JsonProperty("label") String label,
                      @JsonProperty("customClass") String customClass,
                      @JsonProperty("dataObj") String dataObj) {
        this.to = to;
        this.from = from;
        this.desc = desc;
        this.label = label;
        this.customClass = customClass;
        this.dataObj = dataObj;
    }

    @JsonValue
    public String getTo() {
        return to;
    }

    @JsonValue
    public String getFrom() {
        return from;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonValue
    public String getCustomClass() {
        return customClass;
    }

    @JsonValue
    public String getDataObj() {
        return dataObj;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }
}
