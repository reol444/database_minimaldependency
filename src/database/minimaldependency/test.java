package database.minimaldependency;

public class test {
    public static void main(String[] args){
        mytest mt=new mytest();
        mt.test1();
        mt.test2();
        mt.test3();
        mt.test4();
    }
}

class mytest{
    public void test1(){
        Relation_Schema rs = new Relation_Schema();
        // A, B, C, D, E
        rs.add_attribute(new Attribute("A"));
        rs.add_attribute(new Attribute("B"));
        rs.add_attribute(new Attribute("C"));
        rs.add_attribute(new Attribute("D"));
        rs.add_attribute(new Attribute("E"));
        // AB→C, B→D, C→E, EC→B, AC→B
        Attribute_Group gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        gl.append(new Attribute("B"));
        Attribute_Group gr = new Attribute_Group();
        gr.append(new Attribute("C"));
        Function_Dependency f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // B->D
        gl =new Attribute_Group();
        gl.append(new Attribute("B"));
        gr = new Attribute_Group();
        gr.append(new Attribute("D"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // C->E
        gl =new Attribute_Group();
        gl.append(new Attribute("C"));
        gr = new Attribute_Group();
        gr.append(new Attribute("E"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //  EC->B
        gl =new Attribute_Group();
        gl.append(new Attribute("E"));
        gl.append(new Attribute("C"));
        gr = new Attribute_Group();
        gr.append(new Attribute("B"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // AC->B
        gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        gl.append(new Attribute("C"));
        gr = new Attribute_Group();
        gr.append(new Attribute("B"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //输出U和F
        System.out.println(rs);
        //输出(AB)F+
        Attribute_Set a =new Attribute_Set();
        a.append(new Attribute("A"));
        a.append(new Attribute("B"));
        Attribute_Set res=Relation_Schema.get_closure_of_attribute_set(rs.fd_set,rs.a_set, a);
        System.out.println("属性集{A,B}关于函数依赖集F的闭包为为："+res);
    }
    public void test2(){
        Relation_Schema rs = new Relation_Schema();
        // A, B, C, G ,H,I
        rs.add_attribute(new Attribute("A"));
        rs.add_attribute(new Attribute("B"));
        rs.add_attribute(new Attribute("C"));
        rs.add_attribute(new Attribute("G"));
        rs.add_attribute(new Attribute("H"));
        rs.add_attribute(new Attribute("I"));
        // A→C, A→B, C→E, EC→B, AC→B
        Attribute_Group gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        Attribute_Group gr = new Attribute_Group();
        gr.append(new Attribute("C"));
        Function_Dependency f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // A->B
        gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        gr = new Attribute_Group();
        gr.append(new Attribute("B"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // CG->H
        gl =new Attribute_Group();
        gl.append(new Attribute("C"));
        gl.append(new Attribute("G"));
        gr = new Attribute_Group();
        gr.append(new Attribute("H"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //  GC->I
        gl =new Attribute_Group();
        gl.append(new Attribute("G"));
        gl.append(new Attribute("C"));
        gr = new Attribute_Group();
        gr.append(new Attribute("I"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // B->H
        gl =new Attribute_Group();
        gl.append(new Attribute("B"));
        gr = new Attribute_Group();
        gr.append(new Attribute("H"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //输出U和F
        System.out.println(rs);
        // 输出(AG)F+
        Attribute_Set a =new Attribute_Set();
        a.append(new Attribute("A"));
        a.append(new Attribute("G"));
        Attribute_Set res=Relation_Schema.get_closure_of_attribute_set(rs.fd_set,rs.a_set, a);
        System.out.println("属性集{A,G}关于函数依赖集F的闭包为为："+res);
    }
    public void test3(){
        Relation_Schema rs = new Relation_Schema();
        // A, B, C
        rs.add_attribute(new Attribute("A"));
        rs.add_attribute(new Attribute("B"));
        rs.add_attribute(new Attribute("C"));
        // A→B, B→A, B→C, A→C, C→A
        Attribute_Group gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        Attribute_Group gr = new Attribute_Group();
        gr.append(new Attribute("B"));
        Function_Dependency f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // B->A
        gl =new Attribute_Group();
        gl.append(new Attribute("B"));
        gr = new Attribute_Group();
        gr.append(new Attribute("A"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // B->C
        gl =new Attribute_Group();
        gl.append(new Attribute("B"));
        gr = new Attribute_Group();
        gr.append(new Attribute("C"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //  A->C
        gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        gr = new Attribute_Group();
        gr.append(new Attribute("C"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // C->A
        gl =new Attribute_Group();
        gl.append(new Attribute("C"));
        gr = new Attribute_Group();
        gr.append(new Attribute("A"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //输出U和F
        System.out.println(rs);
        // 最小依赖
        System.out.println("最小依赖集为："+ Relation_Schema.get_canonical_cover(rs));
    }

    public void test4(){
        Relation_Schema rs = new Relation_Schema();
        // A, B, C
        rs.add_attribute(new Attribute("A"));
        rs.add_attribute(new Attribute("B"));
        rs.add_attribute(new Attribute("C"));
        // A→BC, B→C, B→C, A→B, AB→C
        Attribute_Group gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        Attribute_Group gr = new Attribute_Group();
        gr.append(new Attribute("B"));
        gr.append(new Attribute("C"));
        Function_Dependency f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // B->C
        gl =new Attribute_Group();
        gl.append(new Attribute("B"));
        gr = new Attribute_Group();
        gr.append(new Attribute("C"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        // A->B
        gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        gr = new Attribute_Group();
        gr.append(new Attribute("B"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //  AB->C
        gl =new Attribute_Group();
        gl.append(new Attribute("A"));
        gl.append(new Attribute("B"));
        gr = new Attribute_Group();
        gr.append(new Attribute("C"));
        f = new Function_Dependency(gl, gr);
        rs.add_function_dependency(f);
        //输出U和F
        System.out.println(rs);
        // 最小依赖
        System.out.println("最小依赖集为："+ Relation_Schema.get_canonical_cover(rs));
    }
}
