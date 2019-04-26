package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterJpaRepository extends JpaRepository<Tweet, Long> {
}
