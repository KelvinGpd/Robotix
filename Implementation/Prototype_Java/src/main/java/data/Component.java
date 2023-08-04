package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Component implements Serializable {
    String name;
    String type;
    String desc;
    int price;
    public Component(String name, String type, String desc, int price){
        this.name=name;
        this.type=type;
        this.desc=desc;
        this.price=price;
    }
    @JsonCreator
    public Component(@JsonProperty("name") String name, @JsonProperty("type") String type){
        this.name=name;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}

