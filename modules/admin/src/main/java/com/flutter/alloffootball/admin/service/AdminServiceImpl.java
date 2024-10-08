package com.flutter.alloffootball.admin.service;

import com.flutter.alloffootball.admin.dto.*;
import com.flutter.alloffootball.admin.dto.field.*;
import com.flutter.alloffootball.admin.dto.match.ResponseViewMatch;
import com.flutter.alloffootball.admin.dto.match.ResponseViewUser;
import com.flutter.alloffootball.admin.repository.AdminRepository;
import com.flutter.alloffootball.admin.wrapper.AdminFieldWrapper;
import com.flutter.alloffootball.admin.wrapper.AdminMatchWrapper;
import com.flutter.alloffootball.common.component.file.FileService;
import com.flutter.alloffootball.common.domain.field.Address;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.domain.field.FieldData;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.exception.FieldError;
import com.flutter.alloffootball.common.exception.FieldException;
import com.flutter.alloffootball.common.exception.MatchError;
import com.flutter.alloffootball.common.exception.MatchException;
import com.flutter.alloffootball.common.jparepository.JpaFieldRepository;
import com.flutter.alloffootball.common.jparepository.JpaMatchRepository;
import com.flutter.alloffootball.common.jparepository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final FileService fileService;
    private final JpaFieldRepository jpaFieldRepository;
    private final JpaOrderRepository jpaOrderRepository;
    private final AdminRepository adminRepository;

    private final AdminFieldWrapper adminFieldWrapper;
    private final JpaMatchRepository jpaMatchRepository;
    private final AdminMatchWrapper adminMatchWrapper;

    @Transactional(readOnly = true)
    @Override
    public Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable) {
        return adminRepository.findAllBySearchField(data, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable) {
        return adminRepository.findAllBySearchMatch(data, pageable);
    }

    @Override
    public void saveField(RequestSaveFieldForm form) {
        System.out.println("form = " + form);
        Field saveField = Field.builder()
            .title(form.getTitle())
            .description(form.getDescription())
            .address(Address.builder()
                .address(form.getAddress())
                .region(form.getRegion())
                .link(form.getLink())
                .build())
            .fieldData(FieldData.builder()
                .size(form.getSize())
                .parking(form.getParking())
                .shower(form.getShower())
                .toilet(form.getToilet())
                .build())
            .build();

        jpaFieldRepository.save(saveField);

        fileService.saveFieldImages(form.getImages(), saveField);

    }

    @Override
    public ResponseViewField findByIdViewField(long fieldId) {
        Field field = fieldFindById(fieldId);
        return adminFieldWrapper.viewFieldWrap(field);
    }

    @Override
    public ResponseViewMatch findByIdViewMatch(long matchId) {
        Match match = matchFindById(matchId);
        List<ResponseViewUser> userList = jpaOrderRepository.findAllByMatchAndCancelDateIsNull(match)
            .stream().map(adminMatchWrapper::viewUserWrap)
            .sorted(Comparator.comparingLong(ResponseViewUser::getUserId))
            .toList();
        return adminMatchWrapper.viewMatchWrap(match, userList);
    }

    @Override
    public ResponseEditField getEditFieldForm(Long fieldId) {
        Field field = fieldFindById(fieldId);
        return new ResponseEditField(field);
    }

    @Override
    public void patchEditField(Long fieldId, ResponseEditField editField) {
        Field field = fieldFindById(fieldId);

        String[] deleteStoreImages = editField.getDeleteImages().split(",");
        fileService.deleteImages(deleteStoreImages);
        fileService.saveFieldImages(editField.getImages(), field);

        field.setTitle(editField.getTitle());
        field.setDescription(editField.getDescription());

        Address address = field.getAddress();
        address.setLink(editField.getLink());
        address.setRegion(editField.getRegion());
        address.setAddress(editField.getAddress());
        System.out.println("address = " + address);

        FieldData fieldData = field.getFieldData();
        fieldData.setParking(editField.getParking());
        fieldData.setShower(editField.getShower());
        fieldData.setToilet(editField.getToilet());
        fieldData.setSize(editField.getSize());

    }

    Field fieldFindById(long fieldId) {
        return jpaFieldRepository.findById(fieldId)
            .orElseThrow(() -> new FieldException(FieldError.FIELD_NOT_EXISTS));
    }

    Match matchFindById(long matchId) {
        return jpaMatchRepository.findById(matchId)
            .orElseThrow(() -> new MatchException(MatchError.MATCH_NOT_EXISTS));
    }
}
