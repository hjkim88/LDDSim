LDDSim: A literature-driven method to calculate similarities among diseases

Hyunjin Kim (chriskim@cs.yonsei.ac.kr)
Youngmi Yoon (ymyoon@gachon.ac.kr)
Jaegyoon Ahn (jgahn@ucla.edu)
Sanghyun Park (sanghyun@cs.yonsei.ac.kr)

Department of Computer Science, Yonsei University

--------------------------------------------------------------------------------------------------------------------------

The source code for LDDSim (Literature-Driven Disease Similarities)
is implemented by JAVA programming language with JDK 7 in NetBeans IDE 7.4 (http://www.netbeans.org/)

You must have at least 12GB memory to run this code

Parameters for experiment are in the global.Variables.java
All the data which are used in the experiment are in the "Kim_LDDSim/data/" folder

--------------------------------------------------------------------------------------------------------------------------

Running example:
[Changing parameters in global.Variables.java is needed]

(1) To extract disease-gene relations and disease-drug relations
    new association.ExtractAssociation("Year").start();

    INPUT
    global.Variables.medlinePath 
    = "Specify the medline files path"; [Default: System.getProperty("user.dir") + "/data/Medline/"]
    global.Variables.geneListPath
    = "Specify the gene symbols path"; [Default: System.getProperty("user.dir") + "/data/Entities/gene_list.txt"]
    global.Variables.diseaseListPath
    = "Specify the disease names path"; [Default: System.getProperty("user.dir") + "/data/Entities/disease_list.txt"]
    global.Variables.drugListPath
    = "Specify the drug names path"; [Default: System.getProperty("user.dir") + "/data/Entities/drug_list.txt"]
    global.Variables.association_path
    = "Specify the output relations directory path"; [Default: System.getProperty("user.dir") + "/output/Associations/"]
    global.Variables.entityTempLen
    = "Specify the storage threshold"; [Default: 50]

    OUTPUT
    1. Disease-Gene relations [Gene-Disease("Year").dat]
    2. Disease-Drug relations [Disease-Drug("Year").dat]
    
(2) To compute frequency of occurrences of relations
    new association.WeightCalculation().start();
    
    INPUT
    global.Variables.association_path
    = "Specify the relations directory path & output directory path"; [Default: System.getProperty("user.dir") + "/output/Associations/"]
    global.Variables.expStartYear
    = "Specify the start year of the medline literature"; [Default: 1980]
    global.Variables.expEndYear
    = "Specify the end year of the medline literature"; [Default: 2012]

    OUTPUT
    1. Weighted Disease-Gene relations [Gene-Disease.dat]
    2. Weighted Disease-Drug relations [Disease-Drug.dat]
    
(3) To make Disease-Gene matrix
    new matrix.MakeDiGeMatrix().start();
    
    INPUT
    global.Variables.geneListPath
    = "Specify the gene symbols path"; [Default: System.getProperty("user.dir") + "/data/Entities/gene_list.txt"]
    global.Variables.diseaseListPath
    = "Specify the disease names path"; [Default: System.getProperty("user.dir") + "/data/Entities/disease_list.txt"]
    global.Variables.association_path
    = "Specify the Disease-Gene.dat directory path"; [Default: System.getProperty("user.dir") + "/output/Associations/"]
    global.Variables.matrix_path
    = "Specify the output disease-gene matrix path"; [Default: System.getProperty("user.dir") + "/output/Matrices/"]
    
    OUTPUT
    1. Disease-Gene matrix [Disease-Gene_matrix.dat]
    
(4) To make Disease-Drug matrix
    new matrix.MakeDiDrMatrix().start();
    
    INPUT
    global.Variables.drugListPath
    = "Specify the drug names path"; [Default: System.getProperty("user.dir") + "/data/Entities/drug_list.txt"]
    global.Variables.diseaseListPath
    = "Specify the disease names path"; [Default: System.getProperty("user.dir") + "/data/Entities/disease_list.txt"]
    global.Variables.association_path
    = "Specify the Disease-Drug.dat directory path"; [Default: System.getProperty("user.dir") + "/output/Associations/"]
    global.Variables.matrix_path
    = "Specify the output disease-drug matrix path"; [Default: System.getProperty("user.dir") + "/output/Matrices/"]
    
    OUTPUT
    1. Disease-Gene matrix [Disease-Drug_matrix.dat]

(5) To calculate disease-disease similarities with the matrices

    INPUT
    global.Variables.geneListPath
    = "Specify the gene symbols path"; [Default: System.getProperty("user.dir") + "/data/Entities/gene_list.txt"]
    global.Variables.diseaseListPath
    = "Specify the disease names path"; [Default: System.getProperty("user.dir") + "/data/Entities/disease_list.txt"]
    global.Variables.drugListPath
    = "Specify the drug names path"; [Default: System.getProperty("user.dir") + "/data/Entities/drug_list.txt"]
    global.Variables.matrix_path
    = "Specify the disease-gene matrix path"; [Default: System.getProperty("user.dir") + "/output/Matrices/"]
    global.Variables.matrix_path
    = "Specify the disease-drug matrix path"; [Default: System.getProperty("user.dir") + "/output/Matrices/"]
    global.Variables.disease_disease_similarity_path
    = "Specify the output disease-disease similarity path"; [Default: System.getProperty("user.dir") + "/output/Similarities/ddSim.dat"]
    
    OUTPUT
    1. Disease-Disease similarities [ddSim.dat]

(6) To calculate disease-disease similarities with entities list & medline data
    (1) + (2) + (3) + (4) + (5)
    
--------------------------------------------------------------------------------------------------------------------------

Expected running time: 
(Fedora Release 10 with Kernel Linux 2.6.27.41-170.2.117.fc10.x86_64 and 31.5GB memory)

(1) To extract disease-gene relations and disease-drug relations (1980-2012)	 = about 5 days
(2) To compute frequency of occurrences of relations				 = about 10 mins
(3) To make Disease-Gene matrix							 = about 8 days
(4) To make Disease-Drug matrix							 = about 2 days
(5) To calculate disease-disease similarities with the matrices			 = about 20 hours
(6) To calculate disease-disease similarities with entities list & medline data	 = about 16 days
