package com.xworkz.project.model.service;

import com.xworkz.project.dto.ImageDto;

import java.util.Optional;

public interface ImageService {


    boolean saveImageDetails(ImageDto imageDto);

    Optional<ImageDto> getImageDetailsByUserId(int id);


//service
}
//void saveImageDetails(ImageDownloadDTO imageDTO);
//
//Optional<ImageDownloadDTO> getImageDetailsByUserId(int userId);
//
//void deactivateAllImagesForUser(int userId);

