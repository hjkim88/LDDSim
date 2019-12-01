/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import JavaMI.MutualInformation;

/**
 *
 * @author Hyunjin Kim (chriskim@cs.yonsei.ac.kr)
 */
public class DDSimWithMatrix {
    
    private String[] geneList;
    private String[] diseaseList;
    private String[] drugList;
    private int[][] dige;
    private int[][] didr;
    private int geneNum;
    private int diseaseNum;
    private int drugNum;
    
    public DDSimWithMatrix() {
        initVariables();
    }
    
    private void initVariables() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("similarity.DDSimWithMatrix() ... Begins.");
        }
        
        loadEntities();
        dige = new int[diseaseNum][geneNum];
        didr = new int[diseaseNum][drugNum];
        loadMatrices();
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
                System.out.println("similarity.DDSimWithMatrix().loadEntities().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private void loadMatrices() {
        try {
            File f = new File(global.Variables.matrix_path + "Disease-Gene_matrix.dat");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            String[] str;
            
            for(int i = 0; i < diseaseNum; i++) {
                line = br.readLine();
                str = line.split("\t");
                
                for(int j = 0; j < geneNum; j++) {
                    dige[i][j] = Integer.parseInt(str[j]);
                }
            }
            
            f = new File(global.Variables.matrix_path + "Disease-Drug_matrix.dat");
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            for(int i = 0; i < diseaseNum; i++) {
                line = br.readLine();
                str = line.split("\t");
                
                for(int j = 0; j < drugNum; j++) {
                    didr[i][j] = Integer.parseInt(str[j]);
                }
            }
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("similarity.DDSimWithMatrix().loadMatrices().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private boolean isAllZero(int[] d) {
        boolean r = true;
        int len = d.length;
        
        for(int i = 0; i < len; i++) {
            if(d[i] != 0) {
                r = false;
                break;
            }
        }
        
        return r;
    }
    
    private double[] iArrToDArr(int[] d) {
        int len = d.length;
        double[] r = new double[len];
        
        for(int i = 0; i < len; i++) {
            r[i] = (double) d[i];
        }
        
        return r;
    }
    
    private void calculateSim() {
        try {
            FileWriter fw = new FileWriter(global.Variables.disease_disease_similarity_path);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile= new PrintWriter(bw);
            
            for(int i = 0; i < diseaseNum-1; i++) {
                System.out.println(i + " / " + (diseaseNum-2));
                for(int j = i+1; j < diseaseNum; j++) {
                    if((!isAllZero(dige[i])) && (!isAllZero(dige[j]))) {
                        if((!isAllZero(didr[i])) && (!isAllZero(didr[j]))) {
                            outFile.println(diseaseList[i] + "\t" + diseaseList[j] + "\t" 
                                    + ((MutualInformation.calculateMutualInformation(iArrToDArr(dige[i]), iArrToDArr(dige[j]))
                                            + MutualInformation.calculateMutualInformation(iArrToDArr(didr[i]), iArrToDArr(didr[j]))) / 2));
                        }
                        else {
                            outFile.println(diseaseList[i] + "\t" + diseaseList[j] + "\t" 
                                    + MutualInformation.calculateMutualInformation(iArrToDArr(dige[i]), iArrToDArr(dige[j])));
                        }
                    }
                    else if((!isAllZero(didr[i])) && (!isAllZero(didr[j]))) {
                        outFile.println(diseaseList[i] + "\t" + diseaseList[j] + "\t" 
                                + MutualInformation.calculateMutualInformation(iArrToDArr(didr[i]), iArrToDArr(didr[j])));
                    }
                }
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("ERROR: similarity.DDSimWithMatrix().calculateSim().BiffException");
            }
            ioe.printStackTrace();
        }
    }
    
    public void start() {
        calculateSim();
    }
    
}
