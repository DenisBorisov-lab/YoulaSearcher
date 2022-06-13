package com.example.youlasearcher.services;

import static android.content.Context.MODE_PRIVATE;

import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import lombok.SneakyThrows;

public class FileService {

    private final static String FILE_NAME = "content.txt";

    public void writeData(String data){
        FileOutputStream fos = null;
        try {

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SneakyThrows
    public String readData(){
        return null;
    }
}
