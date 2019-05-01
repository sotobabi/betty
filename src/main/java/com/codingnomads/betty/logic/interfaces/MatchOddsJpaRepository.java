package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchOddsJpaRepository extends JpaRepository<MatchOdds, Long> {

    @Override
    MatchOdds save(MatchOdds matchOdds);
}
