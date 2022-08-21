package com.example.grievanceapplication;

public class Students {
    String Name;
    String Title;
    String Description;
    String Branch;

    public Students() {
    }

    public Students(String name, String title, String description, String branch) {
        Name = name;
        Title = title;
        Description = description;
        Branch = branch;
    }

    public String getName() {
        return Name;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getBranch() {
        return Branch;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }
}
