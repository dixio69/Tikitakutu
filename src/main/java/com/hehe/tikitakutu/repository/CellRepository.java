package com.hehe.tikitakutu.repository;

import com.hehe.tikitakutu.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CellRepository extends JpaRepository<Cell, Long> {
    List<Cell> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);

    @Query("SELECT x FROM Cell x " +
            "WHERE x.sessionId = :sessionId " +
            "AND x.location_x = :locationX " +
            "AND x.location_y = :locationY ")
    Cell findBySessionIdLocation(String sessionId, int locationX, int locationY);
}
