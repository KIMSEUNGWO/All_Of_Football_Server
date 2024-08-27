package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.component.DataValidator;
import com.flutter.alloffootball.common.component.file.FileService;
import com.flutter.alloffootball.common.component.file.FileType;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.dto.InvalidData;
import com.flutter.alloffootball.common.exception.InvalidDataException;
import com.flutter.alloffootball.common.exception.UserException;
import com.flutter.alloffootball.dto.user.RequestEditUser;
import com.flutter.alloffootball.dto.user.ResponseUserProfile;
import com.flutter.alloffootball.repository.UserRepository;
import com.flutter.alloffootball.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final FileService fileService;
    private final UserRepository userRepository;
    private final UserWrapper userWrapper;

    private final DataValidator dataValidator;

    @Override
    public ResponseUserProfile getUserProfile(Long userId) {
        User user = userRepository.findById(userId);
        return userWrapper.userProfileWrap(user);
    }

    @Override
    public boolean distinctNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public void editUser(Long userId, RequestEditUser editUser) {

        dataValidator.validNickname(editUser.getNickname());

        List<Consumer<User>> consumers = new ArrayList<>();

        if (editUser.getNickname() != null && !editUser.getNickname().isEmpty()) {
            boolean exists = distinctNickname(editUser.getNickname());
            if (exists) throw new InvalidDataException(new InvalidData("nickname", "이미 사용중인 닉네임입니다."));
            consumers.add(user -> user.setNickname(editUser.getNickname()));
        }

        User user = userRepository.findById(userId);
        consumers.forEach(consumer -> consumer.accept(user));

        fileService.editImage(editUser.getImage(), user.getProfile(), FileType.PROFILE);
    }
}
