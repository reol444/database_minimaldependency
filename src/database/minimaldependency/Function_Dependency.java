package database.minimaldependency;

import java.util.ArrayList;
import java.util.Comparator;

public class Function_Dependency {
    Attribute_Group left;
    Attribute_Group right;
    public Function_Dependency(){}
    public Function_Dependency(Attribute_Group left,Attribute_Group right){
        this.left=left;
        this.right=right;
    }
    @Override
    public boolean equals(Object o){
        Function_Dependency f=(Function_Dependency) o;
        return this.left.equals(f.left)&&this.right.equals(f.right);
    }
    @Override
    public String toString(){
        this.left.sort();
        this.right.sort();
        String left_s=left.toString();
        left_s+="->";
        left_s+=right.toString();
        return left_s;
    }

    public Attribute_Group get_left_Attribute_Group(){
        return this.left;
    }

    public Attribute_Group get_right_Attribute_Group(){
        return this.right;
    }

    public Attribute_Set get_left_Attribute_Set(){
        Attribute_Set a=new Attribute_Set();
        a.attributes=this.left.clone().attributes;
        return a;
    }

    public Attribute_Set get_right_Attribute_Set(){
        Attribute_Set a=new Attribute_Set();
        a.attributes=this.right.clone().attributes;
        return a;
    }

    @Override
    public Function_Dependency clone(){
        Function_Dependency a=new Function_Dependency(this.left.clone(),this.right.clone());
        return a;
    }

    public ArrayList<Function_Dependency> left_split(){
        ArrayList<Function_Dependency> res= new ArrayList<>();
        if (this.left.attributes.size()>1){
            for (int i=0;i<this.left.attributes.size();i++){
                Attribute_Group c=new Attribute_Group();
                c.append(this.left.attributes.get(i));
                Function_Dependency f=new Function_Dependency(c,this.right);
                res.add(f);
            }
        }
        return res;
    }

    public ArrayList<Function_Dependency> right_split(){
        ArrayList<Function_Dependency> res= new ArrayList<>();
        if (this.right.attributes.size()>1){
            for (int i=0;i<this.right.attributes.size();i++){
                Attribute_Group c=new Attribute_Group();
                c.append(this.right.attributes.get(i));
                Function_Dependency f=new Function_Dependency(this.left,c);
                res.add(f);
            }
        }
        return res;
    }
}

class SortByIndex2 implements Comparator<Function_Dependency>
{
    @Override
    public int compare(Function_Dependency o1, Function_Dependency o2)
    {
        return o1.left.toString().compareTo(o2.left.toString());
    }
}
