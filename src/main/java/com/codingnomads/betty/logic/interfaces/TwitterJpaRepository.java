package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.data.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwitterJpaRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByKeywordUsedLike(String keywordUsed);

}
