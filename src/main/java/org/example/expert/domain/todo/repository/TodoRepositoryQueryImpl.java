package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryQueryImpl implements TodoRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {

        Todo result = jpaQueryFactory
                .selectFrom(QTodo.todo)
                .leftJoin(QTodo.todo.user, QUser.user).fetchJoin()
                .where(QTodo.todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
