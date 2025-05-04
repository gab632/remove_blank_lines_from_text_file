import java.io.*;



public class TextFileEditing {

    // remove blank lines and return the number of lines removed
    // create a new text file without blank lines in the same directory of the input file.
    public static int removeBlankLines(String inputFileName) throws IOException {

        if (inputFileName.isEmpty()) {
            return 0;
        }
        String extension = getExtension(inputFileName);
        String outputFileName = inputFileName.replace("." + extension, "_no_blank_lines." + extension);

        // check if the file is empty

        int linesRemoved = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    linesRemoved++;
                }
            }
        }
        return linesRemoved;
    }

    private static String getExtension(String inputFileName) {
        String extension = "";
        int i = inputFileName.lastIndexOf('.');
        if (i > 0) {
            extension = inputFileName.substring(i + 1);
        }
        return extension;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a filename as a command line argument");
            return;
        }

        // Get the filename from command line arguments
        String inputFileName = args[0];

        // Verify if the file exists
        File file = new File(inputFileName);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File does not exist: " + inputFileName);
            return;
        }
        int linesRemoved = removeBlankLines(inputFileName);
    }


}// class end
