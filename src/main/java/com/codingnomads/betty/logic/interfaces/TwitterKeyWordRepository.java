package com.codingnomads.betty.logic.interfaces;

import java.util.List;

public interface TwitterKeyWordRepository {

    List<String> getAccountsWithTeamName(String homeTeam);
}
