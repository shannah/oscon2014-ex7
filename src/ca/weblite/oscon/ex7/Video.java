/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.weblite.oscon.ex7;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author shannah
 */
public class Video implements Externalizable {
    private String id;
    private String title;
    private String description;
    private String category;
    private String uploader;
    private int duration;
    private String aspectRatio;
    private String thumbnailUrl;
    private String videoUrl;
    private Date uploadDate = new Date();
    private Date updatedDate = new Date();

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the uploader
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * @param uploader the uploader to set
     */
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the aspectRatio
     */
    public String getAspectRatio() {
        return aspectRatio;
    }

    /**
     * @param aspectRatio the aspectRatio to set
     */
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * @return the thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * @param thumbnailUrl the thumbnailUrl to set
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * @return the videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl the videoUrl to set
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return the uploadDate
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getVersion() {
        return 1;
    }

    public void externalize(DataOutputStream out) throws IOException {
        Util.writeUTF(id, out);
        Util.writeUTF(title, out);
        Util.writeUTF(description, out);
        Util.writeUTF(category, out);
        Util.writeUTF(uploader, out);
        out.writeInt(duration);
        Util.writeUTF(aspectRatio, out);
        Util.writeUTF(thumbnailUrl, out);
        Util.writeUTF(videoUrl, out);
        out.writeLong(uploadDate.getTime());
        out.writeLong(updatedDate.getTime());
    
    }

    public void internalize(int version, DataInputStream in) throws IOException {
        id = Util.readUTF(in);
        title = Util.readUTF(in);
        description = Util.readUTF(in);
        category = Util.readUTF(in);
        uploader = Util.readUTF(in);
        duration = in.readInt();
        aspectRatio = Util.readUTF(in);
        thumbnailUrl = Util.readUTF(in);
        videoUrl = Util.readUTF(in);
        uploadDate = new Date(in.readLong());
        updatedDate = new Date(in.readLong());
    }

    public String getObjectId() {
        return "Video";
    }
}
