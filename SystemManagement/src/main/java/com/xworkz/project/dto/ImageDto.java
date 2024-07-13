package com.xworkz.project.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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

    @Column(name ="image_type")
    private String imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_user_id", referencedColumnName = "id")
    private SignUpDto user;

    @Column(name="createdBy")
    private String createdBy;

    @Column(name="createdOn")
    private LocalDateTime createdOn;

    @Column(name ="updatedBy")
    private String updatedBy;

    @Column(name="updatedOn")
    private LocalDateTime updatedOn;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SignUpDto userId;

   // private Integer id;

    @Override
    public String toString() {
        return "ImageDto{" +
                "imageId=" + imageId +
                ", imageSize=" + imageSize +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", user=" + user +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", imagePath='" + imagePath + '\'' +
                ", userId=" + userId +
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

    public SignUpDto getUser() {
        return user;
    }

    public void setUser(SignUpDto user) {
        this.user = user;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public SignUpDto getUserId() {
        return userId;
    }

    public void setUserId(SignUpDto userId) {
        this.userId = userId;
    }

}
