package com.codingnomads.betty.logic.interfaces;

import java.util.List;

public interface TwitterKeywordRepository {

    List<String> getAccountsWithTeamName(String homeTeam);
}
