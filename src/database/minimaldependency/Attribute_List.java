package database.minimaldependency;

import java.util.ArrayList;

public class Attribute_List {
    public ArrayList<Attribute> attributes;
    public Attribute_List(){
        attributes=new ArrayList<Attribute>();
    }
    @Override
    public boolean equals(Object other) {
        Attribute_List o=(Attribute_List) other;
        int length0 = attributes.size();
        int length1 = o.attributes.size();
        if (length0 != length1) return false;
        for (int i = 0; i < length0; i++) {
            Attribute a = attributes.get(i);
            Attribute b = o.attributes.get(i);
            if (!a.getName().equals(b.getName()))
                return false;
        }
        return true;
    }

    public Attribute getItem(int index){
        return attributes.get(index);
    }
    @Override
    public String toString()
    {
        String str="{";
        for(int i=0;i<attributes.size();i++){
            str+=attributes.get(i).getName();
            str+=",";
        }
        str=str.substring(0,str.length()-1);
        str+="}";
        // 返回值,example{A,B,C,D,E}
        return str;
    }

    public boolean remove(Attribute r){
        return attributes.remove(r);
    }

    public int index(Attribute r){
        return attributes.indexOf(r);
    }

    public void append(Attribute r){
        attributes.add(r);
    }

    public void appendAll(Attribute_List r) {
        for (int i=0;i<r.attributes.size();i++){
            attributes.add(r.attributes.get(i));
        }
    }

    public ArrayList<Attribute> getAttributes(){
        return this.attributes;
    }

    public void copy(Attribute_List r){
        attributes=new ArrayList<Attribute>(r.attributes);
    }

    public Attribute_List clone(){
        Attribute_List new_a=new Attribute_List();
        new_a.attributes=new ArrayList<Attribute>(attributes);
        return new_a;
    }

    public void sort(){
        attributes.sort(new SortByIndex());
    }

    public int count(){
        return attributes.size();
    }
}
