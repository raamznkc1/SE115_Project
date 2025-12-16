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
        if (month < 0 || month >= MONTHS) {
            return -1;
        }

        long maxProfit = Long.MIN_VALUE;
        int bestDayNumber = -1;

        for (int day = 1; day <= DAYS; day++) {

            int dayIndex = day - 1;
            long currentDayProfit = 0;

            for (int commIndex = 0; commIndex < COMMS; commIndex++) {
                currentDayProfit += profitData[month][dayIndex][commIndex];
            }

            if (currentDayProfit > maxProfit) {
                maxProfit = currentDayProfit;
                bestDayNumber = day;
            }
        }

        return bestDayNumber;
    }

    public static String bestMonthForCommodity(String comm ) {
        int commIndex = -1;
        for (int i = 0; i < commodities.length; i++) {
            if (commodities[i].equals(comm)) {
                commIndex = i;
                break;
            }
        }

        if (commIndex == -1) {
            return "INVALID_COMMODITY";
        }

        long maxProfit = Long.MIN_VALUE;
        String bestMonthName = "";

        for (int monthIndex = 0; monthIndex < MONTHS; monthIndex++) {
            long currentTotalProfit = 0;

            for (int dayIndex = 0; dayIndex < DAYS; dayIndex++) {
                currentTotalProfit += profitData[monthIndex][dayIndex][commIndex];
            }

            if (currentTotalProfit > maxProfit) {
                maxProfit = currentTotalProfit;
                bestMonthName = months[monthIndex];
            }
        }

        return bestMonthName;
    }

    public static int consecutiveLossDays(String comm ) {
        int commIndex = -1;
        for (int i = 0; i < commodities.length; i++) {
            if (commodities[i].equals(comm)) {
                commIndex = i;
                break;
            }
        }

        if (commIndex == -1) {
            return -1;
        }

        int maxLossStreak = 0;
        int currentLossStreak = 0;

        for (int monthIndex = 0; monthIndex < MONTHS; monthIndex++) {
            for (int dayIndex = 0; dayIndex < DAYS; dayIndex++) {

                int profit = profitData[monthIndex][dayIndex][commIndex];

                if (profit < 0) {
                    currentLossStreak++;
                } else {
                    currentLossStreak = 0;
                }

                if (currentLossStreak > maxLossStreak) {
                    maxLossStreak = currentLossStreak;
                }
            }
        }

        return maxLossStreak;
    }

    public static int daysAboveThreshold(String comm , int threshold) {
        int commIndex = -1;
        for (int i = 0; i < commodities.length; i++) {
            if (commodities[i].equals(comm)) {
                commIndex = i;
                break;
            }
        }

        if (commIndex == -1) {
            return -1;
        }

        int daysCount = 0;

        for (int monthIndex = 0; monthIndex < MONTHS; monthIndex++) {
            for (int dayIndex = 0; dayIndex < DAYS; dayIndex++) {

                int profit = profitData[monthIndex][dayIndex][commIndex];

                if (profit > threshold) {
                    daysCount++;
                }
            }
        }

        return daysCount;
    }

    public static int biggestDailySwing(int month) {
        if (month < 0 || month >= MONTHS) {
            return -1;
        }

        long maxSwing = 0;
        long previousDayTotalProfit = 0;

        for (int commIndex = 0; commIndex < COMMS; commIndex++) {
            previousDayTotalProfit += profitData[month][0][commIndex];
        }

        for (int dayIndex = 1; dayIndex < DAYS; dayIndex++) {

            long currentDayTotalProfit = 0;

            for (int commIndex = 0; commIndex < COMMS; commIndex++) {
                currentDayTotalProfit += profitData[month][dayIndex][commIndex];
            }

            long swing = Math.abs(currentDayTotalProfit - previousDayTotalProfit);

            if (swing > maxSwing) {
                maxSwing = swing;
            }

            previousDayTotalProfit = currentDayTotalProfit;
        }

        return (int) maxSwing;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        int c1Index = -1;
        for (int i = 0; i < commodities.length; i++) {
            if (commodities[i].equals(c1)) {
                c1Index = i;
                break;
            }
        }
        if (c1Index == -1) {
            return "INVALID_COMMODITY";
        }

        int c2Index = -1;
        for (int i = 0; i < commodities.length; i++) {
            if (commodities[i].equals(c2)) {
                c2Index = i;
                break;
            }
        }
        if (c2Index == -1) {
            return "INVALID_COMMODITY";
        }

        long c1TotalProfit = 0;
        long c2TotalProfit = 0;

        for (int monthIndex = 0; monthIndex < MONTHS; monthIndex++) {
            for (int dayIndex = 0; dayIndex < DAYS; dayIndex++) {
                c1TotalProfit += profitData[monthIndex][dayIndex][c1Index];
                c2TotalProfit += profitData[monthIndex][dayIndex][c2Index];
            }
        }

        long difference = Math.abs(c1TotalProfit - c2TotalProfit);

        if (c1TotalProfit > c2TotalProfit) {
            return c1 + " is better by " + difference;
        } else if (c2TotalProfit > c1TotalProfit) {
            return c2 + " is better by " + difference;
        } else {
            return "Equal";
        }
    }

    public static String bestWeekOfMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        long maxProfit = Long.MIN_VALUE;
        String bestWeekName = "";

        int[] startDays = {1, 8, 15, 22};
        int numberOfWeeks = 4;

        for (int week = 0; week < numberOfWeeks; week++) {

            long currentWeekProfit = 0;

            int startDay = startDays[week];
            int endDay = startDay + 6;

            for (int day = startDay; day <= endDay; day++) {

                int dayIndex = day - 1;

                for (int commIndex = 0; commIndex < COMMS; commIndex++) {
                    currentWeekProfit += profitData[month][dayIndex][commIndex];
                }
            }

            if (currentWeekProfit > maxProfit) {
                maxProfit = currentWeekProfit;
                bestWeekName = "Week " + (week + 1);
            }
        }

        return bestWeekName;
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}