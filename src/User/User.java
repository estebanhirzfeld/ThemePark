package User;

import java.util.List;

import Attraction.Attraction;
import User.Operator.Operator;

public class User {

  private int age;
  private double height;
  private boolean isOperator = false;
  private boolean isEngineer = false;

  public int getAge() {
    return age;
  }

  protected void setOperator(boolean isOperator) {
    this.isOperator = isOperator;
  }

  public boolean isEngineer() {
    return isEngineer;
  }

  public void setEngineer(boolean isEngineer) {
    this.isEngineer = isEngineer;
  }

  public double getHeight() {
    return height;
  }

  public User(int age, double height) {
    this.age = age;
    this.height = height;
  }

  protected User(int age) {
    this.age = age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public String rideAttraction(Attraction attraction, List<Operator> operators) {
    if (attraction.isAvalaible()) {
      if (attraction.isUserAllowed(this)) {
        return "You're Allowed to ride: " + attraction.getName() + " have fun!";
      } else {
        return "You must comply this requirements: \n" + attraction.getRequirements();
      }
    } else {
      for (Operator operator : operators) {
        if (operator.isAvalaible()) {
          operator.operateAtraction(attraction);
        }
      }
      return "Attraction currently not avalaible, calling an operator...";
    }
  }

  public boolean isOperator() {
    return isOperator;
  }

  public void operateAtraction(Attraction selectedAttraction) {
  }

  public void leaveAtraction() {
  }

}
