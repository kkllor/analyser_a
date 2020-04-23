package com.kkllor;

import com.kkllor.analysis.Analyser;
import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.impl.SHDownLoader;
import com.sun.org.apache.xpath.internal.functions.Function2Args;

import java.util.Scanner;
import java.util.function.Function;

public class Entrance {

    public static void main(String args[]) {
        Config.getInstance().parse();
        Scanner scanner = new Scanner(System.in);
        String operation = inputCheck(scanner, "input 'download' or 'analyse' for following operations ", "unknown operation", str -> str.equals("download") || str.equals("analyse"));
        if ("download".equals(operation)) {
            String codes = inputCheck(scanner, "input stock codes,split with ',' ", "unknown operation", str -> str.split(",").length > 0);
            String years = inputCheck(scanner, "input download year,split with ',' ", "unknown years", str -> str.split(",").length > 0);
            String[] a1 = years.split(",");
            int[] a2 = new int[a1.length];
            for (int i = 0; i < a1.length; i++) {
                a2[i] = Integer.parseInt(a1[i]);
            }

            String reportType = inputCheck(scanner, "download yearly reports input 'y',or 'q' for quater reports", "unknown years", str -> str.equals("y") || str.equals("q"));
            ReportType reportType1;
            if ("y".equals(reportType)) {
                reportType1 = ReportType.YEAR;
            } else if ("q".equals(reportType)) {
                reportType1 = ReportType.QUARTER;
            } else {
                reportType1 = ReportType.OTHER;
            }
            SHDownLoader.getInstance().downloadByCodes(reportType1, a2, codes.split(","));
            SHDownLoader.getInstance().exit();
        } else if ("analyse".equals(operation)) {
            new Analyser().analyse("600300");
        }
    }


    private static String inputCheck(Scanner scanner, String tips, String failedMsg, StringToBooleanFunction function) {
        String input;
        do {
            System.out.println(tips);
            input = scanner.nextLine();
        } while (!function.operation(input));
        return input;
    }

    interface StringToBooleanFunction {
        boolean operation(String input);
    }

}