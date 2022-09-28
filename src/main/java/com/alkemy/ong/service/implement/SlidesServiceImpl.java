package com.alkemy.ong.service.implement;

import com.alkemy.ong.dto.SlidesDTOPublic;
import com.alkemy.ong.entity.Slides;
import com.alkemy.ong.mapper.SlidesMapper;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.SlidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlidesServiceImpl implements SlidesService {
    @Autowired
    private SlidesRepository slidesRepository;
    @Autowired
    private SlidesMapper slidesMapper;

    @Override
    public List<SlidesDTOPublic> getSlidesDTO() {
        List<Slides> entities = slidesRepository.findAll();
        return slidesMapper.slidesEntityList2DTO(entities);
    }
}
