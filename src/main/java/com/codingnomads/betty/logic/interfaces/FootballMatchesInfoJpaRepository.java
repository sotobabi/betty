package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface FootballMatchesInfoJpaRepository extends JpaRepository<FootballMatchInfo, Long> {

    @Query(value = "SELECT id, api_id, match_date, name FROM football_matches WHERE match_date >= CURDATE()", nativeQuery = true)
    List<FootballMatchInfo> findByMatch_Date();
}
