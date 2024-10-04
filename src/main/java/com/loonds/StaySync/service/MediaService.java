package com.loonds.StaySync.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {
    public String upload(String userId, MultipartFile file) throws IOException;

}
