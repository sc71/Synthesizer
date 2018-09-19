package com.example.synthesizer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SynthesizerActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonA, buttonBb, buttonB, buttonC, buttonCs, buttonD, buttonDs, buttonE,
            buttonF, buttonFs, buttonG, buttonGs, buttonA2, scaleButton, buttonScaleE, buttonRepeat,
            buttonTwinkle, buttonPlaySong;
    private SoundPool soundPool;
    private int noteA, noteBb, noteB, noteC, noteCs, noteD, noteDs, noteE, noteF, noteFs, noteG, noteGs,
            noteA2, noteBb2, noteB2, noteC2, noteCs2, noteD2, noteDs2, noteE2, noteF2, noteFs2, noteG2,
            noteGs2, noteA3, noteB3;
    private Switch switchOctave;
    public static final float DEFAULT_VOLUME = 1.0f;
    public static final int DEFAULT_PRIORITY = 1;
    public static final float DEFAULT_RATE = 1.0f;
    public static final int WHOLE_NOTE = 500; //in milliseconds
    private boolean octave;
    private Note[] escale, scale, twinklestar1, twinklestar2, twinklestar, river1, river2, river;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesizer);
        wireWidgets();
        setListeners();
        initializeSoundPool();
        createNoteArrays();
    }
//    twinkleTwinkle.add(new Note(noteA));
//        twinkleTwinkle.add(new Note(noteA));
//        twinkleTwinkle.add(new Note(noteE2));
//        twinkleTwinkle.add(new Note(noteE2));
//        twinkleTwinkle.add(new Note(noteFs2));
//        twinkleTwinkle.add(new Note(noteFs2));
//        twinkleTwinkle.add(new Note(1000, noteE2));
//        twinkleTwinkle.add(new Note(noteD));
//        twinkleTwinkle.add(new Note(noteD));
//        twinkleTwinkle.add(new Note(noteCs));
//        twinkleTwinkle.add(new Note(noteCs));
//        twinkleTwinkle.add(new Note(noteB));
//        twinkleTwinkle.add(new Note(noteB));
//        twinkleTwinkle.add(new Note(1000, noteA));
    private void createNoteArrays() {
        escale = new Note[] {new Note(noteE), new Note(noteFs), new Note(noteG), new Note(noteA2),
                new Note(noteB2), new Note(noteCs2), new Note(noteD2), new Note(noteE2)};
        scale = new Note[] {new Note(noteA), new Note(noteBb), new Note(noteB), new Note(noteC),
                new Note(noteCs), new Note(noteD), new Note(noteDs), new Note(noteE), new Note(noteF),
                new Note(noteFs), new Note(noteG), new Note(noteGs), new Note(noteA2)};
        twinklestar1 = new Note[] {new Note(noteA),new Note(noteA), new Note(noteE2),
                new Note(noteE2), new Note(noteFs2), new Note(noteFs2)};
    }


    private void wireWidgets() {
        buttonA = findViewById(R.id.button_synth_a);
        buttonBb = findViewById(R.id.button_synth_bb);
        buttonB = findViewById(R.id.button_synth_b);
        buttonC = findViewById(R.id.button_synth_c);
        buttonCs = findViewById(R.id.button_synth_cs);
        buttonD = findViewById(R.id.button_synth_d);
        buttonDs = findViewById(R.id.button_synth_ds);
        buttonE = findViewById(R.id.button_synth_e);
        buttonF = findViewById(R.id.button_synth_f);
        buttonFs = findViewById(R.id.button_synth_fs);
        buttonG = findViewById(R.id.button_synth_g);
        buttonGs = findViewById(R.id.button_synth_gs);
        buttonA2 = findViewById(R.id.button_synth_a2);
        scaleButton = findViewById(R.id.button_main_scale);
        switchOctave = findViewById(R.id.switch_synth_oct);
        buttonScaleE = findViewById(R.id.button_synth_scale_e);
        buttonRepeat = findViewById(R.id.button_synth_num_pick);
        buttonTwinkle = findViewById(R.id.button_synth_twinkle);
        buttonPlaySong = findViewById(R.id.button_synth_river);
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
        noteA3 = soundPool.load(this, R.raw.scalehighesta, 1);
        noteB3= soundPool.load(this, R.raw.scalehighb, 1);
    }

    private void setListeners() {
        buttonA.setOnClickListener(this);
        buttonBb.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonCs.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonDs.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonFs.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonGs.setOnClickListener(this);
        buttonA2.setOnClickListener(this);
        scaleButton.setOnClickListener(this);
        buttonRepeat.setOnClickListener(this);
        buttonScaleE.setOnClickListener(this);
        buttonTwinkle.setOnClickListener(this);
        buttonPlaySong.setOnClickListener(this);
        switchOctave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    octave = true;
                }
                else{
                    octave = false;
                }
            }
        });
    }

    private class PlaySongBackground extends AsyncTask<Note, Void, Void>{
        @Override
        protected Void doInBackground(Note... notes) {
            Song s = new Song();
            for(Note n : notes){
                s.add(n);
            }
            playSong(s);
            return null;
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_main_scale:
                new PlaySongBackground().execute(scale);
                break;
            case R.id.button_synth_a:
                playNote(highOrLow(noteA, noteA2));
                break;
            case R.id.button_synth_bb:
                playNote(highOrLow(noteBb, noteBb2));
                break;
            case R.id.button_synth_b:
                playNote(highOrLow(noteB, noteB2));
                break;
            case R.id.button_synth_c:
                playNote(highOrLow(noteC, noteC2));
                break;
            case R.id.button_synth_cs:
                playNote(highOrLow(noteCs, noteCs2));
                break;
            case R.id.button_synth_d:
                playNote(highOrLow(noteD, noteD2));
                break;
            case R.id.button_synth_ds:
                playNote(highOrLow(noteDs, noteDs2));
                break;
            case R.id.button_synth_e:
                playNote(highOrLow(noteE, noteE2));
                break;
            case R.id.button_synth_f:
                playNote(highOrLow(noteF, noteF2));
                break;
            case R.id.button_synth_fs:
                playNote(highOrLow(noteFs, noteFs2));
                break;
            case R.id.button_synth_g:
                playNote(highOrLow(noteG, noteG2));
                break;
            case R.id.button_synth_gs:
                playNote(highOrLow(noteGs, noteGs2));
                break;
            case R.id.button_synth_a2:
                playNote(highOrLow(noteA2, noteA3));
                break;
            case R.id.button_synth_scale_e:
                new PlaySongBackground().execute(escale);
                break;
            case R.id.button_synth_num_pick:
                Intent myIntent = new Intent(SynthesizerActivity.this, PickNoteRepeat.class);
                SynthesizerActivity.this.startActivity(myIntent);
                break;
            case R.id.button_synth_twinkle:
                playStar();
                break;
            case R.id.button_synth_river:
                playRiverFlows();
                break;
        }

    }

    private int highOrLow(int note1, int note2) {
        if(octave) {
            soundPool.play(note2, 1.0f, 1.0f, 1, 0, 1.0f);
            return note2;
        }
        else{
            soundPool.play(note1, 1.0f, 1.0f, 1, 0, 1.0f);
            return note1;
        }
    }

    private void playSong(Song scale) {
        for(Note note : scale.getNotes()){
            playNote(note);
            delay(note.getDelay());
        }
    }

    private void playStar1(){
        Song twinkleTwinkle = new Song();
        twinkleTwinkle.add(new Note(noteA));
        twinkleTwinkle.add(new Note(noteA));
        twinkleTwinkle.add(new Note(noteE2));
        twinkleTwinkle.add(new Note(noteE2));
        twinkleTwinkle.add(new Note(noteFs2));
        twinkleTwinkle.add(new Note(noteFs2));
        twinkleTwinkle.add(new Note(1000, noteE2));
        twinkleTwinkle.add(new Note(noteD));
        twinkleTwinkle.add(new Note(noteD));
        twinkleTwinkle.add(new Note(noteCs));
        twinkleTwinkle.add(new Note(noteCs));
        twinkleTwinkle.add(new Note(noteB));
        twinkleTwinkle.add(new Note(noteB));
        twinkleTwinkle.add(new Note(1000, noteA));
        playSong(twinkleTwinkle);
    }

    private void playStar2(){
        Song twinkleTwinkle = new Song();
        twinkleTwinkle.add(new Note(noteE2));
        twinkleTwinkle.add(new Note(noteE2));
        twinkleTwinkle.add(new Note(noteD));
        twinkleTwinkle.add(new Note(noteD));
        twinkleTwinkle.add(new Note(noteCs));
        twinkleTwinkle.add(new Note(noteCs));
        twinkleTwinkle.add(new Note(1000, noteB));
        playSong(twinkleTwinkle);
    }
    private void playStar(){
        playStar1();
        playStar2();
        playStar2();
        playStar1();
    }
    private void delay(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playRiverRight(){
        Song river = new Song();
        river.add(new Note(400,noteA3));
        river.add(new Note(400,noteGs2));
        river.add(new Note(400,noteA3));
        river.add(new Note(400,noteGs2));
        river.add(new Note(400,noteA3));
        river.add(new Note(400,noteE2));
        river.add(new Note(400,noteA3));
        river.add(new Note(2600,noteD2));
        river.add(new Note(200,noteA2));
        river.add(new Note(200,noteCs2));
        river.add(new Note(400,noteA3));
        river.add(new Note(400,noteGs2));
        river.add(new Note(400,noteA3));
        river.add(new Note(400,noteGs2));
        river.add(new Note(400,noteA3));
        river.add(new Note(400,noteE2));
        river.add(new Note(400,noteA3));
        river.add(new Note(2600,noteD2));
        river.add(new Note(200,noteA2));
        river.add(new Note(200,noteCs2));
        //
        river.add(new Note(400,noteA3));
        river.add(new Note(200,noteGs2));
        river.add(new Note(200,noteA3));
        river.add(new Note(200,noteA2));
        river.add(new Note(200,noteGs2));

        playSong(river);
    }

    private void playRiverFlows(){
        playRiverRight();
    }
    private void playNote(int note, int loop) {
        soundPool.play(note, DEFAULT_VOLUME, DEFAULT_VOLUME, DEFAULT_PRIORITY, loop, DEFAULT_RATE);
    }
    private void playNote(Note note){
        playNote(note.getNoteId(), 0);
    }
    private void playNote(int note) {
        soundPool.play(note, DEFAULT_VOLUME, DEFAULT_VOLUME, DEFAULT_PRIORITY, 0, DEFAULT_RATE);
    }
}
