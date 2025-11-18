package com.auto.dealership.service;

import com.auto.dealership.entity.Car;
import com.auto.dealership.entity.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    public void replaceCarPhotos(Car car, List<MultipartFile> files);
    public void replaceEventPhoto(Event event, MultipartFile file);
    public void appendCarPhoto(Car car, List<MultipartFile> files);
}
