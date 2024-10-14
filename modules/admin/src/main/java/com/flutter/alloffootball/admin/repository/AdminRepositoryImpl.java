package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.field.RequestSearchField;
import com.flutter.alloffootball.admin.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.admin.dto.field.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.match.ResponseSearchMatch;
import com.flutter.alloffootball.admin.dto.user.RequestSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseSearchUser;
import com.flutter.alloffootball.admin.wrapper.AdminFieldWrapper;
import com.flutter.alloffootball.admin.wrapper.AdminMatchWrapper;
import com.flutter.alloffootball.admin.wrapper.AdminUserWrapper;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.OrderStatus;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import com.flutter.alloffootball.common.jparepository.JpaAdminRepository;
import com.flutter.alloffootball.common.domain.Admin;
import com.flutter.alloffootball.common.jparepository.JpaOrderRepository;
import com.flutter.alloffootball.common.jparepository.JpaUserRepository;
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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.flutter.alloffootball.common.domain.field.QField.*;
import static com.flutter.alloffootball.common.domain.match.QMatch.match;
import static com.flutter.alloffootball.common.domain.user.QUser.*;

@Repository
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository {

    private final JpaAdminRepository jpaAdminRepository;
    private final JpaOrderRepository jpaOrderRepository;
    private final JpaUserRepository jpaUserRepository;

    private final AdminFieldWrapper adminFieldWrapper;
    private final AdminMatchWrapper adminMatchWrapper;
    private final AdminUserWrapper adminUserWrapper;

    private final JPAQueryFactory query;

    @Override
    public Admin findByAccount(String account) {
        return jpaAdminRepository.findByAccount(account)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable) {
        System.out.println("data = " + data);

        BooleanExpression compareRegion = conditionRegion(data.getRegion());

        BooleanExpression keywordExpression = getKeywordExpression(data.getWord(), getLowerAndReplace(field.title));

        List<Field> fieldList = query.select(field)
            .from(field)
            .where(keywordExpression, compareRegion)
            .orderBy(field.id.desc())
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

    @Override
    public Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable) {
        System.out.println("data = " + data);

        BooleanExpression compareRegion = conditionRegion(data.getRegion());
        BooleanExpression compareSex = conditionSex(data.getSex());
        BooleanExpression compareStatus = conditionMatchStatus(data.getState());

        BooleanExpression keywordExpression = getKeywordExpression(data.getWord(), getLowerAndReplace(field.title));

        List<Match> matchList = query.select(match)
            .from(match)
            .where(keywordExpression, compareRegion, compareSex, compareStatus,
                match.matchDate.between(LocalDateTime.of(data.getStartDate(), LocalTime.MIN), LocalDateTime.of(data.getEndDate(),LocalTime.MAX)))
            .orderBy(match.matchDate.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long totalCount = query.select(match.count())
            .from(match)
            .where(keywordExpression, compareRegion, compareSex, compareStatus,
                match.matchDate.between(LocalDateTime.of(data.getStartDate(), LocalTime.MIN), LocalDateTime.of(data.getEndDate(),LocalTime.MAX)))
            .fetchFirst();
        if (totalCount == null) totalCount = 0L;


        List<ResponseSearchMatch> list = matchList.stream().map(match -> {
            long countPerson = jpaOrderRepository.countByMatchAndOrderStatus(match, OrderStatus.USE);
            return adminMatchWrapper.searchMatchWrap(match, countPerson);
        }).toList();

        System.out.println("list = " + list);
        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public Page<ResponseSearchUser> findAllBySearchUser(RequestSearchUser data, Pageable pageable) {
        return jpaUserRepository.findAllByNicknameContainingIgnoreCaseOrderByIdDesc(data.getWord(), pageable)
            .map(adminUserWrapper::searchUserWrap);
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
    private BooleanExpression conditionSex(SexType sexType) {
        // 지역을 선택하지 않았을 경우 모두 조회
        if (sexType == null) return null;
        return match.matchSex.eq(sexType);
    }
    private BooleanExpression conditionMatchStatus(MatchStatus matchStatus) {
        // 지역을 선택하지 않았을 경우 모두 조회
        if (matchStatus == null) return null;
        return match.matchStatus.eq(matchStatus);
    }
}
