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
public class WeightCalculation {
    
    private String filePath = "";
    private int startYear;
    private int endYear;
    private String[] association;
    private int[] weight;
    private int len;
    
    public WeightCalculation() {
        initVariables();
    }
    
    private void initVariables() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("association.WeightCalculation() ... Begins.");
        }
        
        filePath = global.Variables.association_path;
        startYear = global.Variables.expStartYear;
        endYear = global.Variables.expEndYear;
    }
    
    private int isExist(String str) {
        int r = -1;
        
        for(int i = 0; i < len; i++) {
            if(str.equals(association[i])) {
                r = i;
            }
        }
        
        return r;
    }
    
    private void geneDisease() {
        try {
            File f;
            int cnt = 0;
            
            for(int i = startYear; i <= endYear; i++) {
                f = new File(filePath + "Gene-Disease(" + i + ").dat");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                while(br.readLine() != null) {
                    cnt++;
                }
                
                br.close();
                fr.close();
            }
            len = cnt;
            association = new String[len];
            weight = new int[len];
            
            for(int i = 0; i < len; i++) {
                association[i] = "";
                weight[i] = 0;
            }
            
            cnt = 0;
            for(int i = startYear; i <= endYear; i++) {
                f = new File(filePath + "Gene-Disease(" + i + ").dat");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                int tmp;
                
                while((line = br.readLine()) != null) {
                    tmp = isExist(line);
                    if(tmp == -1) {
                        association[cnt] = line;
                        weight[cnt] = 1;
                        cnt++;
                    }
                    else {
                        weight[tmp] ++;
                    }
                }
                
                br.close();
                fr.close();
            }
            
            FileWriter fw = new FileWriter(filePath + "Gene-Disease.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);
            
            for(int i = 0; i < cnt; i++) {
                outFile.println(association[i] + "\t" + weight[i]);
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("association.WeightCalculation().geneDisease().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private void diseaseDrug() {
        try {
            File f;
            int cnt = 0;
            
            for(int i = startYear; i <= endYear; i++) {
                f = new File(filePath + "Disease-Drug(" + i + ").dat");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                while(br.readLine() != null) {
                    cnt++;
                }
                
                br.close();
                fr.close();
            }
            len = cnt;
            association = new String[len];
            weight = new int[len];
            
            for(int i = 0; i < len; i++) {
                association[i] = "";
                weight[i] = 0;
            }
            
            cnt = 0;
            for(int i = startYear; i <= endYear; i++) {
                f = new File(filePath + "Disease-Drug(" + i + ").dat");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                int tmp;
                
                while((line = br.readLine()) != null) {
                    tmp = isExist(line);
                    if(tmp == -1) {
                        association[cnt] = line;
                        weight[cnt] = 1;
                        cnt++;
                    }
                    else {
                        weight[tmp] ++;
                    }
                }
                
                br.close();
                fr.close();
            }
            
            FileWriter fw = new FileWriter(filePath + "Disease-Drug.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);
            
            for(int i = 0; i < cnt; i++) {
                outFile.println(association[i] + "\t" + weight[i]);
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("association.WeightCalculation().diseaseDrug().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private void geneDrug() {
        try {
            File f;
            int cnt = 0;
            
            for(int i = startYear; i <= endYear; i++) {
                f = new File(filePath + "Gene-Drug(" + i + ").dat");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                while(br.readLine() != null) {
                    cnt++;
                }
                
                br.close();
                fr.close();
            }
            len = cnt;
            association = new String[len];
            weight = new int[len];
            
            for(int i = 0; i < len; i++) {
                association[i] = "";
                weight[i] = 0;
            }
            
            cnt = 0;
            for(int i = startYear; i <= endYear; i++) {
                f = new File(filePath + "Gene-Drug(" + i + ").dat");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                int tmp;
                
                while((line = br.readLine()) != null) {
                    tmp = isExist(line);
                    if(tmp == -1) {
                        association[cnt] = line;
                        weight[cnt] = 1;
                        cnt++;
                    }
                    else {
                        weight[tmp] ++;
                    }
                }
                
                br.close();
                fr.close();
            }
            
            FileWriter fw = new FileWriter(filePath + "Gene-Drug.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);
            
            for(int i = 0; i < cnt; i++) {
                outFile.println(association[i] + "\t" + weight[i]);
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("association.WeightCalculation().geneDrug().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    public void start() {
        geneDisease();
        diseaseDrug();
        geneDrug();
    }
    
}
