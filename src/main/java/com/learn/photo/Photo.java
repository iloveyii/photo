package com.learn.photo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class Photo {
    private String id;

    @NotEmpty(message = "File name cannot be empty")
    @NotNull
    private String fileName;

    @JsonIgnore
    private byte[] data;

    public Photo() {
    }

    public Photo(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
