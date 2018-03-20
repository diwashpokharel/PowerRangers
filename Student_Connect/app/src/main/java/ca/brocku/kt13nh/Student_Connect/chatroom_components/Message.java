package ca.brocku.kt13nh.Student_Connect.chatroom_components;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String fileName;
    private String fileUrl;
    private String name;
    private String photoUrl;
    private String text;
    private String time;
    public Message(){}
    public Message(String text, String name, String photoUrl, String fileName, String fileUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.time = new SimpleDateFormat("hh:mm").format(new Date());
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return this.fileName;
    }
}
