/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package global;

/**
 *
 * @author Hyunjin Kim (chriskim@cs.yonsei.ac.kr)
 */
public class Variables {

    public final static boolean debuggingMode = false;      // Debug mode configuration
    
    public final static String geneListPath = System.getProperty("user.dir") + "/data/Entities/gene_list.txt";  // Gene symbols path
    public final static String diseaseListPath = System.getProperty("user.dir") + "/data/Entities/disease_list.txt";    // Disease names path
    public final static String drugListPath = System.getProperty("user.dir") + "/data/Entities/drug_list.txt";  // Drug names path
    public final static String medlinePath = System.getProperty("user.dir") + "/data/Medline/"; // Medline path
    
    public final static String association_path = System.getProperty("user.dir") + "/output/Associations/"; // Associations and weighted association path
    public final static String matrix_path = System.getProperty("user.dir") + "/output/Matrices/";  // Matrices path
    public final static String disease_disease_similarity_path = System.getProperty("user.dir") + "/output/Similarities/ddSim.dat"; // Disease-Disease similarities path (The result of this source code)
    
    public final static int entityTempLen = 50; // Storage threshold for extracting associations; If error occurred, make the size larger
    public final static int expStartYear = 1980;    // Medline start year
    public final static int expEndYear = 2012;  // Medline end year
    
}