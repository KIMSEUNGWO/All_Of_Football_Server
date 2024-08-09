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
        System.out.println("region = " + region);
        System.out.println("word = " + word);

        StringExpression compareWord = getLowerAndReplace(field.title);
        BooleanExpression compareRegion = conditionRegion(region);

        BooleanExpression keywordExpression = getKeywordExpression(word, compareWord);


        System.out.println("pageable.getOffset() = " + pageable.getOffset());
        System.out.println("pageable.getPageSize() = " + pageable.getPageSize());
        List<Field> fieldList = query.select(field)
            .from(field)
            .where(keywordExpression, compareRegion)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();


        Long totalCount = query.select(field.count())
            .from(field)
            .where(keywordExpression, compareRegion)
            .fetchFirst();
        if (totalCount == null) totalCount = 0L;

        List<ResponseSearchField> list = fieldList.stream().map(adminFieldWrapper::searchFieldWrap).toList();
        System.out.println("list = " + list);
        return new PageImpl<>(list, pageable, totalCount);
    }

    private BooleanExpression getKeywordExpression(String word, StringExpression compareWord) {
        if (word == null || word.isEmpty()) return null;
        StringExpression wordExpression = Expressions.asString("%" + word.toLowerCase().replace(" ", "") + "%");
        return compareWord.like(wordExpression);
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
