package com.example.aaa;

public class Wallet {
    private String Name;
    private String sectionsNames;
    private String id;

    public Wallet(String name, String id) {
        Name = name;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Wallet() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSectionsNames() {
        return sectionsNames;
    }

    public void setSectionsNames(String sectionsNames) {
        this.sectionsNames = sectionsNames;
    }

}
