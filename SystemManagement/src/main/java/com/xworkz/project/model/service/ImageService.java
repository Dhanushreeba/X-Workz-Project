package com.xworkz.project.model.service;

import com.xworkz.project.dto.ImageDto;
import com.xworkz.project.dto.SignUpDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ImageService {


    boolean saveImageDetails(ImageDto imageDto);

    Optional<ImageDto> getImageDetailsByUserId(int id);


    /// New method for updating image details
    void updateImageDetails(ImageDto imageDto); // New method


    //  void setAudit(EditProfileImageDTO editProfileImageDTO , String updatedBy, LocalDateTime updatedOn);
    void setAllImagesInactiveForUser(int userId);  // New method declaration

    void SetAllImagesActiveForUser(int userId);


    void setAuditValues(SignUpDto dto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive);



    List<ImageDto> findByexsitsUserId(int userId);

//service
}
//void saveImageDetails(ImageDownloadDTO imageDTO);
//
//Optional<ImageDownloadDTO> getImageDetailsByUserId(int userId);
//
//void deactivateAllImagesForUser(int userId);

