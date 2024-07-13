package com.xworkz.project.model.repo;

import com.xworkz.project.dto.ImageDto;

import java.util.Optional;

public interface ImageRepo {

    boolean saveImage(ImageDto imageDto);


    //void saveProfileImage(ProfileImageUploadDto profileImageUploadDto);

    Optional<ImageDto> findByUserId(int id);  //id from signUp id
}
