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
    private String pdfType="";

    @Autowired
    public PdfGeneratorController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/welcome")
    public ModelAndView studentsView(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/PaymentResponse-pdf")
    public void downloadPaymentResponse(HttpServletResponse response) {
        try {
            pdfType ="PaymentResponse";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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

    @GetMapping("/ProgressResponse-pdf")
    public void downloadProgressResponse(HttpServletResponse response) {
        try {
            pdfType ="ProgressPayment";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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

    @GetMapping("/PaymentClaim-pdf")
    public void downloadPaymentClaim(HttpServletResponse response) {
        try {
            pdfType ="PaymentClaim";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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

    @GetMapping("/PaymentSummary-pdf")
    public void downloadPaymentSummary(HttpServletResponse response) {
        try {
            pdfType ="PaymentSummary";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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

    @GetMapping("/Backcharge-pdf")
    public void downloadBackcharge(HttpServletResponse response) {
        try {
            pdfType ="Backcharge";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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

    @GetMapping("/VariationOrder-pdf")
    public void downloadVariationOrder(HttpServletResponse response) {
        try {
            pdfType ="VariationOrder";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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

    @GetMapping("/PaymentDetail-pdf")
    public void downloadPaymentDetail(HttpServletResponse response) {
        try {
            pdfType ="PaymentDetail";
            Path file = Paths.get(pdfService.generatePdf(pdfType).getAbsolutePath());
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
