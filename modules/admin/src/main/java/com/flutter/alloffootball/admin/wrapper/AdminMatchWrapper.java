package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.ResponseSearchMatch;
import com.flutter.alloffootball.admin.dto.match.ResponseViewMatch;
import com.flutter.alloffootball.admin.dto.match.ResponseViewUser;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.SexType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminMatchWrapper {

    private final AdminFieldWrapper adminFieldWrapper;

    public ResponseSearchMatch searchMatchWrap(Match match, long currentPerson) {
        return ResponseSearchMatch.builder()
            .matchId(match.getId())
            .matchDate(match.getMatchDate())
            .region(match.getField().getAddress().getRegion().getKo())
            .sex(SexType.getKo(match.getMatchSex()))
            .title(match.getField().getTitle())
            .person(String.format("%d/%d", currentPerson, match.getMaxPerson()))
            .matchStatus(MatchStatus.getKo(match.getMatchStatus()))
            .build();
    }

    public ResponseViewMatch viewMatchWrap(Match match, List<ResponseViewUser> userList) {
        return ResponseViewMatch.builder()
            .matchId(match.getId())
            .matchDate(match.getMatchDate())
            .personCount(match.getPersonCount())
            .matchCount(match.getMatchCount())
            .sexType(match.getMatchSex())
            .price(match.getPrice())
            .matchTime(match.getMatchTime())
            .status(match.getMatchStatus())
            .users(userList)
            .field(adminFieldWrapper.viewFieldWrap(match.getField()))
            .build();
    }

    public ResponseViewUser viewUserWrap(Order order) {
        return ResponseViewUser.builder()
            .userId(order.getUser().getId())
            .nickname(order.getUser().getNickname())
            .price(order.getPrice())
            .createDate(order.getCreateDate())
            .build();
    }

}
