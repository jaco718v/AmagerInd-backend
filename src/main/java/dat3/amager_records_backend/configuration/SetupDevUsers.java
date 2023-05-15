package dat3.amager_records_backend.configuration;


import dat3.amager_records_backend.dto.EventRequest;
import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.entity.News;
import dat3.amager_records_backend.repository.EventRepository;
import dat3.amager_records_backend.repository.NewsRepository;

import dat3.amager_records_backend.entity.Vinyl;
import dat3.amager_records_backend.repository.VinylRepository;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDateTime;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;

    VinylRepository vinylRepository;
    EventRepository eventRepository;

    String passwordUsedByAll;
    NewsRepository newsRepository;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, VinylRepository vinylRepository, NewsRepository newsRepository, EventRepository eventRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.vinylRepository = vinylRepository;
        passwordUsedByAll = "test12";
        this.newsRepository = newsRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");


        //News news1 = new News("hej","her Står noget text", "meget vigtig!");
        //EventRequest event = new EventRequest("Event","Koncert","Koncert med George Michael", LocalDateTime.now());
        //EventEntity eventEntity = new EventEntity(event);
        //eventRepository.save(eventEntity);
        //newsRepository.save(news1);


        /*
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
         */
        Vinyl vinyl = new Vinyl("David Bowie", "Hunky Dory", "UK", 1971, "SF 8244", "Rock", "Billede", 3000);
        Vinyl vinyl2 = new Vinyl("Pink Floyd", "The Wall", "UK", 1980, "LSP 3467", "Rock", "Billede", 200);
        vinylRepository.save(vinyl);
        vinylRepository.save(vinyl2);
    }
}
