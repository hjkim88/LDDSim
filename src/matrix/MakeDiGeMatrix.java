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
public class MakeDiGeMatrix {
    
    private String[] geneList;
    private String[] diseaseList;
    private int[][] dige;
    private String[] diAssociation;
    private String[] geAssociation;
    private int[] weight;
    private int geneNum;
    private int diseaseNum;
    private int associationNum;
    
    public MakeDiGeMatrix() {
        initVariables();
    }
    
    private void initVariables() {
        if(global.Variables.debuggingMode == true) {
            System.out.println("matrix.MakeDiGeMatrix() ... Begins.");
        }
        
        loadEntities();
        loadAssociationInfo();
        
        dige = new int[diseaseNum][geneNum];
        for(int i = 0; i < diseaseNum; i++) {
            for(int j = 0; j < geneNum; j++) {
                dige[i][j] = 0;
            }
        }
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
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("matrix.MakeDiGeMatrix().loadEntities().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
     private void loadAssociationInfo() {
        try {
            File f = new File(global.Variables.association_path + "Gene-Disease.dat");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            associationNum = Integer.parseInt(br.readLine());
            diAssociation = new String[associationNum];
            geAssociation = new String[associationNum];
            weight = new int[associationNum];
            
            String[] line;
            for(int i = 0; i < associationNum; i++) {
                line = br.readLine().split("\t");
                weight[i] = Integer.parseInt(line[2]);
                diAssociation[i] = line[1];
                geAssociation[i] = line[0];
            }
            
            br.close();
            fr.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("matrix.MakeDiGeMatrix().loadAssociationInfo().IOException");
            }
            ioe.printStackTrace();
        }
    }
    
    private void makeMatrix() {
        for(int i = 0; i < diseaseNum; i++) {
            System.out.println(i + " / " + (diseaseNum-1));
            for(int j = 0; j < geneNum; j++) {
                
                for(int k = 0; k < associationNum; k++) {
                    if(diseaseList[i].equals(diAssociation[k]) && geneList[j].equals(geAssociation[k])) {
                        dige[i][j] = weight[k];
                        break;
                    }
                }
                
            }
        }
    }
    
    private void makeFile() {
        try {
            FileWriter fw = new FileWriter(global.Variables.matrix_path + "Disease-Gene_matrix.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile= new PrintWriter(bw);
            
            for(int i = 0; i < diseaseNum; i++) {
                outFile.print(dige[i][0]);
                for(int j = 1; j < geneNum; j++) {
                    outFile.print("\t" + dige[i][j]);
                }
                outFile.println();
            }
            
            outFile.close();
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            if(global.Variables.debuggingMode == true) {
                System.out.println("ERROR: matrix.MakeDiGeMatrix().makeFile().BiffException");
            }
            ioe.printStackTrace();
        }
    }
    
    public void start() {
        makeMatrix();
        makeFile();
    }
    
}
