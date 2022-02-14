package com.rnv.letscode.utils;

import com.rnv.letscode.domain.Rebelde;
import com.rnv.letscode.ic.InteligenciaCentral;
import lombok.Cleanup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {

    public static void gravarArquivo(ArrayList<Rebelde> rebeldes) {
        File arq = new File("Rebeldes.dat");
        try {
            arq.delete();
            arq.createNewFile();

            @Cleanup ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(rebeldes);
        } catch (FileNotFoundException e) {
            InteligenciaCentral.LOGGER.error("Arquivo de cadastro de rebeldes inexistente");
        } catch (UnsupportedEncodingException e) {
            InteligenciaCentral.LOGGER.error(e.getMessage());
        } catch (IOException e) {
            InteligenciaCentral.LOGGER.error(e.getMessage());
        }
    }

    public static ArrayList<Rebelde> lerArquivo() throws FileNotFoundException {
        ArrayList<Rebelde> rebeldes = new ArrayList<>();
        try {
            File arq = new File("Rebeldes.dat");
            if ( arq.exists() ){
                @Cleanup ObjectInputStream objInput = new ObjectInputStream( new FileInputStream(arq));
                rebeldes = (ArrayList<Rebelde>) objInput.readObject();
            }
        } catch (IOException e) {
            InteligenciaCentral.LOGGER.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            InteligenciaCentral.LOGGER.error(e.getMessage());
        }
        return rebeldes;
    }
}
