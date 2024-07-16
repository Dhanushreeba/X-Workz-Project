package com.xworkz.project.model.repo;

import com.xworkz.project.dto.ImageDto;
import com.xworkz.project.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface ImageRepo {

    boolean saveImage(ImageDto imageDto);


    //void saveProfileImage(ProfileImageUploadDto profileImageUploadDto);

    Optional<ImageDto> findByUserId(int id);//id from signUp id

    //to update image table

    void imageUpdateDetails(ImageDto imageDto);

    void SetAllImagesInactiveForUser(int id);

    void SetAllImagesActiveForUser(int id);

//    EditProfileImageDTO findByUserId(String email);

    List<ImageDto> findByexsitsUserId(int id);
}
