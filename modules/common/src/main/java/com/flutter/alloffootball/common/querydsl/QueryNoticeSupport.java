package com.flutter.alloffootball.common.querydsl;

import com.flutter.alloffootball.common.domain.admin.Notice;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.querydsl.suport.QueryDSLRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class QueryNoticeSupport extends QueryDSLRepositorySupport {

    public QueryNoticeSupport() {
        super(Notice.class);
    }


}
