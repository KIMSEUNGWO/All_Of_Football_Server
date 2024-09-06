package com.flutter.alloffootball.querydsl;

import com.flutter.alloffootball.common.domain.board.Board;
import com.flutter.alloffootball.common.enums.region.Region;
import com.flutter.alloffootball.dto.board.RequestSearchBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.flutter.alloffootball.common.domain.board.QBoard.*;

@Repository
@RequiredArgsConstructor
public class QueryDslBoardRepository {

    private final JPAQueryFactory query;

    public List<Board> search(RequestSearchBoard searchBoard, Pageable pageable) {
        return query.select(board)
            .from(board)
            .where(conditionRegion(searchBoard.getRegion()))
            .orderBy(board.createDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    private BooleanExpression conditionRegion(Region region) {
        if (region == null) return null;
        return board.region.eq(region);
    }
}
