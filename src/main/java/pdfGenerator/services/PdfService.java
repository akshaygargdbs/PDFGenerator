package pdfGenerator.services;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {

    private SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public File generatePdf() throws IOException, DocumentException {
        System.out.println("context" + getContext());
        Context context = getContext();
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }


    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("thymeleaf_template", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext() {
        Context context = new Context();
        context.setVariable("refNumber", "TEST000001");
        context.setVariable("repDate", "19-Jan-2021");

        context.setVariable("recieverEmail", "");
        context.setVariable("serviceAdd", "");
        context.setVariable("tel", "");
        context.setVariable("fax", "");
        context.setVariable("email", "test@test.com");
        context.setVariable("inCharge", "");

        context.setVariable("sender", "KIMLY CONSTRUCTION PTE LTD");
        context.setVariable("serviceAddSender", "No. 3 NEW INDUSTRIAL ROAD #05-01/02 KIMLY BUILDING SINGAPORE 536197");
        context.setVariable("telSender", "6280 1755");
        context.setVariable("faxSender", "6280 2755");
        context.setVariable("emailSender", "test@test.com");
        context.setVariable("inChargeSender", "");

        context.setVariable("projectTitle", "PROPOSED NEW ERECTION OF SINGAPORE INSTITUTE OF TECHNOLOGY CAMPUS AT PUNGGOL NORTH (PLOT 2) COMPRISING 1 NO. OF 10-STOREY ADMINISTRATION BLOCK, PART 1/4/6/9-STOREY ACADEMIC BLOCKS, BASEMENT CAR PARK AND OTHER ANCILLARY FACILITIES AND WORKS AT PUNGGOL COAST ROAD AND INCLUDES THE TEMPORARY WORKS AND THE PERMANENT WORKS, AND WHERE THE CONTEXT REQUIRES, A PHASE OR PART OF THE WORKS (THE “WORKS”)");

        context.setVariable("contractIdentificaiton", "");
        context.setVariable("refClaim", "");
        context.setVariable("frm", "");
        context.setVariable("clmIdentify", "");
        context.setVariable("clmIdentifyNum", "");
        context.setVariable("clmIdentifyDate", "");
        context.setVariable("clmIdentifyAmount", "");
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("thymeleaf_template", context);
    }


}