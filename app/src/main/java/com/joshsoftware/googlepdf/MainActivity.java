package com.joshsoftware.googlepdf;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.joshsoftware.googlepdf.model.QuotationPremiumDetail;
import com.joshsoftware.googlepdf.model.VendorQuotation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

public class MainActivity extends AppCompatActivity {
    private boolean isPath;
    File myFile;

    VendorQuotation quotation;
    QuotationPremiumDetail premiumDetail;

    String customerInfo[] = {"", "", "", "Hyundai Grand i10", ""};
    String fillFirstPart[] = {"Customer",
            "Customer Address",
            "Telephone No.",
            "Make and Model of Vehicle",
            "Registration No."};
    String basicDetails = "Basic Details of Car";
    String fillSecondPart[] =
            {"IDV of Car (I)",
                    "Age of Vehicle",
                    "Cubic Capacity",
                    "Seating Capacity"};
    String secondPart[];

    String ageOfVehicle;
    String thirdPart[] = {"Own Damage", "Liability"};
    String fourthPart[] =
            {"From the rating grid of IDV(I) @3.283%",
                    "Basic Act",
                    "Sub-total OD",
                    "Personal Accident cover to Owner Driver @Rs 100,Cover 2 Lakhs",
                    "Less:Commercial Own Damage Discount @",
                    "Liability to paid driver @ Rs 50, Cover 1 Lakh",
                    "Total Schedule OD premium",
                    "PA Benefits(seating capacity)50000 per person",
                    "Less: No Claim Bonus @",
                    "Bi-fuel system",
                    "Total OD Premium",
                    "Total Liability Premium",
            };

    String fifthPart[]={"Addon Premium (Plan: Zero Depreciation, Per Lakh: Rs 450)",
            "Optional Personal Accident Cover",
            "Total Premium Payable",
            "Service Tax @ 15%",
            "Total Amount Without Addon + Ser. Tax",
            "Total Amount With Addon + Ser. Tax"
    };

