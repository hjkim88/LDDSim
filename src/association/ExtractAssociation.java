/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package association;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Hyunjin Kim (chriskim@cs.yonsei.ac.kr)
 */
public class ExtractAssociation {
    
    private String medlinePath;
    private String[] geneList;
    private String[] diseaseList;
    private String[] drugList;
    private int geneNum;
    private int diseaseNum;
    private int drugNum;
    private int year;
    
    public ExtractAssociation(int year) {
        initVariables(year);
    }
    
    private void initVariables(int year) {
        if(global.Variables.debuggingMode == true) {
            System.out.println("association.ExtractAssociation() ... Begins.");
        }
        
        this.year = year;
        medlinePath = global.Variables.medlinePath + year + ".dat";
    }
    
    private void loadEntities() {
        try {
            File f = new File(global.Variables.geneListPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            geneNum = Integer.parseInt(br.readLine());
            geneList = new String[geneNum];
            for(int i = 0; i < geneNum; i++) {
                geneList[i] = br.readLine();
            }
            
            f = new File(global.Variables.diseaseListPath);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            diseaseNum = Integer.parseInt(br.readLine());
            diseaseList = new String[diseaseNum];
            for(int i = 0; i < diseaseNum; i++) {
                diseaseList[i] = br.readLine();
            }
            
            f = new File(global.Variables.drugListPath);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            drugNum = Integer.parseInt(br.readLine());
            drugList = new String[drugNum];
            for(int i = 0; i < drugNum; i++) {
                drugList[i] = br.readLine();
            }
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("association.ExtractAssociation().loadEntities().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private void extract() {
        try {
            File f = new File(medlinePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw1 = new FileWriter(global.Variables.association_path + "Gene-Disease(" + year + ").dat");
            FileWriter fw2 = new FileWriter(global.Variables.association_path + "Disease-Drug(" + year + ").dat");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            PrintWriter outFile1 = new PrintWriter(bw1);
            PrintWriter outFile2 = new PrintWriter(bw2);
            
            String line;
            String[] str;
            String[][] associationTemp;
            int cnt1, cnt2, cnt3 = 0;
            
            String[] geneTemp = new String[global.Variables.entityTempLen];
            String[] diseaseTemp = new String[global.Variables.entityTempLen];
            String[] drugTemp = new String[global.Variables.entityTempLen];
            
            while(br.readLine() != null) {
                line = br.readLine();
                str = line.split("\\.");
                
                for(int i = 0; i < str.length; i++) {
                    cnt1 = 0;
                    for(int j = 0; j < geneNum; j++) {
                        if(str[i].contains(" " + geneList[j] + " ")) {
                            geneTemp[cnt1] = geneList[j];
                            cnt1++;
                        }
                    }
                    cnt2 = 0;
                    for(int j = 0; j < diseaseNum; j++) {
                        if(str[i].contains(" " + diseaseList[j] + " ")) {
                            diseaseTemp[cnt2] = diseaseList[j];
                            cnt2++;
                        }
                    }
                    cnt3 = 0;
                    for(int j = 0; j < drugNum; j++) {
                        if(str[i].contains(" " + drugList[j] + " ")) {
                            drugTemp[cnt3] = drugList[j];
                            cnt3++;
                        }
                    }
                    
                    associationTemp = makeAssociation(cnt1, geneTemp, cnt2, diseaseTemp, cnt3, drugTemp);
                    for(int j = 0; j < (cnt1 * cnt2); j++) {
                        outFile1.println(associationTemp[0][j]);
                    }
                    for(int j = 0; j < (cnt2 * cnt3); j++) {
                        outFile2.println(associationTemp[1][j]);
                    }
                }
                br.readLine();
            }
            
            outFile1.close();
            outFile2.close();
            bw1.close();
            bw2.close();
            fw1.close();
            fw2.close();
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("association.ExtractAssociation().extract().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private String[][] makeAssociation(int cnt1, String[] str1, int cnt2, String[] str2, int cnt3, String[] str3) {
        String[][] association = new String[2][global.Variables.entityTempLen * global.Variables.entityTempLen];
        int cnt;
        
        if((cnt1 > 0) && (cnt2 > 0)) {
            cnt = 0;
            for(int i = 0; i < cnt1; i++) {
                for(int j = 0; j < cnt2; j++) {
                    association[0][cnt] = str1[i] + "\t" + str2[j];
                    cnt++;
                }
            }
        }
        
        if(cnt2 > 0 && cnt3 > 0) {
            cnt = 0;
            for(int i = 0; i < cnt2; i++) {
                for(int j = 0; j < cnt3; j++) {
                    association[1][cnt] = str2[i] + "\t" + str3[j];
                    cnt++;
                }
            }
        }
        
        return association;
    }
    
    public void start() {
        loadEntities();
        extract();
    }
    
}
