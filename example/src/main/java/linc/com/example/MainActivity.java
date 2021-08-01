package linc.com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import linc.com.amplituda.Amplituda;
import linc.com.amplituda.exceptions.io.AmplitudaIOException;
import linc.com.amplituda.exceptions.processing.AmplitudaProcessingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Amplituda amplituda = new Amplituda.Builder()
                .enableExtendedProcessing(this)
                .setErrorListener(error -> {
                    error.printStackTrace();
                })
                .setLogConfig(Log.ERROR, true)
                .build();


//        amplituda.fromFile("/storage/9016-4EF8/MUSIC/Hosini - Froozen.mp3")
        Thread processing = new Thread(() -> {
            amplituda.fromFile("http://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3");
        });

        processing.start();
        try {
            processing.join();
            amplituda.amplitudesAsJson(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*Amplituda amplituda = new Amplituda(this);
        amplituda.fromFile("/storage/emulated/0/Music/Linc - Amplituda.mp3")
                .setErrorListener(error -> {
                    if(error instanceof AmplitudaIOException) {
                        System.out.println("IO exception!");
                    } else if(error instanceof AmplitudaProcessingException) {
                        System.out.println("Processing exception!");
                    }
                })
                .setLogConfig(Log.DEBUG, true)
                .compressAmplitudes(1)
                .amplitudesAsJson(json -> {
                    System.out.println("As json: " + json);
                })
                .amplitudesAsList(list -> {
                    System.out.print("As list: " + Arrays.toString(list.toArray()));
                })
                .amplitudesAsSequence(Amplituda.SINGLE_LINE_SEQUENCE_FORMAT, defSeq -> {
                    System.out.println("As sequence default: " + defSeq);
                })
                .amplitudesAsSequence(Amplituda.SINGLE_LINE_SEQUENCE_FORMAT, " * ", custSeq -> {
                    System.out.println("As sequence custom: " + custSeq);
                })
                .amplitudesAsSequence(Amplituda.NEW_LINE_SEQUENCE_FORMAT, newLineSeq -> {
                    System.out.println("As new line sequence: " + newLineSeq);
                })
                .amplitudesForSecond(1, amps -> {
                    System.out.print("For second: " + Arrays.toString(amps.toArray()));
                });*/
    }
}
