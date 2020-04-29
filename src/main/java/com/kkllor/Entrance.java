package com.kkllor;

import com.kkllor.analysis.Analyser;
import com.kkllor.config.Config;
import com.kkllor.constants.ReportType;
import com.kkllor.download.impl.SHDownLoader;
import com.kkllor.download.impl.SZDownLoader;

import java.util.*;

public class Entrance {

    public static void main(String args[]) {
        Config.getInstance().parse();
        Scanner scanner = new Scanner(System.in);
        String operation = inputCheck(scanner, "input 'download' or 'analyse' ,'collect' for following operations ", "unknown operation", str -> str.equals("download") || str.equals("analyse") || str.equals("collect"));
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

            Map<String, String[]> codesMap = groupCodes(codes.split(","));
            if (codesMap.get("sh").length > 0) {
                SHDownLoader.getInstance().downloadByCodes(reportType1, a2, codesMap.get("sh"));
                SHDownLoader.getInstance().exit();
            }

            if (codesMap.get("sz").length > 0) {
                SZDownLoader.getInstance().downloadByCodes(reportType1, a2, codesMap.get("sz"));
                SZDownLoader.getInstance().exit();
            }

        } else if ("collect".equals(operation)) {
            String code = inputCheck(scanner, "input stock code ", "unknown operation", str -> str.split(",").length > 0);
            new Analyser().collectData(code);
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


    private static Map<String, String[]> groupCodes(String[] codes) {
        List<String> shList = new ArrayList<>();
        List<String> szList = new ArrayList<>();
        Map<String, String[]> resultMap = new HashMap<>();

        for (String code : codes) {
            if (code.startsWith("00")) {
                szList.add(code);
            } else if (code.startsWith("60")) {
                shList.add(code);
            }
        }
        String[] shCodes = new String[shList.size()];
        for (int i = 0; i < shCodes.length; i++) {
            shCodes[i] = shList.get(i);
        }

        String[] szCodes = new String[szList.size()];
        for (int i = 0; i < szCodes.length; i++) {
            szCodes[i] = szList.get(i);
        }
        resultMap.put("sh", shCodes);
        resultMap.put("sz", szCodes);
        return resultMap;
    }
}