package com.zerophi.gestionvie.models;

public class nocificationmodel {
String   notic_titre , notic_description ,publish_date;
int notic_id,parsing,dept;

    public int getDept() {
        return dept;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public void setDept(int dept) {
        this.dept = dept;


    }

    public int getParsing() {

        return parsing;
    }



    public String getNotic_titre() {

        return notic_titre;
    }

    public void setNotic_titre(String notic_titre) {
        this.notic_titre = notic_titre;
    }

    public String getNotic_description() {
        return notic_description;
    }

    public void setNotic_description(String notic_description) {
        this.notic_description = notic_description;
    }



    public int getNotic_id() {
        return notic_id;
    }

    public void setNotic_id(int notic_id) {
        this.notic_id = notic_id;
    }
}
