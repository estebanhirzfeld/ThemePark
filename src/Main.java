import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import Attraction.Attraction;
import Attraction.HorrorHouse.HorrorHouse;
import Attraction.RollerCoaster.RollerCoaster;
import User.User;
import User.Engineer.Engineer;
import User.Operator.Operator;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Main {

  // Presupuesto
  public static double calculateBudget(double maintenanceCost, double operatorSalary) {
    double constructionCost = (maintenanceCost * 18) + (operatorSalary * 18);
    return constructionCost;
  }

  // DEBUG
  public static int availableOperators(List<Operator> operators) {
    int count = 0;
    for (Operator operator : operators) {
      if (operator.isAvalaible()) {
        count++;
      }
    }
    return count;
  }

  public static boolean checkNumber(String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }

    boolean hasDecimal = false;

    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);

      if (c == '.') {
        if (hasDecimal) {
          return false;
        }
        hasDecimal = true;
      } else if (!Character.isDigit(c)) {
        return false;
      }
    }

    return hasDecimal || input.length() > 0;
  }

  public static void customDialog(String title, String msg, int type) {
    if (title == null) {
      title = "ThemePark";
    } else {
      title = "ThemePark - " + title;
    }

    JOptionPane.showMessageDialog(
        null,
        msg,
        title,
        type);
  }

  public static List<Attraction> getUnavailableAttractions(List<Attraction> attractions) {
    List<Attraction> availableAttractions = new ArrayList<>();

    for (Attraction attraction : attractions) {
      if (!attraction.isAvalaible()) {
        availableAttractions.add(attraction);
      }
    }

    return availableAttractions;
  }

  public static List<Attraction> getAvailableAttractions(List<Attraction> attractions) {
    List<Attraction> availableAttractions = new ArrayList<>();

    for (Attraction attraction : attractions) {
      if (attraction.isAvalaible()) {
        availableAttractions.add(attraction);
      }
    }

    return availableAttractions;
  }

  public static Attraction attractionSelectDialog(List<Attraction> attractions, String title, String msg) {
    if (title == null) {
      title = "ThemePark"; // Default title if none provided
    }
    if (attractions == null || attractions.isEmpty()) {
      customDialog("Error", "No hay Atracciones Disponibles", 0); // Show error if no attractions are available
      return null;
    }

    // Convert List<Attraction> to an array for JOptionPane
    Attraction[] attractionArray = attractions.toArray(new Attraction[0]);

    // Display selection dialog
    return (Attraction) JOptionPane.showInputDialog(null, msg, "ThemePark - " + title,
        JOptionPane.INFORMATION_MESSAGE, null,
        attractionArray, attractionArray[0]);
  }

  public static int customOptionsDialog(String[] options, String title, String msg) {
    if (title == null) {
      title = "ThemePark";
    }
    return JOptionPane.showOptionDialog(null, msg, "ThemePark - " + title, JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null, options, options[0]);
  }

  public static User userSelectDialog(User[] users, String title, String msg) {
    if (title == null) {
      title = "ThemePark";
    }
    return (User) JOptionPane.showInputDialog(null, msg, "ThemePark - " + title, JOptionPane.INFORMATION_MESSAGE,
        null, users, users[0]);
  }

  public static String formatAttractionDetails(Attraction attraction) {
    StringBuilder msg = new StringBuilder();

    msg.append("Detalles de la Atracción:\n");
    msg.append("Nombre: ").append(attraction.getName()).append("\n");
    msg.append("Área (m2): ").append(attraction.getM2()).append("\n");
    msg.append("Capacidad: ").append(attraction.getCapacity()).append("\n");
    msg.append("Frecuencia de Revisión Mensual: ").append(attraction.getMonthlyRevisionFrequency()).append(" meses\n");
    msg.append("Fecha de Última Revisión: ").append(attraction.getLastRevisionDate()).append("\n");
    msg.append("Coste de Mantenimiento: $").append(attraction.calculateMaintenanceCost()).append("\n");

    int monthsDelayed = attraction.calculateMonthsDelayed();
    if (monthsDelayed > 0) {
      msg.append("\nADVERTENCIA: La revisión está retrasada por ").append(monthsDelayed)
          .append(" meses. Se aplicarán costos adicionales ").append("( $" + attraction.calculatDelayedCost() + " ).");
    } else {
      msg.append("\nNo hay retrasos en la revisión.\n");
    }

    return msg.toString();
  }

  public static String formatAttractionCreationMsg(String name, double costOfMaintenance, double m2,
      int capacity, int monthlyRevisionFrequency,
      Date lastRevisionDate) {
    StringBuilder msg = new StringBuilder();

    msg.append("Crear Nueva Atracción:").append("\n\n");

    msg.append("Nombre: ").append(name.isEmpty() ? "[No ingresado]" : name).append("\n");
    msg.append("Costo de Mantenimiento: $").append(costOfMaintenance).append("\n");
    msg.append("Área (m2): ").append(m2).append("\n");
    msg.append("Capacidad: ").append(capacity).append("\n");
    msg.append("Frecuencia de Revisión Mensual: ").append(monthlyRevisionFrequency).append(" meses\n");
    msg.append("Fecha de Última Revisión: ")
        .append(
            lastRevisionDate == null ? "[No ingresado]" : lastRevisionDate)
        .append("\n\n");

    return msg.toString();
  }

  public static String menuFormatMsg(User user, List<Attraction> attractions) {
    StringBuilder msg = new StringBuilder();

    if (user != null) {

      if (attractions.size() > 0) {
        if (!user.isEngineer() && !user.isOperator()) {
          msg.append("Edad: ").append(user.getAge());
          msg.append(", Altura: ").append(user.getHeight()).append("cm.\n\n");
        }

        msg.append("Atracciones: ").append("\n");
        for (Attraction attraction : attractions) {
          msg.append(attraction.getName());
          msg.append(attraction.isAvalaible() ? " - Disponible" : " - No Disponible");
          msg.append("\n");
        }
      }
    } else {
      msg.append("Aún no hay Atracciones.\n");
    }
    return msg.toString();
  }

  public static void main(String[] args) {

    // Engineer
    Engineer steve = new Engineer(24, "Steve");

    // Operator
    Operator tony = new Operator(18, "Tony");
    Operator john = new Operator(18, "John");

    // Users
    User user_18_180 = new User(18, 180);
    User user_14_150 = new User(14, 150);

    // Users Array
    User[] users = { user_14_150, user_18_180, tony, john, steve };

    Calendar calendar = Calendar.getInstance();
    calendar.set(2023, Calendar.MAY, 10); // Example: May 10, 2023
    Date lastRevisionDate = calendar.getTime();

    // Attractions
    RollerCoaster rollerCoaster = new RollerCoaster("Sure Death Coaster", 50000, 160, 40, 6, lastRevisionDate,
        160);
    HorrorHouse horrorHouse = new HorrorHouse("Spooky Castle", 50000, 120, 15, 25, lastRevisionDate, 16);
    Attraction randomAtrrAttraction_1 = new Attraction("Merry Go Round", 50000, 50, 70, 1, lastRevisionDate);
    Attraction randomAtrrAttraction_2 = new Attraction("Ferris Wheel", 50000, 80, 60, 1, lastRevisionDate);

    // Attractions List
    List<Attraction> attractions = new ArrayList<>();
    attractions.add(rollerCoaster);
    attractions.add(horrorHouse);
    attractions.add(randomAtrrAttraction_1);
    attractions.add(randomAtrrAttraction_2);

    // Operators List
    List<Operator> operators = new ArrayList<>();
    operators.add(tony);
    operators.add(john);

    tony.operateAtraction(rollerCoaster);

    // ------------------------------------------------------------------------------------------------------------------------------------

    int option;
    Attraction selectedAttraction = null;

    String[] regularMenuOptions = {
        "Seleccionar Atraccion",
        "Modificar Edad",
        "Modificar Altura",
        "Cambiar de Cuenta"
    };

    String[] operatorMenuOptions = {
        "Operar Atraccion",
        "Abandonar Atraccion",
        "Mi Atraccion",
        "Cambiar de Cuenta"
    };

    String[] engineerMenuOptions = {
        "Inspeccionar Atracción",
        "Crear Atracción",
        "Simulador de Costos",
        "Cambiar de Cuenta"
    };

    boolean exitSignIn = false;
    while (!exitSignIn) {
      User loggedUser = userSelectDialog(users, "Iniciar Sesion", "Seleccionar Cuenta");
      if (loggedUser == null) {
        exitSignIn = true;
      } else {
        boolean logOut = false;

        // Determine menu options based on user type
        String[] menuOptions;
        if (loggedUser.isOperator()) {
          menuOptions = operatorMenuOptions;
        } else if (loggedUser.isEngineer()) {
          menuOptions = engineerMenuOptions;
        } else {
          menuOptions = regularMenuOptions;
        }

        while (!logOut) {
          option = customOptionsDialog(menuOptions, "Tu Cuenta", menuFormatMsg(loggedUser, attractions));

          switch (menuOptions[option]) {
            case "Seleccionar Atraccion":
              List<Attraction> availableAttractions = getAvailableAttractions(attractions);
              selectedAttraction = attractionSelectDialog(availableAttractions, "Seleccionar",
                  "Atracciones Disponibles");

              if (selectedAttraction != null) {
                String response = loggedUser.rideAttraction(selectedAttraction, operators);
                customDialog("Atracción: '" + selectedAttraction.toString() + "'", response, 1);
              }
              break;
            case "Modificar Edad":
              String ageInput;
              while (true) {
                ageInput = JOptionPane.showInputDialog(null, "Ingrese edad");
                if (checkNumber(ageInput)) {
                  loggedUser.setAge(Math.abs(Integer.parseInt(ageInput)));
                  break;
                } else {
                  customDialog("Error", "Por favor, ingrese un número válido.", 0);
                }
              }
              break;
            case "Modificar Altura":
              String heightInput;
              while (true) {
                heightInput = JOptionPane.showInputDialog(null, "Ingrese altura");
                if (checkNumber(heightInput)) {
                  loggedUser.setHeight(Math.abs(Double.parseDouble(heightInput)));
                  break;
                } else {
                  customDialog("Error", "Por favor, ingrese un número válido.", 0);
                }
              }
              break;

            // Operator Options
            case "Operar Atraccion":
              List<Attraction> unavailableAttractions = getUnavailableAttractions(attractions);
              selectedAttraction = attractionSelectDialog(unavailableAttractions, "Seleccionar",
                  "Atracciones Disponibles");

              if (selectedAttraction != null) {
                if (loggedUser instanceof Operator) {
                  Operator operator = (Operator) loggedUser;
                  if (operator.getAttraction() != null) {
                    operator.leaveAtraction();
                  }
                }
                loggedUser.operateAtraction(selectedAttraction);
                customDialog("Atracción: '" + selectedAttraction.toString() + "'", "Ahora Disponible", 1);
              }
              break;
            case "Abandonar Atraccion":
              List<Attraction> tempAttractions = getAvailableAttractions(attractions);
              for (Attraction attraction : tempAttractions) {
                if (attraction.getOperator() == loggedUser) {
                  loggedUser.leaveAtraction();
                }
              }
              break;

            case "Mi Atraccion":
              String attractionMessage = ((Operator) loggedUser).getAttraction() != null
                  ? "Atracción: '" + ((Operator) loggedUser).getAttraction() + "'"
                  : "Actualmente no se está operando ninguna atracción";
              customDialog("Actualmente Operando...", attractionMessage, 1);
              break;

            // Engineer Options
            case "Inspeccionar Atracción":
              selectedAttraction = attractionSelectDialog(attractions, "Seleccionar", "Atracción");
              if (selectedAttraction != null) {
                String attractionDetails = formatAttractionDetails(selectedAttraction);
                customDialog("Detalles de la Atracción", attractionDetails, 1);
              }
              break;

            case "Crear Atracción":
              boolean creatingAttraction = true;
              String name = "";
              double costOfMaintenance = 0;
              double m2 = 0;
              int capacity = 0;
              int monthlyRevisionFrequency = 0;

              LocalDateTime now = LocalDateTime.now();
              lastRevisionDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

              while (creatingAttraction) {
                String[] creationMenuOptions = {
                    "Nombre",
                    "Costo de Mantenimiento",
                    "Área (m2)",
                    "Capacidad",
                    "Frecuencia de Revisión Mensual",
                    "Crear Atracción",
                    "Cancelar"
                };

                int creationOption = customOptionsDialog(creationMenuOptions, "Crear Atracción",
                    formatAttractionCreationMsg(name, costOfMaintenance, m2, capacity, monthlyRevisionFrequency,
                        lastRevisionDate));

                switch (creationMenuOptions[creationOption]) {
                  case "Nombre":
                    name = JOptionPane.showInputDialog(null, "Ingrese el nombre de la atracción:");
                    break;
                  case "Costo de Mantenimiento":
                    while (true) {
                      String costInput = JOptionPane.showInputDialog(null, "Ingrese el costo de mantenimiento:");
                      if (checkNumber(costInput)) {
                        costOfMaintenance = Math.abs(Double.parseDouble(costInput));
                        break;
                      } else {
                        customDialog("Error", "Por favor, ingrese un número válido.", 0);
                      }
                    }
                    break;
                  case "Área (m2)":
                    while (true) {
                      String areaInput = JOptionPane.showInputDialog(null, "Ingrese el área en metros cuadrados:");
                      if (checkNumber(areaInput)) {
                        m2 = Math.abs(Double.parseDouble(areaInput));
                        break;
                      } else {
                        customDialog("Error", "Por favor, ingrese un número válido.", 0);
                      }
                    }
                    break;
                  case "Capacidad":
                    while (true) {
                      String capacityInput = JOptionPane.showInputDialog(null, "Ingrese la capacidad:");
                      if (checkNumber(capacityInput)) {
                        capacity = Math.abs(Integer.parseInt(capacityInput));
                        break;
                      } else {
                        customDialog("Error", "Por favor, ingrese un número válido.", 0);
                      }
                    }
                    break;
                  case "Frecuencia de Revisión Mensual":
                    while (true) {
                      String frequencyInput = JOptionPane.showInputDialog(null,
                          "Ingrese la frecuencia de revisión mensual (en meses):");
                      if (checkNumber(frequencyInput)) {
                        monthlyRevisionFrequency = Math.abs(Integer.parseInt(frequencyInput));
                        break;
                      } else {
                        customDialog("Error", "Por favor, ingrese un número válido.", 0);
                      }
                    }
                    break;
                  case "Crear Atracción":
                    Attraction newAttraction = new Attraction(name, costOfMaintenance, m2, capacity, capacity,
                        lastRevisionDate);
                    attractions.add(newAttraction);
                    customDialog("Éxito", "Atracción creada exitosamente:\n" + formatAttractionDetails(newAttraction),
                        1);
                    creatingAttraction = false;
                    break;
                  case "Cancelar":
                    creatingAttraction = false;
                    break;
                }
              }
              break;

            case "Simulador de Costos":
              selectedAttraction = attractionSelectDialog(attractions, "Seleccionar",
                  "Atraccion");

              double maintenanceCost = selectedAttraction.calculateMaintenanceCost();
              double operatorSalary = selectedAttraction.getOperatorSalary();

              double totalBudget = calculateBudget(maintenanceCost, operatorSalary);

              customDialog("Simulador de Costos",
                  "El presupuesto total para la construcción de otra ' " + selectedAttraction.getName() + "' es: "
                      + totalBudget,
                  1);
              break;

            case "Cambiar de Cuenta":
              logOut = true;
              break;
          }
        }
      }
    }

  }

}
