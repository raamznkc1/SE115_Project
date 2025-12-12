// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};


    public static int[][][] profitData = new int[MONTHS][DAYS][COMMS];

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {

        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        long maxProfit = Long.MIN_VALUE;
        String bestCommodity = "";

        for (int commIndex = 0; commIndex < COMMS; commIndex++) {
            long currentTotalProfit = 0;

            for (int dayIndex = 0; dayIndex < DAYS; dayIndex++) {
                currentTotalProfit += profitData[month][dayIndex][commIndex];
            }

            if (currentTotalProfit > maxProfit) {
                maxProfit = currentTotalProfit;
                bestCommodity = commodities[commIndex];
            }
        }
        return bestCommodity + " " + maxProfit;
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }

        int dayIndex = day - 1;
        long totalProfit = 0;

        for (int comm = 0; comm < COMMS; comm++) {
            totalProfit += profitData[month][dayIndex][comm];
        }

        return (int) totalProfit;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        long totalProfit = 0;

        if (from < 1 || from > DAYS || to < 1 || to > DAYS || from > to) {
            return -99999;
        }

        int commIndex = -1;
        for (int i = 0; i < commodities.length; i++) {
            if (commodities[i].equals(commodity)) {
                commIndex = i;
                break;
            }
        }

        if (commIndex == -1) {
            return -99999;
        }

        for (int monthIndex = 0; monthIndex < MONTHS; monthIndex++) {
            for (int day = from; day <= to; day++) {
                int dayIndex = day - 1;
                totalProfit += profitData[monthIndex][dayIndex][commIndex];
            }
        }

        return (int) totalProfit;
    }

    public static int bestDayOfMonth(int month) {
        return 1234;
    }

    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}