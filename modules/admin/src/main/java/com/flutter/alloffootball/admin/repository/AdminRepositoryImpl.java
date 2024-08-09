package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.admin.wrapper.AdminFieldWrapper;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.enums.region.Region;
import com.flutter.alloffootball.common.jparepository.JpaAdminRepository;
import com.flutter.alloffootball.common.domain.Admin;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.flutter.alloffootball.common.domain.field.QField.*;

@Repository
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository {

    private final JpaAdminRepository jpaAdminRepository;
    private final AdminFieldWrapper adminFieldWrapper;

    private final JPAQueryFactory query;

    @Override
    public Admin findByAccount(String account) {
        return jpaAdminRepository.findByAccount(account)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Page<ResponseSearchField> findAllBySearch(Region region, String word, Pageable pageable) {
        StringExpression keywordExpression = getKeywordExpression(word);

        StringExpression compareWord = getLowerAndReplace(field.title);
        BooleanExpression compareRegion = conditionRegion(region);

        List<Field> fieldList = query.select(field)
            .from(field)
            .where(compareWord.like(keywordExpression), compareRegion)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long totalCount = query.select(field.count())
            .from(field)
            .where(compareWord.like(keywordExpression), compareRegion)
            .fetchFirst();
        if (totalCount == null) totalCount = 0L;

        List<ResponseSearchField> list = fieldList.stream().map(adminFieldWrapper::searchFieldWrap).toList();
        return new PageImpl<>(list, pageable, totalCount);
    }

    private StringExpression getKeywordExpression(String word) {
        return Expressions.asString("%" + word.toLowerCase().replace(" ", "") + "%");
    }

    private StringExpression getLowerAndReplace(StringExpression tuple) {
        return Expressions.stringTemplate("replace(lower({0}), ' ', '')", tuple);
    }

    private BooleanExpression conditionRegion(Region region) {
        // 지역을 선택하지 않았을 경우 모두 조회
        if (region == null) return null;
        return field.address.region.eq(region);
    }
}
