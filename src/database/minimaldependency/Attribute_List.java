package database.minimaldependency;

import java.util.ArrayList;

public class Attribute_List {
    public ArrayList<Attribute> attributes;
    public Attribute_List(){
        attributes=new ArrayList<Attribute>();
    }
    public boolean is_equal(Attribute_List other) {
        int length0 = this.attributes.size();
        int length1 = other.attributes.size();
        if (length0 != length1) return false;
        for (int i = 0; i < length0; i++) {
            Attribute a = this.attributes.get(i);
            Attribute b = other.attributes.get(i);
            if (!a.getName().equals(b.getName()))
                return false;
        }
        return true;
    }
}
