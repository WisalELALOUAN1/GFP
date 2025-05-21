package com.example.gfp.ui;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TFLitePredictor {
    private static final String TAG = "TFLitePredictor";
    private Interpreter interpreter;

    // Ces valeurs doivent correspondre aux bornes utilisées dans MinMaxScaler en Python
    private final float minAmount = 1.75f;
    private final float maxAmount = 100000f;

    public TFLitePredictor(Context context) {
        try {
            MappedByteBuffer model = loadModelFile(context, "budget_model.tflite");
            interpreter = new Interpreter(model);
        } catch (IOException e) {
            Log.e(TAG, "Erreur chargement modèle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadModelFile(Context context, String filename) throws IOException {
        AssetFileDescriptor fileDescriptor = context.getAssets().openFd(filename);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    /**
     * Prédit le montant d'une seule catégorie.
     */
    public float predictAmount(int category, int month, int dayOfWeek, int account) {
        if (interpreter == null) return -1f;
        float[][] input = {{category, month, dayOfWeek, account}};
        float[][] output = new float[1][1];
        interpreter.run(input, output);

        // Inverse la normalisation (sigmoid) pour récupérer le montant réel en DH
        return output[0][0] * (maxAmount - minAmount) + minAmount;
    }

    /**
     * Prédit plusieurs catégories à la fois.
     * @param categories liste d'encodages de catégories
     * @param month mois courant
     * @param dayOfWeek jour de la semaine (0 = Lundi)
     * @param account encodage du compte
     * @return une Map encodageCatégorie → montant prédit
     */
    public Map<Integer, Float> predictBatch(List<Integer> categories, int month, int dayOfWeek, int account) {
        Map<Integer, Float> results = new HashMap<>();
        if (interpreter == null) return results;

        for (int cat : categories) {
            float prediction = predictAmount(cat, month, dayOfWeek, account);
            results.put(cat, prediction);
        }
        return results;
    }
}
