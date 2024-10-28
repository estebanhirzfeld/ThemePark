package Attraction.HorrorHouse;

import java.util.Date;

import Attraction.Attraction;
import User.User;

public class HorrorHouse extends Attraction {
  private final int minAgeRequired;

  public HorrorHouse(String name, double costOfMaintenance, double m2, int capacity,
      int monthlyRevisionFrequency, Date lastRevisionDate, int minAgeRequired) {
    super(name, costOfMaintenance, m2, capacity, monthlyRevisionFrequency, lastRevisionDate);
    this.minAgeRequired = minAgeRequired;

    this.setRequirements("Min Age required: " + this.minAgeRequired);
    this.setCostOfMaintenance(this.costOfMaintenance * 1.2);
  }

  @Override
  public boolean isUserAllowed(User user) {
    return user.getAge() >= minAgeRequired;
  }
}
