package CSI_2120_Project_Part_1;


import java.io.File;

public class SimilaritySearch{
    

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Please Enter a valid input such as: java SimilaritySearch <queryImageFilename> <imageDatasetDirectory>");
            System.exit(1);
        }

        String queryImageFilename = args[0];
        String imageDatasetDirectory = args[1];


        ColorImage queryImage = new ColorImage(queryImageFilename);


        int colorDepth = 3; 

        queryImage.reduceColor(colorDepth);


        ColorHistogram queryHistogram = new ColorHistogram(colorDepth);
        queryHistogram.setImage(queryImage);

        //double[] queryHist = queryHistogram.getHistogram();

        File data_set_Dir = new File(imageDatasetDirectory);
        File[] imgfiles = data_set_Dir.listFiles();

        double[] similarImages = new double[imgfiles.length];

        for(int i = 0; i < imgfiles.length; i= i + 2){
            if(imgfiles[i].isFile()){

                String filename = imgfiles[i].getPath();
                ColorHistogram data_set_hist = new ColorHistogram(filename);
                double similar_result = queryHistogram.compare(data_set_hist);
                similarImages[i] = similar_result;

            }

        }

        System.out.println("The 5 most similar images to the query image " + queryImageFilename + " are:");
        double[] final_answ_files = new double[5];
        int max_val = 0;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < similarImages.length;j++){

                if(final_answ_files[i] < similarImages[j]){
                    max_val = j;
                    final_answ_files[i] = similarImages[j];
                    
                }
            }
            System.out.println(imgfiles[max_val].getName() + "has similarity:" + similarImages[max_val]);

            similarImages[max_val] = -1.0;
        }
        
        
    }

}