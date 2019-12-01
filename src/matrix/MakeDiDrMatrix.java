/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

/**
 *
 * @author Hyunjin Kim (chriskim@cs.yonsei.ac.kr)
 */
public class MakeDiDrMatrix {
    
    private String[] drugList;
    private String[] diseaseList;
    private int[][] didr;
    private String[] diAssociation;
    private String[] drAssociation;
    private int[] weight;
    private int drugNum;
    private int diseaseNum;
    private int associationNum;
    
    public MakeDiDrMatrix() {
        initVariables();
    }
    
    private void initVariables() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("matrix.MakeDiDrMatrix() ... Begins.");
        }
        
        loadEntities();
        loadAssociationInfo();
        
        didr = new int[diseaseNum][drugNum];
        for(int i = 0; i < diseaseNum; i++) {
            for(int j = 0; j < drugNum; j++) {
                didr[i][j] = 0;
            }
        }
    }
    
    private void loadEntities() {
        try {
            File f = new File(global.Variables.drugListPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            drugNum = Integer.parseInt(br.readLine());
            drugList = new String[drugNum];
            for(int i = 0; i < drugNum; i++) {
                drugList[i] = br.readLine();
            }
            
            f = new File(global.Variables.diseaseListPath);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            diseaseNum = Integer.parseInt(br.readLine());
            diseaseList = new String[diseaseNum];
            for(int i = 0; i < diseaseNum; i++) {
                diseaseList[i] = br.readLine();
            }
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("matrix.MakeDiDrMatrix().loadEntities().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
     private void loadAssociationInfo() {
        try {
            File f = new File(global.Variables.association_path + "Disease-Drug.dat");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            associationNum = Integer.parseInt(br.readLine());
            diAssociation = new String[associationNum];
            drAssociation = new String[associationNum];
            weight = new int[associationNum];
            
            String[] line;
            for(int i = 0; i < associationNum; i++) {
                line = br.readLine().split("\t");
                weight[i] = Integer.parseInt(line[2]);
                diAssociation[i] = line[0];
                drAssociation[i] = line[1];
            }
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("matrix.MakeDiDrMatrix().loadAssociationInfo().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private void makeMatrix() {
        for(int i = 0; i < diseaseNum; i++) {
            System.out.println(i + " / " + (diseaseNum-1));
            for(int j = 0; j < drugNum; j++) {
                
                for(int k = 0; k < associationNum; k++) {
                    if(diseaseList[i].equals(diAssociation[k]) && drugList[j].equals(drAssociation[k])) {
                        didr[i][j] = weight[k];
                        break;
                    }
                }
                
            }
        }
    }
    
    private void makeFile() {
        try {
            FileWriter fw = new FileWriter(global.Variables.matrix_path + "Disease-Drug_matrix.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile= new PrintWriter(bw);
            
            for(int i = 0; i < diseaseNum; i++) {
                outFile.print(didr[i][0]);
                for(int j = 1; j < drugNum; j++) {
                    outFile.print("\t" + didr[i][j]);
                }
                outFile.println();
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("ERROR: matrix.MakeDiDrMatrix().makeFile().BiffException");
            }
            ioe.printStackTrace();
        }
    }
    
    public void start() {
        makeMatrix();
        makeFile();
    }
    
}
