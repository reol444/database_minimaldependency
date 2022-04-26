package database.minimaldependency;

import java.util.ArrayList;

public class Relation_Schema {
    Function_Dependency_Set fd_set;
    Attribute_Set a_set;
    public Relation_Schema(){
        fd_set=new Function_Dependency_Set();
        a_set=new Attribute_Set();
    }
    @Override
    public String toString(){
        String res="U=";
        res+=this.a_set.toString();
        res+="\n";
        res+="F=";
        res+=this.fd_set.toString();
        return res;
    }

    public Relation_Schema clone(){
        Relation_Schema rs=new Relation_Schema();
        rs.a_set=this.a_set;
        rs.fd_set=this.fd_set;
        return rs;
    }
    public Attribute_Set get_Attribute_Set(){
        return this.a_set;
    }

    public Function_Dependency_Set get_Function_dependency_set(){
        return this.fd_set;
    }

    public void add_attribute(Attribute a){

        this.a_set.append(a);
    }

    public void add_function_dependency(Function_Dependency a){
        this.fd_set.append(a);
    }

    // 在关系模式rs中，求属性集a_set关于F的闭包
    public static Attribute_Set get_closure_of_attribute_set(Function_Dependency_Set fd_set,Attribute_Set u_set,Attribute_Set a_set){
        ArrayList<Function_Dependency> fds= new ArrayList<>(fd_set.get_Function_dependency_set());
        if (a_set.is_subset(u_set))
        {
            Attribute_Set a=new Attribute_Set();
            a.copy(a_set);
            Attribute_Set b;
            while(true)
            {
                Attribute_Set t= new Attribute_Set();
                ArrayList<Function_Dependency>dels=new ArrayList<Function_Dependency>();
                for (Function_Dependency f : fds) {
                    Attribute_Set las = f.get_left_Attribute_Set();
                    if (las.is_subset(a)) {
                        t.appendAll(f.get_right_Attribute_Group());
                        dels.add(f);
                    }
                }
                for (Function_Dependency del : dels) {
                    fds.remove(del);
                }
                b=a.clone();
                a=a.union(t);
                if (a.attributes.toString().equals(b.attributes.toString())||a.attributes.toString().equals(u_set.attributes.toString())){
                    break;
                }
            }
            a.sort();
            return a;
        }
        else{
            return a_set;
        }
    }

    // 求关系模式中F的最小覆盖
    public static Function_Dependency_Set get_canonical_cover(Relation_Schema rs){
        Relation_Schema new_rs=rs.clone();
        new_rs.fd_set.right_split();
        Function_Dependency_Set fd_set_c=new_rs.fd_set.clone();
        int c=new_rs.fd_set.count();
        for(int i=0;i<c;i++){
            Function_Dependency f=new_rs.fd_set.get_item(i);
            fd_set_c.remove(f);
            Attribute_Set cat=get_closure_of_attribute_set(fd_set_c,new_rs.a_set,f.get_left_Attribute_Set());
            Attribute_Set f_right=f.get_right_Attribute_Set();
            if (!f_right.is_subset(cat)){
                fd_set_c.append(f);
            }
        }
        new_rs.fd_set=fd_set_c;
        new_rs.fd_set.left_split();
        c=new_rs.fd_set.count();
        for(int i=0;i<c;i++){
            Function_Dependency f=new_rs.fd_set.get_item(i);
            fd_set_c.remove(f);
            Attribute_Set cat=get_closure_of_attribute_set(fd_set_c,new_rs.a_set,f.get_left_Attribute_Set());
            Attribute_Set f_right=f.get_right_Attribute_Set();
            if (!f_right.is_subset(cat)){
                fd_set_c.append(f);
            }
        }
        return fd_set_c;
    }
}
