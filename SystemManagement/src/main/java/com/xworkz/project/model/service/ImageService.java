package com.xworkz.project.model.service;

import com.xworkz.project.dto.ImageDto;
import com.xworkz.project.dto.SignUpDto;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ImageService {


    boolean saveImageDetails(ImageDto imageDto);

    Optional<ImageDto> getImageDetailsByUserId(int id);

    void setAuditValues(SignUpDto dto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive);


//service
}
//void saveImageDetails(ImageDownloadDTO imageDTO);
//
//Optional<ImageDownloadDTO> getImageDetailsByUserId(int userId);
//
//void deactivateAllImagesForUser(int userId);

