package com.kirak.to.booking;

/**
 * Created by Kir on 14.08.2017.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * TO-to-JSON object for view based on <a href="https://github.com/mbielanczuk/jQuery.Gantt/> Gantt chart
 */

public class ChartTo implements Serializable {

    private final String name;

    private final String desc;

    private final List<ChartValue> values;

    public ChartTo(@JsonProperty("name") String name,
                   @JsonProperty("desc") String desc,
                   @JsonProperty("values") List<ChartValue> values) {
        this.name = name;
        this.desc = desc;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<ChartValue> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "ChartTo{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", values='" + values + '\'' +
                '}';
    }
}
