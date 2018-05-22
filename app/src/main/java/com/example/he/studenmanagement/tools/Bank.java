package com.example.he.studenmanagement.tools;

/**
 * Created by wjy on 2018/5/21.
 */

public class Bank {
    private String id;
    private String title;
    private String idA;//
    private String idB;//
    private String idC;//
    private String idD;
    private String trueOption;

    public Bank(String id,String title,String idA,String idB,String idC,String idD,String trueOptions){
        this.id=id;
        this.title=title;
        this.idA=idA;
        this.idB=idB;
        this.idC=idC;
        this.idD=idD;
        trueOption = trueOptions;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdA() {
        return idA;
    }

    public void setIdA(String idA) {
        this.idA = idA;
    }

    public String getIdB() {
        return idB;
    }

    public void setIdB(String idB) {
        this.idB = idB;
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }

    public String getIdD() {
        return idD;
    }

    public void setIdD(String idD) {
        this.idD = idD;
    }

    public String getTrueOPtion() {
        return trueOption;
    }

    public void setTrueOPtion(String trueOPtion) {
        this.trueOption = trueOPtion;
    }


}

