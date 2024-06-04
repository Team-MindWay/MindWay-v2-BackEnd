package com.mindway.server.v2.domain.book.service.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.repository.BookRepository;
import com.mindway.server.v2.domain.book.service.BookWriteService;
import com.mindway.server.v2.domain.book.util.BookConverter;
import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.goal.exception.NotExistGoalException;
import com.mindway.server.v2.domain.goal.repository.GoalRepository;
import com.mindway.server.v2.domain.rank.entity.Ranks;
import com.mindway.server.v2.domain.rank.repository.RankRepository;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@ServiceWithTransaction
@RequiredArgsConstructor
public class BookWriteServiceImpl implements BookWriteService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final UserUtil userUtil;
    private final RankRepository rankRepository;
    private final GoalRepository goalRepository;

    public void execute(BookWriteRequest bookWriteRequest) {
        User user = userUtil.getCurrentUser();

        Book book = bookConverter.toEntity(bookWriteRequest, user);
        accrue(user);
        bookRepository.save(book);
    }

    private void accrue(User user) {
        Ranks rank = rankRepository.findByUser(user)
                .orElseGet(() -> saveUserRank(user));

        rank.accrue();
        increaseGoals(user);
        rankRepository.save(rank);
    }

    private Ranks saveUserRank (User user) {
        return Ranks.builder()
                .user(user)
                .accrue(0)
                .build();
    }

    private void increaseGoals(User user) {
        Goal goal = goalRepository.findByUser(user)
                .orElseThrow(NotExistGoalException::new);

        int day = LocalDate.now().getDayOfWeek().getValue();

        goal.getWeek().accrue(day);

        goal.accrue();
        goalRepository.save(goal);
    }
}
