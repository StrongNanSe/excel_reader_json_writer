import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Read {
    private static final Logger LOGGER = LogManager.getLogger(Read.class);

    public static final Map<String, LicenseValue> LICENSE_VALUE_MAP = new HashMap<>();

    public static void readExcel() {
        String excelPath = findExcel(System.getProperty("user.dir"));
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(excelPath);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

            int rowIndex;
            int cellIndex;

            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            int rows = xssfSheet.getPhysicalNumberOfRows();

            for (rowIndex = 2; rowIndex < rows; rowIndex++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowIndex);

                LicenseValue licenseValue = new LicenseValue();
                String name = "";

                if (xssfRow != null) {
                    int cells = xssfRow.getPhysicalNumberOfCells();
                    for (cellIndex = 0; cellIndex <= cells; cellIndex++) {
                        XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                        String cellValue = "";

                        if (xssfRow.getCell(2) == null) {
                            break;
                        }

                        if (xssfCell != null) {
                            switch (xssfCell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    cellValue = xssfCell.getCellFormula();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    cellValue = xssfCell.getNumericCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    cellValue = xssfCell.getStringCellValue();
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    cellValue = xssfCell.getBooleanCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    cellValue = xssfCell.getErrorCellString();
                                    break;
                            }

                            if (cellIndex == 0) {
                                licenseValue.setName(cellValue);
                                name = cellValue;
                            } else if (cellIndex == 1) {
                                licenseValue.setIdentifier(cellValue);
                            } else if (cellIndex == 2) {
                                licenseValue.setC1(cellValue);
                            } else if (cellIndex == 3) {
                                licenseValue.setC2(cellValue);
                            } else if (cellIndex == 4) {
                                licenseValue.setC3(cellValue);
                            } else if (cellIndex == 5) {
                                licenseValue.setC4(cellValue);
                            } else if (cellIndex == 6) {
                                licenseValue.setC5(cellValue);
                            } else if (cellIndex == 7) {
                                licenseValue.setC6(cellValue);
                            } else if (cellIndex == 8) {
                                licenseValue.setC7(cellValue);
                            } else if (cellIndex == 9) {
                                licenseValue.setC8(cellValue);
                            } else if (cellIndex == 10) {
                                licenseValue.setC9(cellValue);
                            } else if (cellIndex == 11) {
                                licenseValue.setC10(cellValue);
                            } else if (cellIndex == 12) {
                                licenseValue.setC11(cellValue);
                            } else if (cellIndex == 13) {
                                licenseValue.setC12(cellValue);
                            } else if (cellIndex == 14) {
                                licenseValue.setC13(cellValue);
                            }

                        }
                    }
                    LICENSE_VALUE_MAP.put(name, licenseValue);
                }
            }

//            System.out.println("!!!");
//
//            int i = 1;
//            for (String key : LICENSE_VALUE_MAP.keySet()) {
//                LicenseValue licenseValue1 = LICENSE_VALUE_MAP.get(key);
//                System.out.println(i + ". " + key + " : " + licenseValue1);
//                i ++;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String findExcel(String dirPath) {
        File file = new File(dirPath);
        FileFilter filter = f -> f.getName().endsWith("xlsx");
        File[] files = file.listFiles(filter);

        return Objects.requireNonNull(files)[0].toString();
    }
}
