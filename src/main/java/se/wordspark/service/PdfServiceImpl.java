package se.wordspark.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import se.wordspark.entity.Word;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PdfServiceImpl.class);
    @Override
    public List<Word> getAllWords(String pdfPath) throws IOException {
        String text;
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                text = stripper.getText(document);
                List<String> words = Arrays.asList(text.split(" "));

                System.out.println("Text:" + words);
            }
            document.close();
        }catch (IOException e){
            LOGGER.error("Not found %s",pdfPath);
        }

        return null;
    }
}
