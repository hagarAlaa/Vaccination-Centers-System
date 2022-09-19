package Shaza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shatha
 */
public class System {

    static ArrayList<Center> center = new ArrayList<>();
    static ArrayList<Integer> cap = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        File file1 = new File("intialInformation.txt");
        File file2 = new File("commands.txt");

        if (!file1.exists()) {
            throw new FileNotFoundException("file is not exist");
        }

        if (!file2.exists()) {
            throw new FileNotFoundException("file is not exist");
        }
        // For print.
        int count = 0;
        ArrayList<Center> centerNewList = new ArrayList<>();
        Scanner input1 = new Scanner(file1);
        Scanner input2 = new Scanner(file2);

        File outputfile = new File("output.txt");

        PrintWriter output = new PrintWriter(outputfile);

        output.println("		Welcome to the KAU Vaccination Centers Management System");
        output.println();
        while (input2.hasNext()) {

            String command = input2.next();

            if ("STARTUP".equalsIgnoreCase(command)) {
                // int CentereNum = input1.nextInt();
                STARTUP(input1, output);
            } else if ("DISPLAY_ALL_CENTERS".equalsIgnoreCase(command)) {
                output.println("The first distribution for health practitioners among the vaccination centers ");
                DISPLAY_ALL_CENTERS(output);

            } else if ("NUM_PRACTIONERS".equalsIgnoreCase(command)) {

                NUM_PRACTIONERS(output, input2);
            } else if ("DISPLAY".equals(command)) {
                int CenterNumber = input2.nextInt();
                output.println("	The pracitioners of center " + CenterNumber + "are");
                output.println("	-----------------------------------");
                output.println();
                output.println("                         " + names.get(CenterNumber - 1));
                output.println();
                output.println("---------------------------------");
                DISPLAY(output, CenterNumber);
            } else if ("DISPLAY_ALL_BASED_ON_STATUS".equals(command)) {
                DISPLAY_ALL_BASED_ON_STATUS(output, input2);
            } else if ("DISPLAY_BASED_ON_STATUS".equals(command)) {
                DISPLAY_BASED_ON_STATUS(output, input2);
            } else if ("LEAVE_THE_JOB".equals(command)) {
                LEAVE_THE_JOB(output, input2);
            } else if ("REMOVE_ALL_LEFT_PRACTITIONERS".equals(command)) {
                REMOVE_ALL_LEFT_PRACTITIONERS(output, input2);
            } else if ("MOVE".equals(command)) {
                MOVE(output, input2);
            } else if ("DELETE_CENTER".equals(command)) {
                DELETE_CENTER(output, input2);
            } else if ("MERGE".equalsIgnoreCase(command)) {
                centerNewList = MERGE(output, centerNewList);
                //DISPLAY(output, 1);

            } else if ("QUIT".equals(command)) {
                QUIT(output);
            }
            //  output.println(" ");
            //output.flush();

        }

    }

    public static void STARTUP(Scanner input1, PrintWriter output) {
        int CentereNum = input1.nextInt();
        for (int i = 0; i < CentereNum; i++) {
            int number = input1.nextInt();
            Center Centerr = new Center(i + 1);
            center.add(Centerr);
            cap.add(number);
        }

        output.println("The centers for this semester are:");
        for (int i = 0; i < CentereNum; i++) {
            String ceName = input1.next();
            names.add(ceName);
            output.println(" " + ceName + " ");
        }

        for (int i = 0; i < CentereNum; i++) {
            for (int j = 0; j < cap.get(i); j++) {

                String Id = input1.next();

                String Fname = input1.next();

                String Lname = input1.next();

                Practitioner Practitionerr = new Practitioner(Id, Fname, Lname, "Exist", i + 1);
                center.get(i).addPractitioner(Practitionerr);

            }
        }
        output.println();

    }

    public static void DISPLAY_ALL_CENTERS(PrintWriter output) {

        output.println();
        output.println("===================================================================================================");
        output.println("      " + names.get(0) + "		  Main Hospital		   Medical Service Center");
        output.println();
        output.println("--------------------------------------------------------------------------------------------------");

        output.println();
        Practitioner practitioner = null;
        Practitioner centerone = center.get(0).getHead();
        Practitioner centertwo = center.get(1).getHead();
        Practitioner centerthree = center.get(2).getHead();

        while (centerone != null || centertwo != null || centerthree != null) {
            if (centerone != null) {
                output.printf("%5s", centerone.toString() + "    ");
                // output.print(centerone.toString());
                centerone = centerone.getnext();
            }
            if (centertwo != null) {
                // output.print("           " + centertwo.toString());
                output.printf("%25s", centertwo.toString() + "		  ");
                centertwo = centertwo.getnext();
            }
            if (centerthree != null) {
                output.printf("%25s", centerthree.toString() + "		  ");

                //output.print("           " + centerthree.toString());
                centerthree = centerthree.getnext();
            }
            output.println();
        }

        output.println("===================================================================================================");

    }

    public static void NUM_PRACTIONERS(PrintWriter output, Scanner input) {

        int CenterNumber = input.nextInt();
        Practitioner helpPte = center.get(CenterNumber - 1).getHead();
        int numPractitioner = 0;
        while (helpPte != null) {
            numPractitioner++;
            helpPte = helpPte.getnext();
        }
        output.println("Number of practitioners in center " + CenterNumber + ":" + numPractitioner);
        output.print("===================================================================================================");
        output.println();
    }

    public static void DISPLAY_ALL_BASED_ON_STATUS(PrintWriter output, Scanner input2) {
        String stu = input2.next();
        int found = 0;
        Practitioner centerone = center.get(0).getHead();
        Practitioner centertwo = center.get(1).getHead();
        Practitioner centerthree = center.get(2).getHead();
        output.println("");
        while (centerone != null || centertwo != null || centerthree != null) {
            if (centerone != null) {
                if (centerone.getStatus().equals(stu)) {
                    output.println("");
                    output.print(centerone.toString());
                    found++;
                }
                centerone = centerone.getnext();
            }

            if (centertwo != null) {
                if (centertwo.getStatus().equals(stu)) {
                    output.print("           " + centertwo.toString());
                    found++;
                }
                centertwo = centertwo.getnext();
            }
            if (centerthree != null) {
                if (centerthree.getStatus().equals(stu)) {
                    output.print("           " + centerthree.toString());
                    found++;
                }
                centerthree = centerthree.getnext();
            }
        }

        if (found == 0) {
            output.print("\nNot found any practitioners of the status " + stu);

        }
        output.println("");
        output.println("===================================================================================================");

    }

    public static void DISPLAY_BASED_ON_STATUS(PrintWriter output, Scanner input2) {
        String stu = input2.next();
        int number = input2.nextInt();
        int yes = 0;
        Practitioner practitioner = center.get(number - 1).getHead();
        while (practitioner != null) {
            if (practitioner.getStatus().equals(stu)) {
                yes++;
                if (yes == 1) {
                    output.println("	The practitioners of status Exist in center " + number
                            + " are\n         -------------------------------------------------\n\n      			 "
                            + names.get(number - 1));
                    output.println("-----------------------------------------------------");
                }
                output.println(practitioner.toString());
            }
            practitioner = practitioner.getnext();
        }

        if (yes == 0) {
            output.println("\nNot found any practitioners of the status " + stu + " in center " + number);
        }
        output.println("============================"
                + "=======================================================================");

    }

    public static void LEAVE_THE_JOB(PrintWriter output, Scanner input2) {
        String id = input2.next();
        for (int i = 0; i < center.size(); i++) {
            Practitioner practitioner = center.get(i).getHead();
            while (practitioner != null) {
                if (practitioner.getparctID().equals(id)) {
                    output.println("\nThe practitioner of  " + id + " is left\n");
                    output.println("	The practitioners of center " + center.get(i).getcenterID() + " are\n"
                            + "        -------------------------------------------------\n"
                            + " \n"
                            + "      			 " + names.get(practitioner.getCenter() - 1) + "		  		   	\n"
                            + "\n"
                            + "-----------------------------------------------------");
                    practitioner.setStatus("Left");
                    DISPLAY(output, center.get(i).getcenterID());

                }
                practitioner = practitioner.getnext();
            }
        }
    }

    public static void DISPLAY(PrintWriter output, int CenterNumber) {

        Practitioner centerNum = center.get(CenterNumber - 1).getHead();
        while (centerNum != null) {
            output.println("             " + centerNum.toString());
            centerNum = centerNum.getnext();
        }
    }

    public static void REMOVE_ALL_LEFT_PRACTITIONERS(PrintWriter output, Scanner input2) {

        output.println("All left Practitioners are moved to new linked list\n");
        for (int i = 0; i < center.size(); i++) {
            center.get(i).deletePractitionersBasedOnStatus("Left");
        }
        output.println("The remaining practitioners After remove the practitioners of status left");
        DISPLAY_ALL_CENTERS(output);
    }

    public static void MOVE(PrintWriter output, Scanner input2) {
        String id = input2.next();
        int number = input2.nextInt();
        for (int i = 0; i < center.size(); i++) {
            Practitioner practitioner = center.get(i).getHead();
            while (practitioner != null) {
                if (practitioner.getparctID().equals(id)) {
                    output.println("The practitioner of  " + id + " is moved to center" + number + "\n");
                    center.get(i).deletePractitionerById(id);
                    practitioner.setStatus("Moved ");
                    center.get(number - 1).addPractitioner(practitioner);
                    DISPLAY_ALL_CENTERS(output);
                    return;
                }
                practitioner = practitioner.getnext();
            }
        }
    }

    public static void DELETE_CENTER(PrintWriter output, Scanner input2) {

        int number = input2.nextInt();
        center.get(number - 1).setHead(null);
        output.println("  			Center " + number + " is Closed");
        output.println("===================================================================================================\n");
    }

    public static ArrayList<Center> MERGE(PrintWriter output, ArrayList<Center> centerNewList) {
        Center Centerr = new Center();
        for (int i = 0; i < center.size(); i++) {
            if (center.get(i) != null) {
                centerNewList.add(center.get(i));
            }
        }
        output.println("===================================================================================================\n");
        output.println("                " + "The remaining sections have been merged\n");
        output.println("--------------------------------------------------------------------------------------------------");
        DISPLAY(output, 2);
        DISPLAY(output, 3);
        return centerNewList;
    }

    public static void QUIT(PrintWriter output) {
        output.println();
        output.println("			-------------------------------------");
        output.println("	   	       |	     Good Bye                 |");
        output.println("                       -------------------------------------           ");
        output.println();
        output.flush();
        output.close();
        System.exit(0);
    }

}
