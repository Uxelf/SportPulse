package com.uxelf.sportpulse.external_api.service;

import com.uxelf.sportpulse.external_api.dto.RapidApiTeamResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    public RapidApiTeamResponse getTeams(){
        RapidApiTeamResponse response = new RapidApiTeamResponse();
        response.setResponse(List.of(

                // La Liga
                buildTeam(529, "FC Barcelona", "Spain",
                        "https://media.api-sports.io/football/teams/529.png", 1899, false, 2, List.of(1, 4),
                        "Camp Nou", "C/ d'Arístides Maillol, s/n", "Barcelona", 99354, "grass"),
                buildTeam(541, "Real Madrid", "Spain",
                        "https://media.api-sports.io/football/teams/541.png", 1902, false, 2, List.of(1, 2),
                        "Estadio Santiago Bernabéu", "Av. de Concha Espina, 1", "Madrid", 81044, "grass"),
                buildTeam(530, "Atlético Madrid", "Spain",
                        "https://media.api-sports.io/football/teams/530.png", 1903, false, 1, List.of(3, 4),
                        "Estadio Cívitas Metropolitano", "Av. Luis Aragonés, 4", "Madrid", 68456, "grass"),
                buildTeam(532, "Valencia CF", "Spain",
                        "https://media.api-sports.io/football/teams/532.png", 1919, false, 2, List.of(1, 3, 4),
                        "Estadio de Mestalla", "C/ deست Vicent Ferrer, 44", "Valencia", 49430, "grass"),
                buildTeam(543, "Real Betis", "Spain",
                        "https://media.api-sports.io/football/teams/543.png", 1907, false, 4, List.of(1, 2, 4),
                        "Estadio Benito Villamarín", "Av. de Heliópolis, s/n", "Sevilla", 60721, "grass"),
                buildTeam(536, "Sevilla FC", "Spain",
                        "https://media.api-sports.io/football/teams/536.png", 1890, false, 2, List.of(4),
                        "Estadio Ramón Sánchez-Pizjuán", "C/ Sevilla Fútbol Club, s/n", "Sevilla", 43883, "grass"),

                // Premier League
                buildTeam(33, "Manchester United", "England",
                        "https://media.api-sports.io/football/teams/33.png", 1878, false, 3, List.of(3, 4),
                        "Old Trafford", "Sir Matt Busby Way", "Manchester", 74310, "grass"),
                buildTeam(40, "Liverpool", "England",
                        "https://media.api-sports.io/football/teams/40.png", 1892, false, 2, List.of(1, 3),
                        "Anfield", "Anfield Road", "Liverpool", 53394, "grass"),
                buildTeam(42, "Arsenal", "England",
                        "https://media.api-sports.io/football/teams/42.png", 1886, false, 3, List.of(1, 3, 4),
                        "Emirates Stadium", "Highbury House, 75 Drayton Park", "London", 60704, "grass"),
                buildTeam(50, "Manchester City", "England",
                        "https://media.api-sports.io/football/teams/50.png", 1880, false, 1, List.of(1, 2, 3),
                        "Etihad Stadium", "Sportcity", "Manchester", 53400, "grass"),
                buildTeam(49, "Chelsea", "England",
                        "https://media.api-sports.io/football/teams/49.png", 1905, false, 2, List.of(3),
                        "Stamford Bridge", "Fulham Road", "London", 40853, "grass"),
                buildTeam(47, "Tottenham Hotspur", "England",
                        "https://media.api-sports.io/football/teams/47.png", 1882, false, 4, List.of(1, 4),
                        "Tottenham Hotspur Stadium", "782 High Road", "London", 62850, "grass"),

                // Serie A
                buildTeam(489, "AC Milan", "Italy",
                        "https://media.api-sports.io/football/teams/489.png", 1899, false, 2, List.of(1),
                        "San Siro", "Piazzale Angelo Moratti", "Milan", 75923, "grass"),
                buildTeam(496, "Juventus", "Italy",
                        "https://media.api-sports.io/football/teams/496.png", 1897, false, 1, List.of(2),
                        "Allianz Stadium", "Via Druento, 175", "Turin", 41507, "grass"),
                buildTeam(505, "Inter Milan", "Italy",
                        "https://media.api-sports.io/football/teams/505.png", 1908, false, 2, List.of(3),
                        "San Siro", "Piazzale Angelo Moratti", "Milan", 75923, "grass"),
                buildTeam(497, "AS Roma", "Italy",
                        "https://media.api-sports.io/football/teams/497.png", 1927, false, 1, List.of(4),
                        "Stadio Olimpico", "Viale dei Gladiatori, 2", "Rome", 70634, "grass"),

                // Bundesliga
                buildTeam(157, "Bayern Munich", "Germany",
                        "https://media.api-sports.io/football/teams/157.png", 1900, false, 2, List.of(4),
                        "Allianz Arena", "Werner-Heisenberg-Allee 25", "Munich", 75024, "grass"),
                buildTeam(165, "Borussia Dortmund", "Germany",
                        "https://media.api-sports.io/football/teams/165.png", 1909, false, 3, List.of(3, 4),
                        "Signal Iduna Park", "Strobelallee 50", "Dortmund", 81365, "grass"),
                buildTeam(173, "Bayer Leverkusen", "Germany",
                        "https://media.api-sports.io/football/teams/173.png", 1904, false, 3, List.of(2, 3, 4),
                        "BayArena", "Bismarckstraße 122-124", "Leverkusen", 30210, "grass"),

                // Ligue 1
                buildTeam(85, "Paris Saint-Germain", "France",
                        "https://media.api-sports.io/football/teams/85.png", 1970, false, 2, List.of(1, 2, 3, 4),
                        "Parc des Princes", "24 Rue du Commandant Guilbaud", "Paris", 47929, "grass"),
                buildTeam(80, "Lyon", "France",
                        "https://media.api-sports.io/football/teams/80.png", 1950, false, 4, List.of(1),
                        "Groupama Stadium", "10 Avenue Simone Veil", "Lyon", 59186, "grass"),
                buildTeam(81, "Marseille", "France",
                        "https://media.api-sports.io/football/teams/81.png", 1899, false, 2, List.of(1, 2),
                        "Stade Vélodrome", "3 Boulevard Michelet", "Marseille", 67394, "grass"),

                // Champions League (equipos internacionales adicionales)
                buildTeam(559, "Benfica", "Portugal",
                        "https://media.api-sports.io/football/teams/559.png", 1904, false, 4, List.of(1, 2, 3),
                        "Estádio da Luz", "Av. General Norton de Matos", "Lisbon", 64642, "grass"),
                buildTeam(212, "Ajax", "Netherlands",
                        "https://media.api-sports.io/football/teams/212.png", 1900, false, 2, List.of(1, 2, 3, 4),
                        "Johan Cruyff Arena", "Arena Boulevard 1", "Amsterdam", 54990, "grass")
        ));
        return response;
    }

    private RapidApiTeamResponse.TeamWrapper buildTeam(
            Integer id, String name, String country, String logo,
            Integer founded, boolean national,
            Integer league, List<Integer> seasons,
            String venueName, String venueAddress, String venueCity,
            Integer venueCapacity, String venueSurface) {

        RapidApiTeamResponse.TeamWrapper wrapper = new RapidApiTeamResponse.TeamWrapper();

        RapidApiTeamResponse.TeamInfo team = new RapidApiTeamResponse.TeamInfo();
        team.setId(id);
        team.setName(name);
        team.setCountry(country);
        team.setLogo(logo);
        team.setFounded(founded);
        team.setNational(national);
        team.setLeague(league);
        team.setSeasons(seasons);

        RapidApiTeamResponse.VenueInfo venue = new RapidApiTeamResponse.VenueInfo();
        venue.setName(venueName);
        venue.setAddress(venueAddress);
        venue.setCity(venueCity);
        venue.setCapacity(venueCapacity);
        venue.setSurface(venueSurface);

        wrapper.setTeam(team);
        wrapper.setVenue(venue);

        return wrapper;
    }
}
