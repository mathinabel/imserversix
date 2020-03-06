package com.quyuanjin.imserver.test;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Sheet {

    public static void main(String[] args) throws Exception {

       // System.out.println(autoAnswer("笨蛋"));
      /*    try {
            //创建工作簿
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\Administrator\\Desktop\\AndroidAi\\1616.xlsx"));
        //    System.out.println("xssfWorkbook对象：" + xssfWorkbook);
            //读取第一个工作表(这里的下标与list一样的，从0开始取，之后的也是如此)
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
      //      System.out.println("sheet对象：" + sheet);
            //获取第一行的数据
            XSSFRow row = sheet.getRow(0);
      //      System.out.println("row对象：" + row);
            //获取该行第一个单元格的数据
            XSSFCell cell0 = row.getCell(0);
            System.out.println("cello对象：" + cell0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //创建工作簿
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("C:\\\\Users\\\\Administrator\\\\Desktop\\\\AndroidAi\\\\1616.xlsx"));
            //   System.out.println("xssfWorkbook对象：" + xssfWorkbook);
            //读取第一个工作表
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            //  System.out.println("sheet对象：" + sheet);
            //获取最后一行的num，即总行数。此处从0开始计数
            int maxRow = sheet.getLastRowNum();
            //    System.out.println("总行数为：" + maxRow);

            ArrayList<String> stringArrayList = new ArrayList<>();
            for (int row = 0; row <= maxRow; row++) {

                if (sheet.getRow(row) != null) {

                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getRow(row).getLastCellNum();
              System.out.println("--------第" + row + "行的数据如下--------");
               for (int rol = 0; rol < maxRol; rol++){
                    System.out.print(sheet.getRow(row).getCell(rol) + "  ");
               }
                 System.out.println();
                    //获取到第一行之后，应该将这个行第一个单元格与消息作比较，如果相似，则取该行第二个数据
                    if (maxRol > 1) {
                        if (sheet.getRow(row).getCell(0) != null) {
                            if (sheet.getRow(row).getCell(0).toString().equals("笨蛋")) {
                                //  System.out.println(sheet.getRow(row).getCell(1));
                                stringArrayList.add(sheet.getRow(row).getCell(1).toString());
                            }
                        }

                    }
                }
            }
            if (stringArrayList.size()>0){
                Random r = new Random(1);
                int ran1 = r.nextInt(stringArrayList.size());
                System.out.println(stringArrayList.get(ran1));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private static String PATH = "File/sheet/robot/";

    public static String autoAnswer(String questron) {

        XSSFWorkbook xssfWorkbook = null;
        try {
            Path directory = Paths.get(PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);

            }
            xssfWorkbook = new XSSFWorkbook(new FileInputStream(PATH + "1616.xlsx"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int maxRow = sheet.getLastRowNum();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int row = 0; row <= maxRow; row++) {
            if (sheet.getRow(row) != null) {
                int maxRol = sheet.getRow(row).getLastCellNum();
                if (maxRol > 1) {
                    if (sheet.getRow(row).getCell(0) != null) {

                        if (questron.contains(sheet.getRow(row).getCell(0).toString())) {
                            //  System.out.println(sheet.getRow(row).getCell(1));
                            stringArrayList.add(sheet.getRow(row).getCell(1).toString());
                        }
                    }
                }
            }
        }
        if (stringArrayList.size() > 0) {
            Random r = new Random();
            int ran1 = r.nextInt(stringArrayList.size());
            //  System.out.println(stringArrayList.get(ran1));
            return stringArrayList.get(ran1);
        }
        return "我听不懂你在说什么";
    }
}
