package ca.brocku.kt13nh.Student_Connect.chatroom_components;

/**
 * Created by pc-user on 3/11/2018.
 */

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private String text;
    private String name;
    private String photoUrl;
    private String fileName;
    private String fileUrl;
    private String time;

    /**
     * Default constructor
     */
    public Message(){

    }

    /**
     * Constructor that is primarily used to create Message objects
     * @param text
     * @param name
     * @param photoUrl
     * @param fileName
     * @param fileUrl
     */
    public Message(String text, String name, String photoUrl, String fileName, String fileUrl ) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.fileName = fileName;
        this.fileUrl = fileUrl;

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        this.time = dateFormat.format(new Date());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime(){ return time; }

    public void setTime(String time) { this.time = time; }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }
}