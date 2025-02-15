package edu.csu.codemanagesystem;

import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.domain.import_excel.service.ImportServiceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class ImportServiceTest {
    @Autowired
    private ImportServiceFactory importServiceFactory;

//    @Test
//    public void testTeacherImportService()  {
//        IImportExcel teacherImport = importServiceFactory.getImportExcel(ImportServiceFactory.ImportServiceType.TEACHER);
//        Path path = Paths.get("test.xlsx");
//        InputStream inputStream = null;
//        try {
//            inputStream = Files.newInputStream(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        teacherImport.ImportExcel(inputStream);
//
//    }
}
