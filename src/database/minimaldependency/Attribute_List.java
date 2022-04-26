package database.minimaldependency;

import java.util.ArrayList;

public class Attribute_List {
    public ArrayList<Attribute> attributes;
    public Attribute_List(){
        attributes= new ArrayList<>();
    }
    @Override
    public boolean equals(Object other) {
        Attribute_List o=(Attribute_List) other;
        int length0 = this.attributes.size();
        int length1 = o.attributes.size();
        if (length0 != length1) return false;
        for (int i = 0; i < length0; i++) {
            Attribute a = this.attributes.get(i);
            Attribute b = o.attributes.get(i);
            if (!a.getName().equals(b.getName()))
                return false;
        }
        return true;
    }

    public Attribute getItem(int index){
        return this.attributes.get(index);
    }
    @Override
    public String toString()
    {
        String str="{";
        for(int i=0;i<this.attributes.size();i++){
            str+=this.attributes.get(i).getName();
            str+=",";
        }
        str=str.substring(0,str.length()-1);
        str+="}";
        return str;
    }

    public boolean remove(Attribute r){
        return this.attributes.remove(r);
    }

    public int index(Attribute r){
        return this.attributes.indexOf(r);
    }

    public void append(Attribute r){
        this.attributes.add(r);
    }

    public void appendAll(Attribute_List r) {
        for (int i=0;i<r.attributes.size();i++){
            this.attributes.add(r.attributes.get(i));
        }
    }

    public ArrayList<Attribute> getAttributes(){
        return this.attributes;
    }

    public void copy(Attribute_List r){
        this.attributes= new ArrayList<>(r.attributes);
    }

    public Attribute_List clone(){
        Attribute_List new_a=new Attribute_List();
        new_a.attributes= new ArrayList<>(this.attributes);
        return new_a;
    }

    public void sort(){
        this.attributes.sort(new SortByIndex());
    }

    public int count(){
        return this.attributes.size();
    }
}
