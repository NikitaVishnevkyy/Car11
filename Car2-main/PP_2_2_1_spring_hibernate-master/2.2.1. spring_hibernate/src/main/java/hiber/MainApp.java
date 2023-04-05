package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);

      UserService userService = context.getBean(UserService.class);
      userService.deleteAllUsers();
      List<Car> cars = carService.findAll();

      User user1 = new User("Sasha", "Sasha", "Sasha@mail.ru", new Car("car1", "series1"));
      userService.save(user1);


      List<User> uu = userService.findAll();
      for (User user : uu) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println("Найти \"BMW X5\"");
      System.out.println(userService.findOwner("BMW", "X5"));
      context.close();
   }
}
