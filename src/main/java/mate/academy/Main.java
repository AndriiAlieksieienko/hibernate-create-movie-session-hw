package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    public static void main(String[] args) {
        Injector injector = Injector.getInstance("mate.academy");
        MovieService movieService;
        movieService = (MovieService) injector.getInstance(MovieService.class);
        CinemaHallService cinemaHallService;
        cinemaHallService = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        MovieSessionService movieSessionService;
        movieSessionService = (MovieSessionService) injector.getInstance(MovieSessionService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        movieService.add(new Movie("Terminator"));
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(fastAndFurious);
        movieSession.setCinemaHall(cinemaHall);
        LocalDateTime dateTime = LocalDateTime.of(
                LocalDate.of(2026,6,2),
                LocalTime.of(13, 30)
        );
        movieSession.setShowTime(dateTime);
        movieSessionService.add(movieSession);

        LocalDate date = LocalDate.of(2026,6,2);
        List<MovieSession> foundSessions = movieSessionService.findAvailableSessions(1L, date);
        System.out.println(foundSessions);
    }
}
