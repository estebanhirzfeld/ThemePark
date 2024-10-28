package User.Operator;

import Attraction.Attraction;
import User.User;

public class Operator extends User {
  private String name;
  private boolean isAvalaible = true;
  private Attraction attraction;

  public Operator(int age, String name) {
    super(age);
    this.name = name;
    this.setOperator(true);

    if (age < 18) {
      System.out.println("Operator must be at least 18 years old to be an employee.");
    }
  }

  public void operateAtraction(Attraction attraction) {
    if(!attraction.isAvalaible() && attraction.getOperator() == null){
      attraction.setOperator(this);
      this.attraction = attraction;
    }
  }

  public Attraction getAttraction() {
    return attraction;
  }

  @Override
  public void leaveAtraction() {
    this.attraction.removeOperator(this);
    this.attraction = null;
  }

  @Override
  public String toString() {
    return name + " ( Operator )";
  }

  public boolean isAvalaible() {
    return isAvalaible;
  }

  public String getName() {
    return name;
  }

  public void setAvalaible(boolean isAvalaible) {
    this.isAvalaible = isAvalaible;
  }
  
}
