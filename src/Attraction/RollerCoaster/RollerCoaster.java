package Attraction.RollerCoaster;

import java.util.Date;

import Attraction.Attraction;
import User.User;
import User.Operator.Operator;

public class RollerCoaster extends Attraction {
  private final double minHeightRequired;

  public RollerCoaster(String name, double costOfMaintenance, double m2, int capacity,
      int monthlyRevisionFrequency, Date lastRevisionDate, double minHeightRequired) {
    super(name, costOfMaintenance, m2, capacity, monthlyRevisionFrequency, lastRevisionDate);
    this.minHeightRequired = minHeightRequired;

    this.setRequirements("Min Height required: " + this.minHeightRequired);
    this.setCostOfMaintenance(this.costOfMaintenance * 1.5);
  }

  @Override
  public boolean isUserAllowed(User user) {
    return user.getHeight() >= minHeightRequired;
  }
}