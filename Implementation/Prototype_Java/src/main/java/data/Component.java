package data;

public class Component {
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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return ("Nom: " + name + "\nType: "+ type + "\nDescription: "+ desc +"\nPrix: "+ price);
    }
}

