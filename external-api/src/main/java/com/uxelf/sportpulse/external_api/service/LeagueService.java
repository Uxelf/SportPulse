package com.uxelf.sportpulse.external_api.service;

import com.uxelf.sportpulse.external_api.dto.RapidApiLeagueResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueService {

    public RapidApiLeagueResponse getLeagues() {
        RapidApiLeagueResponse response = new RapidApiLeagueResponse();
        response.setResponse(List.of(
                buildLeague(140, "La Liga", "League",
                        "https://media.api-sports.io/football/leagues/140.png",
                        "Spain", "ES",
                        List.of(
                                buildSeason(2021, "2021-08-13", "2022-05-22", false),
                                buildSeason(2022, "2022-08-12", "2023-05-28", false),
                                buildSeason(2023, "2023-08-11", "2024-05-26", false),
                                buildSeason(2024, "2024-08-17", "2025-05-25", true)
                        )),
                buildLeague(39, "Premier League", "League",
                        "https://media.api-sports.io/football/leagues/39.png",
                        "England", "GB",
                        List.of(
                                buildSeason(2021, "2021-08-13", "2022-05-22", false),
                                buildSeason(2022, "2022-08-05", "2023-05-28", false),
                                buildSeason(2023, "2023-08-11", "2024-05-19", false),
                                buildSeason(2024, "2024-08-16", "2025-05-25", true)
                        )),
                buildLeague(135, "Serie A", "League",
                        "https://media.api-sports.io/football/leagues/135.png",
                        "Italy", "IT",
                        List.of(
                                buildSeason(2021, "2021-08-21", "2022-05-22", false),
                                buildSeason(2022, "2022-08-13", "2023-06-04", false),
                                buildSeason(2023, "2023-08-19", "2024-05-26", false),
                                buildSeason(2024, "2024-08-17", "2025-05-25", true)
                        )),
                buildLeague(78, "Bundesliga", "League",
                        "https://media.api-sports.io/football/leagues/78.png",
                        "Germany", "DE",
                        List.of(
                                buildSeason(2021, "2021-08-13", "2022-05-14", false),
                                buildSeason(2022, "2022-08-05", "2023-05-27", false),
                                buildSeason(2023, "2023-08-18", "2024-05-18", false),
                                buildSeason(2024, "2024-08-23", "2025-05-17", true)
                        )),
                buildLeague(61, "Ligue 1", "League",
                        "https://media.api-sports.io/football/leagues/61.png",
                        "France", "FR",
                        List.of(
                                buildSeason(2021, "2021-08-07", "2022-05-21", false),
                                buildSeason(2022, "2022-07-29", "2023-06-03", false),
                                buildSeason(2023, "2023-08-11", "2024-05-18", false),
                                buildSeason(2024, "2024-08-16", "2025-05-24", true)
                        )),
                buildLeague(2, "UEFA Champions League", "Cup",
                        "https://media.api-sports.io/football/leagues/2.png",
                        "World", "WL",
                        List.of(
                                buildSeason(2021, "2021-07-06", "2022-05-28", false),
                                buildSeason(2022, "2022-06-28", "2023-06-10", false),
                                buildSeason(2023, "2023-06-27", "2024-06-01", false),
                                buildSeason(2024, "2024-07-09", "2025-05-31", true)
                        )),
                buildLeague(94, "Primeira Liga", "League",
                        "https://media.api-sports.io/football/leagues/94.png",
                        "Portugal", "PT",
                        List.of(
                                buildSeason(2021, "2021-08-07", "2022-05-21", false),
                                buildSeason(2022, "2022-08-06", "2023-05-28", false),
                                buildSeason(2023, "2023-08-12", "2024-05-18", false),
                                buildSeason(2024, "2024-08-10", "2025-05-18", true)
                        )),
                buildLeague(88, "Eredivisie", "League",
                        "https://media.api-sports.io/football/leagues/88.png",
                        "Netherlands", "NL",
                        List.of(
                                buildSeason(2021, "2021-08-13", "2022-05-15", false),
                                buildSeason(2022, "2022-08-05", "2023-05-28", false),
                                buildSeason(2023, "2023-08-11", "2024-05-12", false),
                                buildSeason(2024, "2024-08-09", "2025-05-11", true)
                        ))
        ));
        return response;
    }

    private RapidApiLeagueResponse.LeagueWrapper buildLeague(
            Integer id, String name, String type, String logo,
            String country, String code,
            List<RapidApiLeagueResponse.SeasonInfo> seasons) {

        RapidApiLeagueResponse.LeagueWrapper wrapper = new RapidApiLeagueResponse.LeagueWrapper();

        RapidApiLeagueResponse.LeagueInfo league = new RapidApiLeagueResponse.LeagueInfo();
        league.setId(id);
        league.setName(name);
        league.setType(type);
        league.setLogo(logo);

        RapidApiLeagueResponse.CountryInfo countryInfo = new RapidApiLeagueResponse.CountryInfo();
        countryInfo.setName(country);
        countryInfo.setCode(code);

        wrapper.setLeague(league);
        wrapper.setCountry(countryInfo);
        wrapper.setSeasons(seasons);

        return wrapper;
    }

    private RapidApiLeagueResponse.SeasonInfo buildSeason(
            Integer year, String start, String end, boolean current) {

        RapidApiLeagueResponse.SeasonInfo season = new RapidApiLeagueResponse.SeasonInfo();
        season.setYear(year);
        season.setStart(start);
        season.setEnd(end);
        season.setCurrent(current);
        return season;
    }
}

