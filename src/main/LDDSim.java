/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Hyunjin Kim (chriskim@cs.yonsei.ac.kr)
 */
public class LDDSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i = global.Variables.expStartYear; i <= global.Variables.expEndYear; i++) {
            new association.ExtractAssociation(i).start();  // Make associations using entities(disease, gene, drug) and medline literatures [Required time: about 5 days (1980-2012)]
        }
        new association.WeightCalculation().start();    // Compute frequency of occurrences of relations [Required time: about 10 mins]
        new matrix.MakeDiGeMatrix().start();    // Make disease-gene matrix [Required time: about 8 days]
        new matrix.MakeDiDrMatrix().start();    // Make disease-drug matrix [Required time: about 2 days]
        new similarity.DDSimWithMatrix().start();   // Calculate disease-disease similarities [Required time: about 20 hours]
    }
}
