package model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class MapObject {
    public Integer distance;
    public String label;

    public MapObject(Integer distance, String label) {
        this.distance = distance;
        this.label = label;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "MapObject{" +
                "distance=" + distance +
                ", label='" + label + '\'' +
                '}';
    }
}
