package pdfGenerator;

import com.lowagie.text.DocumentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@SpringBootApplication(scanBasePackages={"pdfGenerator"})
public class DemoApplication {

	public static void main(String[] args) throws IOException, DocumentException {
		SpringApplication.run(DemoApplication.class, args);

	}
}
