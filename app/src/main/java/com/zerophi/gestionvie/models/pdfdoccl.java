package com.zerophi.gestionvie.models;

public class pdfdoccl {

    int document_id, module_id ,enseignantuser_id ;
    String nameautheur;
    String titre;

    public String getNameautheur() {
        return nameautheur;
    }

    public void setNameautheur(String nameautheur) {
        this.nameautheur = nameautheur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    String pdfurl;
    String pdfurlicon ;

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getEnseignantuser_id() {
        return enseignantuser_id;
    }

    public void setEnseignantuser_id(int enseignantuser_id) {
        this.enseignantuser_id = enseignantuser_id;
    }



    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public String getPdfurlicon() {
        return pdfurlicon;
    }

    public void setPdfurlicon(String pdfurlicon) {
        this.pdfurlicon = pdfurlicon;
    }
}
