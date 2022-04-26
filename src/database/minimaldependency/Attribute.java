package database.minimaldependency;

import java.util.Comparator;
import java.util.Objects;

public class Attribute {
    private String name;

    public Attribute(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return this.name;
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.name);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;
        Attribute a = (Attribute) o;
        return a.getName().equals(this.name);
    }

    public boolean less_than(Attribute other){
        if(this.name.compareTo(other.getName())<0){
            return true;
        }
        return false;
    }

    public boolean great_than(Attribute other){
        if(this.name.compareTo(other.getName())>0){
            return true;
        }
        return false;
    }

    @Override
    public Attribute clone(){
        return new Attribute(this.name);
    }
}

class SortByIndex implements Comparator<Attribute> {
    @Override
    public int compare(Attribute o1,Attribute o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
