package database.minimaldependency;

import java.util.ArrayList;

public class Attribute_Group extends Attribute_List{

    public String toString(){
        if (this.attributes.size()>1){
            String res="(";
            for(int i=0;i<this.attributes.size();i++){
                res+=this.attributes.get(i).getName();
                res+=",";
            }
            res=res.substring(0,res.length()-1);
            res+=")";
            return res;
        }
        else{
            String res="";
            res+=this.attributes.get(0).getName();
            return res;
        }
    }

    public boolean equals(Object other) {
        Attribute_Group r=(Attribute_Group) other;
        int length0=this.attributes.size();
        int length1=r.attributes.size();
        if (length0!=length1)return false;
        for(int i=0;i<length0;i++){
            Attribute a=this.attributes.get(i);
            Attribute b=r.attributes.get(i);
            if (!a.getName().equals(b.getName()))
                return false;
        }
        return true;
    }

    public Attribute_Group clone(){
        Attribute_Group new_a=new Attribute_Group();
        new_a.attributes= new ArrayList<>(this.attributes);
        return new_a;
    }
}
