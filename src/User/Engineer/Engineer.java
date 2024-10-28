package User.Engineer;

import User.User;

public class Engineer extends User{
  private String name;

  public Engineer(int age, String name) {
    super(age);
    this.name = name;
    this.setEngineer(true);

    if (age < 18) {
      System.out.println("Operator must be at least 18 years old to be an employee.");
    }
  }


  @Override
  public String toString() {
    return name + " ( Engineer )";
  }  
}
