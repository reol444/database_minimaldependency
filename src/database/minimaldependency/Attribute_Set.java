package database.minimaldependency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Attribute_Set extends Attribute_List{
    public Attribute_Set union(Attribute_Set a)
    {
        Set<Attribute> a_set = new HashSet<>(a.attributes);
        Set<Attribute> this_set = new HashSet<>(this.attributes);
        Set<Attribute> res = new HashSet<>();
        res.addAll(this_set);
        res.addAll(a_set);
        ArrayList<Attribute> union_list = new ArrayList<>(res);
        Attribute_Set b=new Attribute_Set();
        b.attributes=union_list;
        return b;
    }

    public Attribute_Set difference(Attribute_Set a)
    {
        Set<Attribute> a_set = new HashSet<>(a.attributes);
        Set<Attribute> this_set = new HashSet<>(this.attributes);
        Set<Attribute> res = new HashSet<>();
        res.addAll(this_set);
        res.removeAll(a_set);
        ArrayList<Attribute> difference_list = new ArrayList<>(res);
        Attribute_Set b=new Attribute_Set();
        b.attributes=difference_list;
        return b;
    }

    public Attribute_Set intersection(Attribute_Set a)
    {
        Set<Attribute> a_set = new HashSet<>(a.attributes);
        Set<Attribute> this_set = new HashSet<>(this.attributes);
        Set<Attribute> res = new HashSet<>();
        res.addAll(this_set);
        res.retainAll(a_set);
        ArrayList<Attribute> difference_list = new ArrayList<>(res);
        Attribute_Set b=new Attribute_Set();
        b.attributes=difference_list;
        return b;
    }

    public boolean is_subset(Attribute_Set a){
        Set<Attribute> a_set = new HashSet<>(a.attributes);
        Set<Attribute> this_set = new HashSet<>(this.attributes);
        return a_set.containsAll(this_set);
    }

    public boolean is_superset(Attribute_Set a)
    {
        Set<Attribute> a_set = new HashSet<>(a.attributes);
        Set<Attribute> this_set = new HashSet<>(this.attributes);
        return this_set.containsAll(a_set);
    }
    public boolean is_disjoint(Attribute_Set a){
        Set<Attribute> a_set = new HashSet<>(a.attributes);
        Set<Attribute> this_set = new HashSet<>(this.attributes);
        Set<Attribute> res = new HashSet<>();
        res.addAll(this_set);
        res.retainAll(a_set);
        if (res.size()>0){
            return true;
        }
        return false;
    }

    public Attribute_Set clone(){
        Attribute_Set new_a=new Attribute_Set();
        new_a.attributes=new ArrayList<Attribute>(this.attributes);
        return new_a;
    }
}
