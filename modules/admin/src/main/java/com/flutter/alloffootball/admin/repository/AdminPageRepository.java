package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.field.RequestSearchField;
import com.flutter.alloffootball.admin.dto.field.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.admin.dto.match.ResponseSearchMatch;
import com.flutter.alloffootball.admin.dto.user.RequestSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseUserOrder;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.OrderStatus;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import com.flutter.alloffootball.common.jparepository.JpaOrderRepository;
import com.flutter.alloffootball.common.jparepository.JpaUserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.thymeleaf.standard.expression.SimpleExpression;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.flutter.alloffootball.common.domain.field.QField.field;
import static com.flutter.alloffootball.common.domain.match.QMatch.match;

@Repository
@RequiredArgsConstructor
public class AdminPageRepository {

    private final JPAQueryFactory query;
    private final JpaOrderRepository jpaOrderRepository;
    private final JpaUserRepository jpaUserRepository;

    public Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable) {
        BooleanExpression compareRegion = conditionRegion(data.getRegion());

        BooleanExpression keywordExpression = getKeywordExpression(data.getWord(), getLowerAndReplace(field.title));

        BooleanExpression[] totalExpressions = new BooleanExpression[] {keywordExpression, compareRegion};

        List<Field> fieldList = query.select(field)
            .from(field)
            .where(totalExpressions)
            .orderBy(field.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();


        Long totalCount = getTotalCount(field, totalExpressions);

        List<ResponseSearchField> list = fieldList.stream().map(ResponseSearchField::new).toList();
        System.out.println("list = " + list);
        return new PageImpl<>(list, pageable, totalCount);
    }

    public Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable) {
        BooleanExpression compareRegion = conditionRegion(data.getRegion());
        BooleanExpression compareSex = conditionSex(data.getSex());
        BooleanExpression compareStatus = conditionMatchStatus(data.getState());

        BooleanExpression keywordExpression = getKeywordExpression(data.getWord(), getLowerAndReplace(field.title));

        BooleanExpression[] totalExpressions = new BooleanExpression[]{keywordExpression, compareRegion, compareSex, compareStatus,
            match.matchDate.between(LocalDateTime.of(data.getStartDate(), LocalTime.MIN), LocalDateTime.of(data.getEndDate(), LocalTime.MAX))};

        List<Match> matchList = query.select(match)
            .from(match)
            .where(totalExpressions)
            .orderBy(match.matchDate.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long totalCount = getTotalCount(match, totalExpressions);

        List<ResponseSearchMatch> list = matchList.stream().map(match -> {
            long countPerson = jpaOrderRepository.countByMatchAndOrderStatus(match, OrderStatus.USE);
            return new ResponseSearchMatch(match, countPerson);
        }).toList();

        System.out.println("list = " + list);
        return new PageImpl<>(list, pageable, totalCount);
    }

    public Page<ResponseSearchUser> findAllBySearchUser(RequestSearchUser data, Pageable pageable) {
        return jpaUserRepository.findAllByNicknameContainingIgnoreCaseOrderByIdDesc(data.getWord(), pageable)
            .map(ResponseSearchUser::new);
    }

    public Page<ResponseUserOrder> findAllByUserOrder(Long userId, Pageable pageable) {
        return jpaOrderRepository.findAllByUser_idOrderByCreateDateDesc(userId, pageable)
            .map(ResponseUserOrder::new);
    }


    private Long getTotalCount(EntityPathBase<?> target, BooleanExpression... expressions) {
        Long count = query.select(target.count())
            .from(target)
            .where(expressions)
            .fetchFirst();
        return count == null ? 0L : count;
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
        // 성별을 선택하지 않았을 경우 모두 조회
        if (sexType == null) return null;
        return match.matchSex.eq(sexType);
    }
    private BooleanExpression conditionMatchStatus(MatchStatus matchStatus) {
        // 상태를 선택하지 않았을 경우 모두 조회
        if (matchStatus == null) return null;
        return match.matchStatus.eq(matchStatus);
    }
}
