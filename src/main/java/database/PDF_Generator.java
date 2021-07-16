package database;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.sql.ResultSet;
import java.sql.Statement;
public class PDF_Generator{
     public static void generatePDF() throws Exception{
          Statement statement = Database.initialize_database();
          //gets the names and surnames from a database
          ResultSet resultSet=statement.executeQuery("select * from namelist");
          String names="\n";
          while(resultSet.next())
          {
               names+=resultSet.getString("Name")+" ";
               names+=resultSet.getString("Surname")+"\n";

          }
          String FILE = "C:\\Discord_Practica\\src\\main\\java\\database\\Name_List.pdf";
          PdfWriter writer = new PdfWriter(FILE);
          PdfDocument pdfDoc = new PdfDocument(writer);
          pdfDoc.addNewPage();
          Document document = new Document(pdfDoc);
          Paragraph p=new Paragraph().add(names);
          document.add(p);
          document.close();
     }
}
