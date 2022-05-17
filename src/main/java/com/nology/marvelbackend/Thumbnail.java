package com.nology.marvelbackend;

public class Thumbnail {

    private String path;
    private String extension;

    public Thumbnail(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public Thumbnail() {
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

    @Override
    public String toString() {
        return "{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
