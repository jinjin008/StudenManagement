package com.example.he.studenmanagement.tools;

/**
 * Created by wjy on 2018/5/21.
 */

public class Bank {

    private int id;
    private int type;
    private int difficult;
    private String title;
    private String idA;//
    private String idB;//
    private String idC;//
    private String idD;
    private String trueOption;



    public Bank(int id,String title,String idA,String idB){
        this.id=id;
        this.title=title;
        this.idA=idA;
        this.idB=idB;
    }
    public Bank(int id,String title,String idA,String idB,String idC,String idD,String trueOption){
        this.id=id;
        this.title=title;
        this.idA=idA;
        this.idB=idB;
        this.idC=idC;
        this.idD=idD;
        this.trueOption=trueOption;
    }
    public Bank(int id,int type,int difficult,String title,String idA,String idB,String idC,String idD,String trueOption){
        this.id=id;
        this.type=type;
        this.difficult=difficult;
        this.title=title;
        this.idA=idA;
        this.idB=idB;
        this.idC=idC;
        this.idD=idD;
        this.trueOption = trueOption;

    }
    public Bank(int type,int difficult,String title,String idA,String idB,String idC,String idD){
        this.type=type;
        this.difficult=difficult;
        this.title=title;
        this.idA=idA;
        this.idB=idB;
        this.idC=idC;
        this.idD=idD;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
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

    public String getTrueOption() {
        return trueOption;
    }

    public void setTrueOption(String trueOption) {
        this.trueOption = trueOption;
    }
}

