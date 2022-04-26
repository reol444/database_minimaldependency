package database.minimaldependency;

import java.util.ArrayList;
import java.util.Arrays;

public class Function_Dependency_Set {
    ArrayList<Function_Dependency> fds;
    // 构造函数初始化
    public Function_Dependency_Set(){
        fds= new ArrayList<>();
    }

    // 获取指定位置的函数依赖
    public Function_Dependency get_item(int index){
        return fds.get(index);
    }

    // 将函数依赖集转换为字符串
    public String toString(){
        String[] res =new String[fds.size()];
        for (int i=0;i<fds.size();i++){
            res[i]=fds.get(i).toString();
        }
        Arrays.sort(res);
        String f_res="{";
        for (int j=0;j<fds.size();j++){
            f_res+=(res[j]+",");
        }
        f_res=f_res.substring(0,f_res.length()-1);
        f_res+="}";
        return f_res;

    }

    // 判断两个函数依赖集是否相同
    public boolean equals(Object o){
        Function_Dependency_Set f=(Function_Dependency_Set) o;
        return f.toString().equals(this.toString());
    }
    // 增加一个函数依赖
    public void append(Function_Dependency fd){
        fds.add(fd);
    }
    // 删除一个函数依赖
    public void remove(Function_Dependency fd){
        this.fds.remove(fd);
    }

    // 获取索引
    public int index(Function_Dependency fd){
        return this.fds.indexOf(fd);
    }

    // 获取函数依赖集
    public ArrayList<Function_Dependency> get_Function_dependency_set(){
        return this.fds;
    }

    // 获取依赖集的长度
    public int count(){
        return this.fds.size();
    }

    @Override
    public Function_Dependency_Set clone() {
        Function_Dependency_Set a= new Function_Dependency_Set();
        a.fds= new ArrayList<>(this.fds);
        return a;
    }

    // 将函数依赖集的每个右半部分分裂开
    public void right_split()
    {
        ArrayList<Function_Dependency> del_list= new ArrayList<>();
        ArrayList<Function_Dependency> add_list=new ArrayList<>();
        for (Function_Dependency f : fds) {
            ArrayList<Function_Dependency> r = f.right_split();
            if (r.size() != 0) {
                del_list.add(f);
                add_list.addAll(r);
            }
        }
        for (Function_Dependency function_dependency : del_list) {
            this.fds.remove(function_dependency);
        }
        this.fds.addAll(add_list);
        this.fds.sort(new SortByIndex2());
    }


    // 将函数依赖集的左半部分裂开
    public void left_split()
    {
        ArrayList<Function_Dependency> del_list=new ArrayList<>();
        ArrayList<Function_Dependency> add_list=new ArrayList<>();
        for (Function_Dependency f : fds) {
            ArrayList<Function_Dependency> r = f.left_split();
            if (r.size() != 0) {
                del_list.add(f);
                add_list.addAll(r);
            }
        }
        for (Function_Dependency function_dependency : del_list) {
            this.fds.remove(function_dependency);
        }
        this.fds.addAll(add_list);
        this.fds.sort(new SortByIndex2());
    }
}
