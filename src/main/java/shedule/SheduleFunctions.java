package shedule;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class SheduleFunctions {
    public static String getTodayShedule(String group) throws IOException {

        FileInputStream file = new FileInputStream(new File(Constants.fileShedule));
        Workbook workbook = new XSSFWorkbook(file);

        String data="";

        LocalDateTime ldt = LocalDateTime.now();

        Sheet sheet = workbook.getSheetAt(0);
        int i;

        Row row1 = null;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getRichStringCellValue().getString().equals(Utils.getTrueDateFormat(ldt))){
                    row1 = row;
                }
            }
        }
        i=0;
        int groupInt;
        groupInt = Integer.parseInt(group) + 2;
        if (row1==null){
            data = "Отдых:)";
            return data;
        }
        else {
            for (Cell cell : row1) {
                i++;
                if ((i == 3) & (groupInt == 3)) {
                    data = cell.getRichStringCellValue().getString();
                }
                if ((i == 4) & (groupInt == 4)) {
                    data = cell.getRichStringCellValue().getString();
                }
                if ((i == 5) & (groupInt == 5)) {
                    data = cell.getRichStringCellValue().getString();
                }
                if ((i == 6) & (groupInt == 6)) {
                    data = cell.getRichStringCellValue().getString();
                }
            }
            return data;
        }
    }
    public static String getNextShedule(String group) throws IOException {

        FileInputStream file = new FileInputStream(new File(Constants.fileShedule));
        Workbook workbook = new XSSFWorkbook(file);

        String data="";

        LocalDateTime ldt = LocalDateTime.now();

        Sheet sheet = workbook.getSheetAt(0);
        int i;

        Row row1 = null;
        for(i = 0; i < 3;i++){
            if(row1 == null){
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getRichStringCellValue().getString().equals(Utils.getTrueDateFormat(ldt))){
                            row1 = row;
                        }
                    }
                }
            }
            else {
                break;
            }
            ldt = ldt.plusDays(1);
        }
        i=0;
        int groupInt;
        groupInt = Integer.parseInt(group) + 2;
        for (Cell cell : row1) {
            i++;
            if ((i == 3) & (groupInt == 3)) {
                data = cell.getRichStringCellValue().getString();
            }
            if ((i == 4) & (groupInt == 4)) {
                data = cell.getRichStringCellValue().getString();
            }
            if ((i == 5) & (groupInt == 5)) {
                data = cell.getRichStringCellValue().getString();
            }
            if ((i == 6) & (groupInt == 6)) {
                data = cell.getRichStringCellValue().getString();
            }
        }

        String dateLesson = Utils.getTrueDateFormat(ldt);
        dateLesson = dateLesson.replaceAll("(\\r|\\n)", " ");

        data = data + ", " + dateLesson;

        return data;
    }

    public static String getTodayWeekShedule(String group) throws IOException {

        FileInputStream file = new FileInputStream(new File(Constants.fileShedule));
        Workbook workbook = new XSSFWorkbook(file);

        String[] data = new String[3];

        String[] todayWeekDate = Utils.initWeek();

        Sheet sheet = workbook.getSheetAt(0);
        int i;

        Row[] row1 = new Row[3];
        for(i = 0; i < 3;i++){
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getRichStringCellValue().getString().equals(todayWeekDate[i])){
                        row1[i] = row;
                    }
                }
            }
        }
        i=0;
        int groupInt;
        groupInt = Integer.parseInt(group) + 2;
        for (int j=0;j<3;j++){
            for (Cell cell : row1[j]) {
                i++;
                if ((i == 3) & (groupInt == 3)) {
                    data[j] = cell.getRichStringCellValue().getString();
                }
                if ((i == 4) & (groupInt == 4)) {
                    data[j] = cell.getRichStringCellValue().getString();
                }
                if ((i == 5) & (groupInt == 5)) {
                    data[j] = cell.getRichStringCellValue().getString();
                }
                if ((i == 6) & (groupInt == 6)) {
                    data[j] = cell.getRichStringCellValue().getString();
                }
            }
            i=0;
        }
        String shedule = "";

        for (int w = 0; w <= 2; w++)
        {
            int l = w+1;
            todayWeekDate[w] = todayWeekDate[w].replaceAll("(\\r|\\n)", " ");
            shedule+= l + ". " + data[w] + ", " + todayWeekDate[w]  +"\n";
        }


        return shedule;
    }
}
