package Attraction;

import java.util.Calendar;
import java.util.Date;

import User.User;
import User.Operator.Operator;

public class Attraction {
    private String name;
    private boolean isAvalaible;
    private Operator operator;
    private String requirements;

    protected double costOfMaintenance = 50000;
    private double m2;
    private int capacity;
    private int monthlyRevisionFrequency;
    private Date lastRevisionDate;

    public double getDelayPenalty() {
        return delayPenalty;
    }

    private double operatorSalary = 1200;
    private double delayPenalty = 5000;

    public Attraction(String name, double costOfMaintenance, double m2, int capacity,
            int monthlyRevisionFrequency, Date lastRevisionDate) {
        this.name = name;
        this.costOfMaintenance = costOfMaintenance;
        this.m2 = m2;
        this.capacity = capacity;
        this.monthlyRevisionFrequency = monthlyRevisionFrequency;
        this.lastRevisionDate = lastRevisionDate;
    }

    public boolean isUserAllowed(User user) {
        return true;
    }

    public void setCostOfMaintenance(double costOfMaintenance) {
        this.costOfMaintenance = costOfMaintenance;
    }

    public double getCostOfMaintenance() {
        return costOfMaintenance;
    }

    public boolean isAvalaible() {
        return isAvalaible;
    }

    public String getName() {
        return name;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
        this.operator.setAvalaible(false);

        this.isAvalaible = true;
    }

    public void removeOperator(Operator operator) {
        if (this.operator != null) {
            this.operator.setAvalaible(true);
        }
        this.operator = null;

        this.isAvalaible = false;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getRequirements() {
        return requirements;
    }

    public Operator getOperator() {
        return operator;
    }

    //

    public double getM2() {
        return m2;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMonthlyRevisionFrequency() {
        return monthlyRevisionFrequency;
    }

    public Date getLastRevisionDate() {
        return lastRevisionDate;
    }

    public double calculatDelayedCost() {
        return calculateMonthsDelayed() * this.delayPenalty;
    }

    public int calculateMonthsDelayed() {
        Calendar currentDate = Calendar.getInstance();
        Calendar revisionDate = Calendar.getInstance();
        revisionDate.setTime(lastRevisionDate);

        int monthsSinceLastRevision = (currentDate.get(Calendar.YEAR) - revisionDate.get(Calendar.YEAR)) * 12
                + (currentDate.get(Calendar.MONTH) - revisionDate.get(Calendar.MONTH));

        int monthsDelayed = monthsSinceLastRevision - monthlyRevisionFrequency;

        return Math.max(monthsDelayed, 0);
    }

    public double calculateMaintenanceCost() {
        int monthsDelayed = calculateMonthsDelayed();
        double additionalCost = 0;

        if (monthsDelayed > 0) {
            additionalCost = monthsDelayed * 20000;
        }

        double finalCost = costOfMaintenance + (m2 * 10) + (capacity * 100) + additionalCost;
        return finalCost;
    }

    public double getOperatorSalary() {
        return operatorSalary;
    }

}
