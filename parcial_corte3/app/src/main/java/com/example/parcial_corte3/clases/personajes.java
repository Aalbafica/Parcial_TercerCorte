package com.example.parcial_corte3.clases;

public class personajes {
    private int id;
    private String name;
    private String description;
    private String path; // imagen ruta
    private String extension; // tipo de imagen (jpg, png, etc..)

    public personajes(int id, String name, String description, String path, String extension) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
