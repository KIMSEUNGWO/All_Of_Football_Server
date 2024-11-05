package com.flutter.alloffootball.querydsl.suport;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;

@Repository
public abstract class QueryDSLRepositorySupport {

    private final Class<?> domainClass;
    private Querydsl querydsl;
    private EntityManager em;
    private JPAQueryFactory query;

    public QueryDSLRepositorySupport(Class<?> domainClass) {
        Assert.notNull(domainClass, "Domain class must not be null!");
        this.domainClass = domainClass;
    }

    @Autowired
    public void setEm(EntityManager em) {
        Assert.notNull(em, "EntityManager must not be null!");
        JpaEntityInformation<?, ?> entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
        SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
        EntityPath<?> path = resolver.createPath(entityInformation.getJavaType());
        this.em = em;
        this.querydsl = new Querydsl(em, new PathBuilder<>(path.getType(), path.getMetadata()));
        this.query = new JPAQueryFactory(em);
    }
    @PostConstruct
    public void validate() {
        Assert.notNull(em, "EntityManager must not be null!");
        Assert.notNull(querydsl, "Querydsl must not be null!");
        Assert.notNull(query, "QueryFactory must not be null!");
    }

    public JPAQueryFactory getQuery() {
        return query;
    }
    public Querydsl getQuerydsl() {
        return querydsl;
    }

    public <T> JPAQuery<T> select(Expression<T> expr) {
        return getQuery().select(expr);
    }
    public <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
        return getQuery().selectFrom(from);
    }

    public <T> Page<T> applyPagination(Pageable pageable,
                                          EntityPathBase<?> entity,
                                          Function<JPAQueryFactory, JPAQuery<T>> contentQuery) {
        JPAQuery<T> jpaContentQuery = contentQuery.apply(getQuery());

        List<T> content = getQuerydsl().applyPagination(pageable, jpaContentQuery).fetch();

        JPAQuery<Long> countResult = getCountResult(entity, jpaContentQuery);

        return PageableExecutionUtils.getPage(content, pageable,
            countResult::fetchOne);
    }

    private <T> JPAQuery<Long> getCountResult(EntityPathBase<?> entity, JPAQuery<T> jpaContentQuery) {
        return getQuery().select(entity.count())
            .from(entity)
            .where(jpaContentQuery.getMetadata().getWhere());
    }
}
