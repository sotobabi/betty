package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchOddsJpaRepository extends JpaRepository<MatchOdds, Long> {

    @Override
    MatchOdds save(MatchOdds matchOdds);

    @Override
    @Query(value = "SELECT t.id, t.away_team, t.away_team_odd, t.home_team, t.home_team_odd, t.match_date FROM match_odds t " +
            "INNER JOIN (" +
            "SELECT max(id) AS MaxId, away_team " +
            "FROM match_odds " +
            "GROUP BY away_team" +
            ") tm ON t.id = tm.MaxId AND t.away_team = tm.away_team", nativeQuery = true)
    List<MatchOdds> findAll();

    @Query(value = "SELECT id, away_team, away_team_odd, home_team, home_team_odd, match_date FROM match_odds " +
            "WHERE match_date >= CURDATE() ORDER BY id DESC LIMIT 1", nativeQuery = true)
    MatchOdds findLatestInstanceInMatchOddsTable();


}
