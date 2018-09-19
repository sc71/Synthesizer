package com.example.synthesizer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;

public class PickNoteRepeat extends AppCompatActivity implements View.OnClickListener{

    private int noteA, noteB, noteBb, noteC, noteCs, noteD, noteDs, noteE, noteF, noteFs,
            noteG, noteGs, noteA2, noteB2, noteBb2, noteC2, noteCs2, noteD2, noteDs2, noteE2,
            noteF2, noteFs2,noteG2,noteGs2;
    private NumberPicker picker1, picker2;
    private int valuePicker1, valuePicker2;
    private SoundPool soundPool;
    private Button buttonPlay, buttonReturn;
    public static final float DEFAULT_VOLUME = 1.0f;
    public static final int DEFAULT_PRIORITY = 1;
    public static final float DEFAULT_RATE = 1.0f;
    private int[] notesLowerOct, notesHigherOct;
    private Switch switchOctave;
    private Boolean octave;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        octave = false;
        wireWidgets();
        setListeners();
        initializeSoundPool();
        initializeNotes();
    }

    private void initializeNotes() {
        notesLowerOct = new int[] {noteA, noteB, noteBb, noteC, noteCs, noteD, noteDs, noteE, noteF,
                noteFs, noteG, noteGs};
        notesHigherOct = new int[] {noteA2, noteB2, noteBb2, noteC2, noteCs2, noteD2, noteDs2,
                noteE2, noteF2, noteFs2, noteG2, noteGs2};
    }


    private void setListeners() {
        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                valuePicker1 = picker1.getValue();
                Log.d("picker value", valuePicker1 + "");
            }
        });
        picker2.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                valuePicker2 = picker2.getValue();
            }
        });
        switchOctave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    octave = true;
                }
                else{
                    octave = false;
                }
            }
        });
        buttonReturn.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
    }

    private void wireWidgets() {
        picker1 = findViewById(R.id.numberpicker_picker_picker1);
        picker1.setMaxValue(4);
        picker1.setMinValue(0);
        String[] strings1 = {"1", "2", "3", "4", "5"};
        picker1.setDisplayedValues(strings1);
        //np.setWrapSelectorWheel(false);
        picker2 = findViewById(R.id.numberpicker_picker_picker2);
        picker2.setMaxValue(10);
        picker2.setMinValue(0);
        String[] strings = {"A", "B♭", "C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯"};
        picker2.setDisplayedValues(strings);
        buttonPlay = findViewById(R.id.button_picker_play);
        buttonReturn = findViewById(R.id.button_picker_back);
        switchOctave = findViewById(R.id.switch_picker_octave);
    }

    private void initializeSoundPool() {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        noteA = soundPool.load(this, R.raw.scalea, 1);
        noteB = soundPool.load(this, R.raw.scaleb, 1);
        noteBb = soundPool.load(this, R.raw.scalebb, 1);
        noteC = soundPool.load(this, R.raw.scalec, 1);
        noteCs = soundPool.load(this, R.raw.scalecs, 1);
        noteD = soundPool.load(this, R.raw.scaled, 1);
        noteDs = soundPool.load(this, R.raw.scaleds, 1);
        noteE = soundPool.load(this, R.raw.scalee, 1);
        noteF = soundPool.load(this, R.raw.scalef, 1);
        noteFs = soundPool.load(this, R.raw.scalefs, 1);
        noteG = soundPool.load(this, R.raw.scaleg, 1);
        noteGs = soundPool.load(this, R.raw.scalegs, 1);
        noteA2 = soundPool.load(this, R.raw.scalehigha, 1);
        noteB2 = soundPool.load(this, R.raw.scalehighb, 1);
        noteBb2 = soundPool.load(this, R.raw.scalehighbb, 1);
        noteC2 = soundPool.load(this, R.raw.scalehighc, 1);
        noteCs2 = soundPool.load(this, R.raw.scalehighcs, 1);
        noteD2 = soundPool.load(this, R.raw.scalehighd, 1);
        noteDs2 = soundPool.load(this, R.raw.scalehighds, 1);
        noteE2 = soundPool.load(this, R.raw.scalehighe, 1);
        noteF2 = soundPool.load(this, R.raw.scalehighf, 1);
        noteFs2 = soundPool.load(this, R.raw.scalehighfs, 1);
        noteG2 = soundPool.load(this, R.raw.scalehighg, 1);
        noteGs2 = soundPool.load(this, R.raw.scalehighgs, 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_picker_play:
                repeatedPlay(valuePicker1, valuePicker2);
                break;

            case R.id.button_picker_back:
                Intent myIntent = new Intent(PickNoteRepeat.this, SynthesizerActivity.class);
                PickNoteRepeat.this.startActivity(myIntent);
                break;
        }
    }

    private void repeatedPlay(int valuePicker1, int valuePicker2) {
        if(octave){
            playNote(notesHigherOct[valuePicker2], valuePicker1);
        }
        else{
            playNote(notesLowerOct[valuePicker2], valuePicker1);
        }
    }
    private void playNote(int note, int loop) {
        soundPool.play(note, DEFAULT_VOLUME, DEFAULT_VOLUME, DEFAULT_PRIORITY, loop, DEFAULT_RATE);
        Log.d("loop", loop + "");
    }
}
