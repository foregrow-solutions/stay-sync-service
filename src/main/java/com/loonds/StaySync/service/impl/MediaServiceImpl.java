package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.service.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
//    private final UserRepository userRepository;

    @Override
    @Transactional
    public String upload(String userId, MultipartFile file) throws IOException {
//        Optional<User> user1 = userRepository.findById(userId).map(user -> {
//            try {
//                user.setProfilePic(ImageUtil.compressImage(file.getBytes()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            return user;
//        });
        return "Upload Image";
    }

}