    String finalResult[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isPath = false;
        Button createPDFbutton = (Button) findViewById(R.id.createPDF);
        final Button viewPDFbutton = (Button) findViewById(R.id.viewPDF);
        Button sendToWA = (Button) findViewById(R.id.wa);
        quotation = new VendorQuotation();
        quotation.setVendorName("TATA AIG General Insurance Private Ltd");
        quotation.setDiscount("Rs 300");
        quotation.setAddOnAmount("Rs 100");
        quotation.setCompanyCngKitRate(350);
        quotation.setExternalCngKitRate(450);
        quotation.setAgeWisePrice("Rs. 200");
        quotation.setPerPassengerRate("Rs. 500");

        premiumDetail = new QuotationPremiumDetail();
        premiumDetail.setIdv("Rs. 340,000");
        premiumDetail.setAddOnPremium(3400);
        premiumDetail.setBasicPremium(2000);
        premiumDetail.setBiFuelCharge(200);
        premiumDetail.setCommercialDiscount(250);
        premiumDetail.setCommercialOdpremium(500);
        premiumDetail.setFuelType("Petrol");
        premiumDetail.setLiabilityPremium(400);
        premiumDetail.setExternalFitted(true);
        premiumDetail.setNcbValue("20");
        premiumDetail.setNcbDiscount(1000);
        premiumDetail.setTotalOdPremium(3000);
        premiumDetail.setTotalPremium(5000);
        premiumDetail.setPaPremium(3000);
        premiumDetail.setBiFuelCharge(10d);
        premiumDetail.setLiabilityPremium(2500d);

        quotation.setPremiumDetail(premiumDetail);

        ageOfVehicle = "11 months";
        String cubicCapacity = "1300cc";
        String seatingCapacity = "5";
        secondPart = new String[]{premiumDetail.getIdv(), ageOfVehicle, cubicCapacity, seatingCapacity};
        finalResult = new String[]{"AddonPremium","OptAccCover","TotalPrem","Servicetax","AmtW//OAddon","AmtwST"};

        createPDFbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF(quotation);
            }
        });

        viewPDFbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPDF();
            }
        });

        sendToWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToWhatsApp();
            }
        });

        createPDF(quotation);
        viewPDF();
    }

    public void createPDF(VendorQuotation quotation) {
        String customerInfo[] = {"", "", "", "Hyundai Grand i10", ""};
        String fillFirstPart[] = {"Customer",
                "Customer Address",
                "Telephone No.",
                "Make and Model of Vehicle",
                "Registration No."};
        String basicDetails = "Basic Details of Car";
        String fillSecondPart[] =
                {"IDV of Car (I)",
                        "Age of Vehicle",
                        "Cubic Capacity",
                        "Seating Capacity"};
        String secondPart[]=new String[]{premiumDetail.getIdv(), ageOfVehicle, "1300", "5"};;

        String ageOfVehicle = "11 months";
        String thirdPart[] = {"Own Damage", "Liability"};
        String fourthPart[] =
                {"From the rating grid of IDV(I) @3.283%",
                        "Basic Act",
                        "Sub-total OD",
                        "Personal Accident cover to Owner Driver @Rs 100,Cover 2 Lakhs",
                        "Less:Commercial Own Damage Discount @",
                        "Liability to paid driver @ Rs 50, Cover 1 Lakh",
                        "Total Schedule OD premium",
                        "PA Benefits(seating capacity)50000 per person",
                        "Less: No Claim Bonus @",
                        "Bi-fuel system",
                        "Total OD Premium",
                        "Total Liability Premium",
                };

        String fifthPart[]={"Addon Premium (Plan: Zero Depreciation, Per Lakh: Rs 450)",
                "Optional Personal Accident Cover",
                "Total Premium Payable",
                "Service Tax @ 15%",
                "Total Amount Without Addon + Ser. Tax",
                "Total Amount With Addon + Ser. Tax"
        };

        QuotationPremiumDetail premiumDetail = quotation.getPremiumDetail();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        String formattedDate = date.format(calendar.getTime());
        long timeStamp = calendar.getTimeInMillis();

        File pdfFolder = new File(Environment.getExternalStorageDirectory().getPath(), "PDFDemo");
        if (!pdfFolder.exists()) {
            isPath = pdfFolder.mkdir();
        }
        myFile = new File(pdfFolder.getAbsolutePath() + "/" + timeStamp + "NewPDFFile.pdf");

        PDFBoxResourceLoader.init(getApplicationContext());
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDPageContentStream contentStream;

        PDFont font = PDType1Font.HELVETICA;
        float fontSize = 8;

        PDRectangle mediabox = page.getMediaBox();

        float margin = 72;
        float width = mediabox.getWidth() - 2 * margin;
        float length = mediabox.getHeight() - 2 * margin;
        float startX = mediabox.getLowerLeftX() + margin;
        float startY = mediabox.getUpperRightY() - margin;
        float y = startY, x = startX;

        float _50_width = width * 0.5f;
        float _35_width = width * 0.35f;
        float _85_width = width * 0.85f;
        float _40_width = width * 0.4f;
        float _60_width = width * 0.6f;
        float _15_width = width * 0.15f;
        float _20_width = width * 0.2f;
        float _80_width = width * 0.8f;
        float _30_width = width * 0.3f;


        try {

            AssetManager assetManager = getAssets();
            contentStream = new PDPageContentStream(document, page);
            drawString(contentStream, width, x, y + 3, fontSize, formattedDate, false);

            //print side lines.
            drawLine(contentStream,x, y, x, y - length +108);
            drawLine(contentStream,x + width, y, x + width, y - length +108);

            drawLine(contentStream,x, y, x + width, y);
            y -= 100;

            InputStream inputStream = assetManager.open("tata.jpg");
            PDImageXObject imageXObject = JPEGFactory.createFromStream(document, inputStream);
            contentStream.drawImage(imageXObject, x + 10, y + 10, 80, 80);

            //print company name
            float stringWidth = width * 0.5f;
            drawString(contentStream, stringWidth, x + _40_width + 20f, y + 50, 14, quotation.getVendorName(), true);
            drawLine(contentStream,x + _40_width, y, x + _40_width, y - (20 * 5));

            for (int i = 0; i < 6; i++) {
                drawLine(contentStream,x, y, x + width, y);
                if (i > 0) {
                    drawString(contentStream, x + _40_width - 20, x + 10, y + 5, fontSize, fillFirstPart[i - 1], false);
                    drawString(contentStream, x + _40_width - 20, x + _40_width + 10, y + 5, fontSize, customerInfo[i - 1], false);
                }
                y -= 20;
            }
            y -= 20;
            drawLine(contentStream,x + _35_width, y, x + _35_width, y - 40);
            stringWidth = font.getStringWidth(basicDetails) / 1000f * fontSize;
            drawString(contentStream, width, x + _50_width - stringWidth / 2, y + 15, 12, basicDetails, true);
            drawLine(contentStream,x + _50_width, y, x + _50_width, y - 20 * 12+10);
            drawLine(contentStream,x + _85_width, y, x + _85_width, y - 40);
            for (int j = 0; j < 3; j++) {
                drawLine(contentStream,x, y, x + width, y);
                if (j > 0) {
                    int i = j * 2 - 1;
                    drawString(contentStream, _30_width - 20, x + 10, y + 5, fontSize, fillSecondPart[i - 1], false);
                    drawString(contentStream, _20_width - 5, x + _35_width + 10, y + 5, fontSize, secondPart[i - 1], false);
                    drawString(contentStream, _80_width - 20, x + _50_width + 10, y + 5, fontSize, fillSecondPart[i], false);
                    drawString(contentStream, _20_width - 5, x + _85_width+ 10, y + 5, fontSize, secondPart[i], false);
                }
                y -= 20;
            }
            y -= 20;

            drawString(contentStream, _50_width - 20, x + width * 0.25f - font.getStringWidth(thirdPart[0]) / 2000f * fontSize, y + 15, 12, thirdPart[0], true);
            drawString(contentStream, _50_width - 20, x + width * 0.75f - font.getStringWidth(thirdPart[1]) / 2000f * fontSize, y + 15, 12, thirdPart[1], true);

            drawLine(contentStream,x+_35_width, y, x+_35_width, y - 20 * 8+10);
            drawLine(contentStream,x+_85_width, y, x+_85_width, startY - length+108);
            int j=0;
            for (int i = 0; i < 2; i++) {
                drawLine(contentStream,x, y, x + width, y);
                if (i > 0) {
                    j = i;
                    drawString(contentStream,_35_width, x + 10, y + 5, fontSize, fourthPart[j - 1], false);
                    drawString(contentStream,_15_width, x+_35_width+10, y + 5, fontSize, premiumDetail.getIdv(), false);
                    drawString(contentStream,_35_width, x+_50_width+ 10, y + 5, fontSize, fourthPart[j], false);
                    drawString(contentStream,_15_width, x+_85_width+ 10, y + 5, fontSize, "Rs." + premiumDetail.getBasicPremium(), false);
                }
                y -= 20;
            }
            j++;
            y -= 10;
            drawString(contentStream,_35_width-20f,x+10,y+20,fontSize,fourthPart[2],false);
            drawString(contentStream,_15_width-10f,x+_35_width+10,y+20,fontSize,"Rs."+String.valueOf(premiumDetail.getOdPremium()),false);
            drawString(contentStream,_35_width-20f,x+_50_width+10,y+20,fontSize,fourthPart[3],false);
            drawString(contentStream,_15_width-10f,x+_85_width+10,y+20,fontSize,"Rs.100",false);

            for (int i = 0; i < 2; i++) {
                drawLine(contentStream,x, y, x + width, y);
                if(i==1){
                    drawString(contentStream,_35_width-20f,x+10,y+20,fontSize,fourthPart[4]+String.valueOf(premiumDetail.getOdRate())+"%",true);
                    drawString(contentStream,_35_width-10f,x+_35_width+10,y+20,fontSize,"Rs."+String.valueOf(premiumDetail.getCommercialDiscount()),true);

                    drawString(contentStream,_35_width-20f,x+_50_width+10,y+20,fontSize,fourthPart[5],true);
                    drawString(contentStream,_35_width-10f,x+_85_width+10,y+20,fontSize,"Rs.50",true);
                }
                y -= 30;
            }
            drawLine(contentStream,x, y, x + width, y);
            drawString(contentStream,_35_width-20f,x+10,y+20,fontSize,fourthPart[6],false);
            drawString(contentStream,_15_width-10f,x+_35_width+10,y+20,fontSize,"Rs."+String.valueOf(premiumDetail.getTotalPremiumWithoutPremium()),false);
            drawString(contentStream,_35_width-20f,x+_50_width+10,y+20,fontSize,fourthPart[7],false);
            drawString(contentStream,_15_width-10f,x+_85_width+10,y+20,fontSize,"Rs."+String.valueOf(premiumDetail.getPaPremium()),false);
            y-=20;
            drawString(contentStream,_35_width-20f,x+10,y+5,fontSize,fourthPart[8]+String.valueOf(premiumDetail.getNcbValue())+"%",false);
            drawString(contentStream,_15_width-20f,x+_35_width+10,y+5,fontSize,"Rs."+"100",false);
            drawString(contentStream,_35_width-20f,x+_50_width+10,y+5,fontSize,fourthPart[9],false);
            drawString(contentStream,_15_width-20f,x+_85_width+10,y+5,fontSize,"Rs."+String.valueOf(premiumDetail.getBiFuelCharge()),false);
            drawLine(contentStream,x, y, x + width, y);
            y-=20;
            drawString(contentStream,_35_width-20f,x+10,y+5,fontSize,fourthPart[10],true);
            drawString(contentStream,_15_width-20f,x+_35_width+10,y+5,fontSize,"Rs."+100,true);
            drawString(contentStream,_35_width-20f,x+_50_width+10,y+5,fontSize,fourthPart[11],true);
            drawString(contentStream,_15_width-20f,x+_85_width+10,y+5,fontSize,"Rs."+String.valueOf(premiumDetail.getLiabilityPremium()),true);
            drawLine(contentStream,x,y,x+width,y);
            y-=20;

            for(int i=0;i<6;i++){
                boolean isBold=false;
                if(i==0||i==2||i==4||i==5){
                    isBold=true;
                }
                drawString(contentStream,_85_width-20f,x+10,y+5,fontSize,fifthPart[i],isBold);
                drawString(contentStream,_15_width-10f,x+_85_width+10,y+5,fontSize,finalResult[i],isBold);
                drawLine(contentStream,x,y,x+width,y);
                y-=20;
            }

            contentStream.close();
            document.save(myFile);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawLine(PDPageContentStream contentStream,float startX,float startY,float endX,float endY){
        try {
            drawLine(contentStream,startX, startY, endX, endY);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void drawString(PDPageContentStream contentStream, float width, float startX, float startY, float fontSize, String s, boolean isBold) {
        PDType1Font font;
        if (isBold) {
            font = PDType1Font.HELVETICA_BOLD;

        } else {
            font = PDType1Font.HELVETICA;

        }
        float leading = 1.2f * fontSize;
        List<String> lines = new ArrayList<>();
        int lastSpace = -1;
        while (s.length() > 0) {
            int spaceIndex = s.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = s.length();
            String subString = s.substring(0, spaceIndex);

            try {
                float size = fontSize * font.getStringWidth(subString) / 1000;
                if (size > width) {
                    if (lastSpace < 0)
                        lastSpace = spaceIndex;
                    subString = s.substring(0, lastSpace);
                    lines.add(subString);
                    s = s.substring(lastSpace).trim();
                    lastSpace = -1;
                } else if (spaceIndex == s.length()) {
                    lines.add(s);
                    s = "";
                } else {
                    lastSpace = spaceIndex;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.moveTextPositionByAmount(startX, startY);
            for (String line : lines) {
                contentStream.drawString(line);
                contentStream.moveTextPositionByAmount(0, -leading);
            }
            contentStream.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewPDF() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(myFile), "application/pdf");
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent = Intent.createChooser(i, "Open With");
        startActivity(intent);
    }

    public void sendToWhatsApp() {
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("application/pdf");
        waIntent.setPackage("com.whatsapp");
        Uri uri = Uri.parse("file://" + myFile.getAbsolutePath());
        waIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(waIntent, "Hello"));
    }
}
