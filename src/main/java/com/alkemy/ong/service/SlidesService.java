package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesDTOPublic;
import javassist.NotFoundException;

import java.util.List;

public interface SlidesService {
    public List<SlidesDTOPublic> getSlidesDTO();

    public SlidesDTO getSlideDTO(String id) throws NotFoundException;

    SlidesDTO updateSlide(String id, SlidesDTO slideDTO) throws NotFoundException;

    void deleteSlide(String id) throws NotFoundException;
}
