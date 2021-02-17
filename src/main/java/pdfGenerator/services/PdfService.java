package pdfGenerator.services;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfGenerator.bean.PaymentClaim;
import pdfGenerator.bean.PaymentSummary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@Service
public class PdfService {

    private SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public File generatePdf(String pdfType) throws IOException, DocumentException {
        Context context = getContext(pdfType);
        String html = loadAndFillTemplate(context, pdfType);
        return renderPdf(html, pdfType);
    }


    private File renderPdf(String html, String pdfType) throws IOException, DocumentException {
        File file = File.createTempFile(pdfType, ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(String pdfType) {
        Context context = new Context();
        if (pdfType.isEmpty() == false && "PaymentResponse".equalsIgnoreCase(pdfType)) {
            context.setVariable("refNumber", "11");
            context.setVariable("repDate", "19-Jan-2021");
            context.setVariable("recieverEmail", "xxx PTE LTD");
            context.setVariable("serviceAdd", "28 Riverfront, Singapore XXXXXX");
            context.setVariable("tel", "1234-xxxx");
            context.setVariable("fax", "5438-xxxx");
            context.setVariable("email", "henry@guanchuan.com; winbo@guanchuan.com ");
            context.setVariable("inCharge", "MR. ABC");
            context.setVariable("sender", "KIMLY CONSTRUCTION PTE LTD");
            context.setVariable("serviceAddSender", "No. 3 NEW INDUSTRIAL ROAD #05-01/02 KIMLY BUILDING SINGAPORE 536197");
            context.setVariable("telSender", "1234-xxxx");
            context.setVariable("faxSender", "5438-xxxx");
            context.setVariable("emailSender", "sallytan@kimly.com.sg");
            context.setVariable("inChargeSender", "Ms. Sally Tan");
            context.setVariable("projectTitle", "PROPOSED NEW ERECTION OF SINGAPORE INSTITUTE OF TECHNOLOGY CAMPUS....");
            context.setVariable("contractIdentificaiton", "Supply, Fabricate, Delivery and Installation of xxx");
            context.setVariable("refClaim", "01-12-2020");
            context.setVariable("frm", "31-12-2020");
            context.setVariable("clmIdentify", "11");
            context.setVariable("clmIdentifyNum", "GCE/PC/KCPL/SITP2/F-241/11/Henry");
            context.setVariable("clmIdentifyDate", "24-12-2020");
            context.setVariable("clmIdentifyAmount", "SGD 518,930.00");
        }
        if (pdfType.isEmpty() == false && "ProgressPayment".equalsIgnoreCase(pdfType)) {
            context.setVariable("projectName", "xxx");
            context.setVariable("contractType", "LUMP SUM / RE-MEASUREMENT");
            context.setVariable("reviewDirector", "Choo Tat Jin");
            context.setVariable("approveDirector", "Khoo Beng Hwee");
        }
        if (pdfType.isEmpty() == false && "PaymentClaim".equalsIgnoreCase(pdfType)) {
            context.setVariable("numberClaim", "11");
            ArrayList<PaymentClaim> paymentClaimList = new ArrayList<PaymentClaim>();
            PaymentClaim payment = new PaymentClaim();
            payment.setPc("8");
            payment.setMonth("Sep-20");
            payment.setRecoverySchedule("$10,000.00");
            payment.setRecoveryAmount("$10,000.00");
            payment.setCumulative("$10,000.00");
            paymentClaimList.add(payment);
            PaymentClaim payment2 = new PaymentClaim();
            payment2.setPc("9");
            payment2.setMonth("Oct-20");
            payment2.setRecoverySchedule("$10,000.00");
            payment2.setRecoveryAmount("$10,000.00");
            payment2.setCumulative("$20,000.00");
            paymentClaimList.add(payment2);
            PaymentClaim payment3 = new PaymentClaim();
            payment3.setPc("10");
            payment3.setMonth("Nov-20");
            payment3.setRecoverySchedule("$10,000.00");
            payment3.setRecoveryAmount("$10,000.00");
            payment3.setCumulative("$30,000.00");
            paymentClaimList.add(payment3);
            PaymentClaim payment4 = new PaymentClaim();
            payment4.setPc("10");
            payment4.setMonth("Dec-20");
            payment4.setRecoverySchedule("$10,000.00");
            payment4.setRecoveryAmount("$10,000.00");
            payment4.setCumulative("$40,000.00");
            paymentClaimList.add(payment4);
            context.setVariable("paymentList", paymentClaimList);
        }
        if (pdfType.isEmpty() == false && "PaymentSummary".equalsIgnoreCase(pdfType)) {
            context.setVariable("numberClaim", "11");
            ArrayList<PaymentSummary> paymentSummaryList = new ArrayList<PaymentSummary>();
            PaymentSummary payment = new PaymentSummary();
            payment.setPc("1");
            payment.setMonth("Feb-20");
            payment.setCertification("$300,000.00");
            payment.setAdvacnePayment("");
            payment.setBackcharge("");
            payment.setCumulative("$300,000.00");
            paymentSummaryList.add(payment);
            PaymentSummary payment1 = new PaymentSummary();
            payment1.setPc("2");
            payment1.setMonth("Mar-20");
            payment1.setCertification("$500,000.00");
            payment1.setAdvacnePayment("");
            payment1.setBackcharge("");
            payment1.setCumulative("$200,000.00");
            paymentSummaryList.add(payment1);
            PaymentSummary payment2 = new PaymentSummary();
            payment2.setPc("3A");
            payment2.setMonth("Apr-20");
            payment2.setCertification("");
            payment2.setAdvacnePayment("$40,000.00");
            payment2.setBackcharge("");
            payment2.setCumulative("$800,000.00");
            paymentSummaryList.add(payment2);
            PaymentSummary payment3 = new PaymentSummary();
            payment3.setPc("4");
            payment3.setMonth("May-20");
            payment3.setCertification("$200,000.00");
            payment3.setAdvacnePayment("");
            payment3.setBackcharge("$220.00");
            payment3.setCumulative("$820,000.00");
            paymentSummaryList.add(payment3);
            PaymentSummary payment4 = new PaymentSummary();
            payment4.setPc("5");
            payment4.setMonth("Jun-20");
            payment4.setCertification("");
            payment4.setAdvacnePayment("");
            payment4.setBackcharge("");
            payment4.setCumulative("$820,000.00");
            paymentSummaryList.add(payment4);
            PaymentSummary payment5 = new PaymentSummary();
            payment5.setPc("6");
            payment5.setMonth("Jul-20");
            payment5.setCertification("");
            payment5.setAdvacnePayment("");
            payment5.setBackcharge("");
            payment5.setCumulative("$820,000.00");
            paymentSummaryList.add(payment5);
            PaymentSummary payment6 = new PaymentSummary();
            payment6.setPc("7");
            payment6.setMonth("Aug-20");
            payment6.setCertification("");
            payment6.setAdvacnePayment("");
            payment6.setBackcharge("");
            payment6.setCumulative("$820,000.00");
            paymentSummaryList.add(payment6);
            PaymentSummary payment7 = new PaymentSummary();
            payment7.setPc("8");
            payment7.setMonth("Sep-20");
            payment7.setCertification("$80,000.00");
            payment7.setAdvacnePayment("");
            payment7.setBackcharge("");
            payment7.setCumulative("$900,000.00");
            paymentSummaryList.add(payment7);
            PaymentSummary payment8 = new PaymentSummary();
            payment8.setPc("9");
            payment8.setMonth("Oct-20");
            payment8.setCertification("$90,000.00");
            payment8.setAdvacnePayment("");
            payment8.setBackcharge("");
            payment8.setCumulative("$990,000.00");
            paymentSummaryList.add(payment8);
            PaymentSummary payment9 = new PaymentSummary();
            payment9.setPc("10");
            payment9.setMonth("Nov-20");
            payment9.setCertification("$340,000.00");
            payment9.setAdvacnePayment("");
            payment9.setBackcharge("");
            payment9.setCumulative("$1,024,000.00");
            paymentSummaryList.add(payment9);
            context.setVariable("paymentList", paymentSummaryList);
        }

        if (pdfType.isEmpty() == false && "Backcharge".equalsIgnoreCase(pdfType)) {
            context.setVariable("projectName", "SINGAPORE INSTITUTE OF TECHNOLOGY -PLOT 2");
            context.setVariable("subConName", "XYZ PTE LTD");
            context.setVariable("subConWork", "Supply, Fabricate, Delivery and Installation of xyz");
        }
        if (pdfType.isEmpty() == false && "VariationOrder".equalsIgnoreCase(pdfType)) {
            context.setVariable("projectName", "SINGAPORE INSTITUTE OF TECHNOLOGY -PLOT 2");
            context.setVariable("subConName", "XYZ PTE LTD");
            context.setVariable("subConWork", "Supply, Fabricate, Delivery and Installation of xyz");
        }
        if (pdfType.isEmpty() == false && "PaymentDetail".equalsIgnoreCase(pdfType)) {
            context.setVariable("projectName", "SINGAPORE INSTITUTE OF TECHNOLOGY -PLOT 2");
            context.setVariable("subConName", "XYZ PTE LTD");
            context.setVariable("subConWork", "Supply, Fabricate, Delivery and Installation of xyz");
        }
        return context;
    }

    private String loadAndFillTemplate(Context context, String pdfType) {
        return templateEngine.process(pdfType, context);
    }


}