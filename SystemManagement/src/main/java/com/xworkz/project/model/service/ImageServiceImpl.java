package com.xworkz.project.model.service;

import com.xworkz.project.dto.ImageDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.ImageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepo imageRepo;

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    ImageServiceImpl(){
        log.info("created constr for ImageServiceImpl");
    }

    @Override
    public boolean saveImageDetails(ImageDto imageDto) {

        log.info("saveImageDetails method running in ImageUploadServiceImpl..");

        boolean imageData=  imageRepo.saveImage(imageDto);

        if(imageData)
        {
            log.info("imageUploadRepo in ImageUploadServiceImpl");
        }

        else
        {
            log.info("imageUploadRepo not in ImageUploadServiceImpl");
        }


        return true;
    }

    @Override
    public void setAuditValues(SignUpDto dto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive)
    {
        dto.setCreatedBy(createdBy);
        dto.setCreatedOn(createdOn);
        dto.setUpdatedBy(updatedBy);
        dto.setUpdatedOn(updatedOn);
        dto.setActive(isActive);
    }

    @Override
    public Optional<ImageDto> getImageDetailsByUserId(int id) {
        return imageRepo.findByUserId(id);
    }
}
