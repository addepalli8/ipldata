package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        String csvFile = "D:\\Downloads\\iplDataSet\\deliveries.csv";
        String csvFile1 = "D:\\Downloads\\iplDataSet\\matches.csv";
        String line = "";
        String[][] deliveries = new String[150461][16];
        String[][] matches = new String[637][20];
        String[] bowlers = new String[150461];
        String[] teamfielding = new String[97];
        String[] teamrun4 = new String[150461];
        String[] teamrun6 = new String[150461];
        String[] teamname46runs = new String[150461];
        String[] teamnetbat = new String[150461];
        String[] teamnetball = new String[150461];
        String[] finname = new String[150461];
        String[] econball = new String[150461];
        String[] tossfield2016 = new String[100];
        String[] tossfield2017 = new String[100];

        int[] totalruns = new int[150461];
        int[] bowlerruns = new int[150461];
        int[] bowlerlegby = new int[150461];
        int[] bowlerby = new int[150461];
        int[] battingruns = new int[150461];
        int[] bowlingruns = new int[150461];
        int[] countfield2017 = new int[100];
        int[] countfield2016 = new int[100];

        float[] bowlereconomy = new float[150461];
        float[] battingnet = new float[150461];
        float[] bowlingnet = new float[150461];
        float[] netrunrate = new float[150461];

        List<String> economyteam = new ArrayList();
        List<String> fieldtoss1617 = new ArrayList();
        List<String> bowlername = new ArrayList();
        List<String> allbowlers = new ArrayList();
        List<String> battingteamname = new ArrayList();

        Map<String, Integer> tossfield = new HashMap<>();
        Map<String, Integer> team4runs = new HashMap<>();
        Map<String, Integer> team6runs = new HashMap<>();
        Map<String, Integer> teamtotalruns = new HashMap<>();
        Map<String, Integer> bowlerconcededruns = new HashMap<>();
        Map<String, Integer> bowlerballs = new HashMap<>();
        Map<String, Integer> battingecon = new HashMap<>();
        Map<String, Integer> bowlingecon = new HashMap<>();
        Map<String, Integer> ballsfaced = new HashMap<>();
        Map<String, Integer> ballsconceded = new HashMap<>();
        int i = 0, k = 0;

        // Retriving data from the delivery.csv file using try and catch method
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] delivery = line.split(",");
                deliveries[i] = delivery;
                i++;
            }
        } catch (IOException e) {
            System.out.print(e);
        }

        //      Retriving data from the matches.csv file using try and catch method
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) {
            i = 0;
            while ((line = br.readLine()) != null) {
                String[] match = line.split(",");
                matches[i] = match;
                i++;
            }
        } catch (IOException e) {
            System.out.print(e);
        }
        //the retrived data is stored in matches and deliveries array

        //k is used as incrementation counter
        //extracting only the required data by specifying the condition year ,toss , field team and storing in team field
        //teamfield consits of all the data regarding the query1 in the form of array
        //fieldtoss1617 is used to save all the data of teams year 2016 and2017 by removing duplicates
        k = 0;
        for (i = 0; i < matches.length; i++) {
            if (((matches[i][1].equals("2017")) && (matches[i][7].equals("field"))) || (matches[i][1].equals("2016")) && (matches[i][7].equals("field"))) {
                String temp = (matches[i][1] + "\t" + matches[i][6]);

                teamfielding[k] = temp;
                k++;
                if (!fieldtoss1617.contains(temp)) {
                    fieldtoss1617.add(temp);
                }
            }
        }
        //k is used as incrementation counter
        //extracting only the required data by specifying the condition by specifying the year and no of 4's scored
        //teamrun4 consits of all the data regarding the query2 in the form of  list and removes all duplicatevalues
        
            k = 0;
        for (i = 0; i < matches.length; i++) {
            for (int j = 0; j < deliveries.length; j++) {
                if ((deliveries[j][0].equals(matches[i][0])) && ((deliveries[j][13]).equals("4"))) {
                    teamrun4[k] = ((matches[i][1]) + " " + deliveries[j][2]);
                    k++;

                }
            }

        }
          //k is used as incrementation counter
        //extracting only the required data by specifying the condition by specifying the year and no of 6's scored
        //teamrun6 consits of all the data regarding the query2 in the form of array
        k = 0;
        for (i = 0; i < matches.length; i++) {
            for (int j = 0; j < deliveries.length; j++) {
                if ((deliveries[j][0].equals(matches[i][0])) && ((deliveries[j][13]).equals("6"))) {
                    teamrun6[k] = ((matches[i][1]) + " " + deliveries[j][2]);
                    k++;
                }
            }

        }
        
        //k is used as incrementation counter
        //extracting only the required data by specifying the condition by specifying the year and no of runs scored
        //totalruns consits of all the data regarding the query2 in the form of  list and removes all duplicatevalues
        k = 0;
        for (i = 1; i < matches.length; i++) {
            for (int j = 1; j < deliveries.length; j++) {
                if ((deliveries[j][0].equals(matches[i][0]))) {
                    teamname46runs[k] = ((matches[i][1]) + " " + deliveries[j][2]);
                    totalruns[k] = Integer.valueOf(deliveries[j][15]);
                    k++;

                }
            }

        }

        //k is used as incrementation counter
        //extracting only the required data by specifying the condition by specifying the year and total runs,legnye runs,bye runs scored
        //bowlers consits of all the name regarding the query3 in the form of array
        //bowlerruns consits of all the runs conceded regarding the query3 in the form of array
        //bowlerlegby consits of all the legbye runs conceded regarding the query3 in the form of array
        //bowlerby consits of all the bye runs conceded regarding the query3 in the form of  list and removes all duplicatevalues
        k = 0;
        for (i = 1; i < matches.length; i++) {
            for (int j = 1; j < deliveries.length; j++) {
                if ((deliveries[j][0].equals(matches[i][0]))) {
                    bowlers[k] = ((matches[i][1]) + " " + deliveries[j][7]);
                    bowlerruns[k] = Integer.valueOf(deliveries[j][15]);
                    bowlerlegby[k] = Integer.valueOf(deliveries[j][10]);
                    bowlerby[k] = Integer.valueOf(deliveries[j][9]);
                    if (!allbowlers.contains(bowlers[k])) {
                        allbowlers.add(bowlers[k]);
                    }
                    k++;
                }
            }

        }
        //k is used as incrementation counter
        //extracting only the required data by specifying the condition by specifying the year and total runs,legnye runs,bye runs scored
        //teamnetbat consits of all the name regarding the query4 in the form of array
        //teamnetball consits of all the name conceded regarding the query4 in the form of array
        //battingruns consits of all the runs conceded regarding the query4 in the form of array
        //bowlingruns consits of all the balls thrown regarding the query4 in the form of array
        //economyteam consits of all the names conceded regarding the query4 in the form of list and removes all duplicatevalues

        k = 0;
        for (i = 1; i < matches.length; i++) {
            for (int j = 1; j < deliveries.length; j++) {
                if ((deliveries[j][0].equals(matches[i][0]))) {
                    teamnetbat[k] = ((matches[i][1]) + " " + deliveries[j][2]);
                    teamnetball[k] = ((matches[i][1]) + " " + deliveries[j][3]);
                    battingruns[k] = Integer.valueOf(deliveries[j][15]);
                    bowlingruns[k] = Integer.valueOf(deliveries[j][15]);
                    if (!economyteam.contains(teamnetbat[k])) {
                        economyteam.add(teamnetbat[k]);
                    }
                    k++;
                }
            }
        }

        //Retriving only the necessary data to the list to team4runs and thier count
        for (i = 0; i < teamrun4.length; i++) {
            if (team4runs.containsKey(teamrun4[i])) {
                team4runs.put(teamrun4[i], team4runs.get(teamrun4[i]) + 1);
            } else {
                team4runs.put(teamrun4[i], 1);
            }
        }

        //Retriving only the necessary data to the list to team6runs and thier count
        for (i = 0; i < teamrun6.length; i++) {
            if (team6runs.containsKey(teamrun6[i])) {
                team6runs.put(teamrun6[i], team6runs.get(teamrun6[i]) + 1);
            } else {
                team6runs.put(teamrun6[i], 1);
            }
        }
        
        //Retriving only the necessary data to the list to teamtotalruns and adding score
        for (i = 0; i < teamname46runs.length; i++) {
            if (teamtotalruns.containsKey(teamname46runs[i])) {
                teamtotalruns.put(teamname46runs[i], teamtotalruns.get(teamname46runs[i]) + totalruns[i]);
            } else {
                teamtotalruns.put(teamname46runs[i], 1);
            }
        }
        //Retriving only the necessary data to the list to bowlerballs and thier count

        for (i = 0; i < bowlers.length; i++) {
            if (bowlerballs.containsKey(bowlers[i])) {
                bowlerballs.put(bowlers[i], bowlerballs.get(bowlers[i]) + 1);
            } else {
                bowlerballs.put(bowlers[i], 1);
            }
        }
                //Retriving only the necessary data to the list to allbowlers who has more than 10 overs 

        for (i = 0; i < allbowlers.size(); i++) {
            if (bowlerballs.get(allbowlers.get(i)) >= 60) {
                bowlername.add(allbowlers.get(i));
            }
        }
        //Retriving only the necessary data to the list to bowlerconcededruns who has more than 10 overs and finding the conceded runs
        for (i = 0; i < bowlers.length; i++) {
            if (bowlerconcededruns.containsKey(bowlers[i])) {
                bowlerconcededruns.put(bowlers[i], bowlerconcededruns.get(bowlers[i]) + bowlerruns[i] - bowlerlegby[i] - bowlerby[i]);
            } else {
                bowlerconcededruns.put(bowlers[i], 1);
            }
        }
         //Retriving only the necessary data to the list to battingecon and adding score
        for (i = 0; i < teamnetbat.length; i++) {
            if (battingecon.containsKey(teamnetbat[i])) {
                battingecon.put(teamnetbat[i], battingecon.get(teamnetbat[i]) + battingruns[i]);
            } else {
                battingecon.put(teamnetbat[i], 1);
            }
        }
        //Retriving only the necessary data to the list to bowlingecon and adding score
        for (i = 0; i < teamnetball.length; i++) {
            if (bowlingecon.containsKey(teamnetball[i])) {
                bowlingecon.put(teamnetball[i], bowlingecon.get(teamnetball[i]) + bowlingruns[i]);
            } else {
                bowlingecon.put(teamnetball[i], 1);
            }
        }

        //Retriving only the necessary data to the list to ballsfaced and thier count
        for (i = 0; i < teamnetbat.length; i++) {
            if (ballsfaced.containsKey(teamnetbat[i])) {
                ballsfaced.put(teamnetbat[i], ballsfaced.get(teamnetbat[i]) + 1);
            } else {
                ballsfaced.put(teamnetbat[i], 1);
            }
        }
        //Retriving only the necessary data to the list to ballsconceded and thier count
        for (i = 0; i < teamnetball.length; i++) {
            if (ballsconceded.containsKey(teamnetball[i])) {
                ballsconceded.put(teamnetball[i], ballsconceded.get(teamnetball[i]) + 1);
            } else {
                ballsconceded.put(teamnetball[i], 1);
            }
        }

        team6runs.remove(null);
        teamtotalruns.remove(null);
        team4runs.remove(null);
        //Retriving only the necessary data to the list to teamfielding and thier count
        for (i = 0; i < teamfielding.length; i++) {
            if (tossfield.containsKey(teamfielding[i])) {
                tossfield.put(teamfielding[i], tossfield.get(teamfielding[i]) + 1);
            } else {
                tossfield.put(teamfielding[i], 1);
            }
        }

        //sending all 2016 and 2017 data values to the array tossfield2016
        for (i = 0; i < fieldtoss1617.size(); i++) {
            if ((fieldtoss1617.get(i)).contains("2016")) {
                tossfield2016[i] = fieldtoss1617.get(i);
                countfield2016[i] = tossfield.get(fieldtoss1617.get(i));
            }
            if ((fieldtoss1617.get(i)).contains("2017")) {
                tossfield2017[i] = fieldtoss1617.get(i);
                countfield2017[i] = tossfield.get(fieldtoss1617.get(i));
            }
        }

        //sorting all the values to find top 4 teams  of data and name to map
        for (i = 0; i < tossfield2016.length; i++) {

            for (int j = 0; j < tossfield2016.length; j++) {
                if (countfield2016[i] > countfield2016[j]) {
                    int temp = countfield2016[j];
                    countfield2016[j] = countfield2016[i];
                    countfield2016[i] = temp;
                    String temp1 = tossfield2016[j];
                    tossfield2016[j] = tossfield2016[i];
                    tossfield2016[i] = temp1;

                }

            }
        }
        //sorting all the values to find top 4 teams  of data and name to map
        for (i = 0; i < tossfield2017.length; i++) {

            for (int j = 0; j < tossfield2017.length; j++) {
                if (countfield2017[i] > countfield2017[j]) {
                    int temp = countfield2017[j];
                    countfield2017[j] = countfield2017[i];
                    countfield2017[i] = temp;
                    String temp1 = tossfield2017[j];
                    tossfield2017[j] = tossfield2017[i];
                    tossfield2017[i] = temp1;

                }

            }
        }

        //printing only the top 4 values in 2016 and 2017
        System.out.print("***** FIRST QUESTION *****\n\n");
        for (i = 0; i < 4; i++) {
            System.out.print(tossfield2016[i] + " - " + countfield2016[i] + "\t|\t");
            System.out.print(tossfield2017[i] + " - " + countfield2017[i] + "\n");
        }

        //printing all the values of all teams in all years with no 4's no 6's and total runs
        System.out.print("\n***** SECOND QUESTION *****\n\n");
        Set<String> keys = team4runs.keySet();
        for (String key : keys) {
            battingteamname.add(key);
        }
        for (i = 0; i < team4runs.size(); i++) {

            System.out.print("YEAR & TEAM: " + battingteamname.get(i) + "\t No 4'S:  " + team4runs.get(battingteamname.get(i)) + "\t No 6'S: " + team6runs.get(battingteamname.get(i)) + "\t TOTAL RUNS: " + teamtotalruns.get(battingteamname.get(i)) + "\n");
        }

        System.out.print("\n***** THIRD    QUESTION *****\n");
        //finding the bowlers w.r.t each year and printing them
        for (int year = 2008; year <= 2017; year++) {
            k = 0;
            for (i = 0; i < bowlername.size(); i++) {
                if (bowlername.get(i).contains(year + "")) {
                    econball[k] = (bowlername.get(i));
                    bowlereconomy[k] = (float) (bowlerconcededruns.get(bowlername.get(i))) / (int) (bowlerballs.get(bowlername.get(i)) / 6);
                    k++;
                }
            }
           
            //sorting all the values of data and name to map
            for (i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (bowlereconomy[i] > bowlereconomy[j]) {
                        float temp = bowlereconomy[j];
                        bowlereconomy[j] = bowlereconomy[i];
                        bowlereconomy[i] = temp;

                        String temp1 = econball[j];
                        econball[j] = econball[i];
                        econball[i] = temp1;

                    }
                }
            }
            System.out.print("\n******" + year + "******\n");
            for (i = 0; i < 10; i++) {
                k--;
                System.out.print("YEAR: " + econball[k] + " = " + bowlereconomy[k] + " \n");

            }
        }
        System.out.print("\n***** FOURTH    QUESTION *****\n\n");
        //finding the teams w.r.t each year and printing them
        for (int year = 2008; year <= 2017; year++) {
            k = 0;
            for (i = 0; i < economyteam.size(); i++) {
                if (economyteam.get(i).contains(year + "")) {
                    battingnet[k] = ((float) (battingecon.get(economyteam.get(i))) / (ballsfaced.get(economyteam.get(i)) / 6));
                    bowlingnet[k] = ((float) (bowlingecon.get(economyteam.get(i))) / (ballsconceded.get(economyteam.get(i)) / 6));
                    netrunrate[k] = (battingnet[k] - bowlingnet[k]);
                    finname[k] = economyteam.get(i);
                    k++;
                }
            }
            //sorting all the values  of data and name to map
            for (i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (netrunrate[i] > netrunrate[j]) {
                        float temp = netrunrate[j];
                        netrunrate[j] = netrunrate[i];
                        netrunrate[i] = temp;

                        String temp1 = finname[j];
                        finname[j] = finname[i];
                        finname[i] = temp1;

                    }
                }
            }

            System.out.print(finname[0] + " = " + netrunrate[0] + "\n");
        }

    }
}
