package pdfGenerator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdfGenerator.services.PdfService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class PdfGeneratorController {


    private PdfService pdfService;

    @Autowired
    public PdfGeneratorController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/welcome")
    public ModelAndView studentsView(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            System.out.println(file.getFileName());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
