package com.xworkz.project.dto;

import com.xworkz.project.constant.Status;
import lombok.Data;
import org.apache.logging.log4j.status.StatusData;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name="image_upload")
public class ImageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Integer imageId;

    @Column(name="image_size")
    private Long imageSize;

    @Column(name="image_name")
    private String imageName;

    @Column(name="image_type")
    private String imageType;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private SignUpDto user;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="user_id")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

   // private Integer id;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "imageId=" + imageId +
                ", imageSize=" + imageSize +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", userId=" + userId +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

//    public SignUpDto getUser() {
//        return user;
//    }
//
//    public void setUser(SignUpDto user) {
//        this.user = user;
//    }



    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

//    public SignUpDto getUserId() {
//        return userId;
//    }
//
//    public void setUserId(SignUpDto userId) {
//        this.userId = userId;
//    }

}
