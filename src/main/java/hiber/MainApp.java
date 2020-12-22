package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      String model = "BMW";
      int series = 7;

      userService.add(new User("Andrey", "Volik", "volik@mail.ru", new Car("BMW", 7)));
      userService.add(new User("Michael", "Zelenkov", "zelenkov@mail.ru", new Car("Audi", 100)));
      userService.add(new User("Vladimir", "Cherepov", "cherepov@mail.ru", new Car("Audi", 80)));
      userService.add(new User("Dmitriy", "Sazhnev", "sazhnev@mail.ru", new Car("VOLKSWAGEN", 10)));
      userService.add(new User("Igor", "Nikifirov","nikiforov@mail.ru", new Car("Audi",7)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
         System.out.println(userService.getUserCar(model,series));
      }
      context.close();
   }
}