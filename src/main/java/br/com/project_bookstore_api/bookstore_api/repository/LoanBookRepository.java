package br.com.project_bookstore_api.bookstore_api.repository;

import br.com.project_bookstore_api.bookstore_api.model.LoanBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanBookRepository extends JpaRepository<LoanBook, UUID> {

    @Query("""
        SELECT l FROM LoanBook l
        WHERE l.dateHourLoan BETWEEN :start AND :end
        """)
    List<LoanBook> findLoansByDateHourLoanBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
        SELECT CASE WHEN COUNT(l) = 0 THEN TRUE ELSE FALSE END
        FROM LoanBook l
        WHERE l.user.id = :userId
        AND l.dateHourLoanReturn > CURRENT_TIMESTAMP
        """)
    boolean isUserAvailableForNewLoan(@Param("userId") UUID userId);
}
