package com.example.he.studenmanagement.tools;

/**
 * Created by wjy on 2018/5/31.
 */

public class Paper  {

    private String id;
    private String name;

    public Paper(String id,String name){
        this.id=id;
        this.name=name;
    }
    public Paper(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id= id;
    }



    public String getName() {
        return name;
    }

    public void setPaperName(String paperName) {
        this.name = paperName;
    }
}