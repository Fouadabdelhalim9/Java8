package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaExample {

    public static void main(final String[] args) {

        final UsersRepository repository = new UsersRepository();

        banner("Listing all users");
        // SOLVED all users
        repository.select(null, null);

        banner("Listing all users");
      // SOLVED With functional interfaces declared
repository.select(user -> user.active, null);
        Predicate<User> activeUserPredicate = user -> user.active;
        repository.select(activeUserPredicate, null);

        banner("Listing all users - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user -> user.active, null);

        banner("Listing users with age > 5 sorted by name");
        // TODO With functional interfaces declared
        Predicate<User> ageGreaterThanFive = user -> user.age > 5;
        Comparator<User> sortByUserName = Comparator.comparing(user -> user.name);
        repository.select(ageGreaterThanFive, sortByUserName);

        banner("Listing users with age > 5 sorted by name - lambda");
//        // TODO With functional interfaces used directly
        repository.select(user -> user.age > 5, Comparator.comparing(user -> user.name));
        banner("Listing users with age < 10 sorted by age");// TODO With functional interfaces declared
        Predicate<User> ageLessThanTen = user -> user.age < 10;
        Comparator<User> sortByUserAge = Comparator.comparingInt(user -> user.age);
        repository.select(ageLessThanTen, sortByUserAge);

        banner("Listing users with age < 10 sorted by age - lambda");
        // TODO With functional interfaces used directly
        repository.select(user -> user.age < 10, Comparator.comparingInt(user -> user.age));

        banner("Listing active users sorted by name");
        // TODO With functional interfaces declared
        Predicate<User> activeUsers = user -> user.active;
        Comparator<User> sortByUserNameAgain = Comparator.comparing(user -> user.name);
        repository.select(activeUsers, sortByUserNameAgain);

       banner("Listing active users sorted by name - lambda");
       // TODO With functional interfaces used directly
        repository.select(user -> user.active, Comparator.comparing(user -> user.name));
        banner("Listing active users with age > 8 sorted by name");
        // TODO With functional interfaces declared
        Predicate<User> activeUsersAgeGreaterThanEight = user -> user.active && user.age > 8;
        repository.select(activeUsersAgeGreaterThanEight, Comparator.comparing(user -> user.name));

       banner("Listing active users with age > 8 sorted by name - lambda");
        // TODO With functional interfaces used directly
        repository.select(user -> user.active && user.age > 8, Comparator.comparing(user -> user.name));

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }

}

class UsersRepository {
    List<User> users;

    UsersRepository() {
        users = Arrays.asList(
            new User("Seven", 7, true),
            new User("Four", 4, false),
            new User("Eleven", 11, true),
            new User("Three", 3, true),
            new User("Nine", 9, false),
            new User("One", 1, true),
            new User("Twelve", 12, true));
    }

    void select(final Predicate<User> filter, final Comparator<User> order) {
        Stream<User> usersStream = users.stream();

        if (filter != null) {
            usersStream = usersStream.filter(filter);
        }
        if (order != null) {
            usersStream = usersStream.sorted(order);
        }
        usersStream.forEach(System.out::println);
    }
}

class User {
    String name;
    int age;
    boolean active;

    User(final String name, final int age, boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "\t| " + age;
    }
}


