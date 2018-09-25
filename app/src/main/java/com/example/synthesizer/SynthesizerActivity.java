package com.example.synthesizer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.SystemClock;
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
            noteGs2, noteA3, noteB3, noteA0, noteBb0, noteB0, noteC0, noteCs0, noteD0, noteDs0, noteE0, noteF0, noteFs0, noteG0, noteGs0;
    private Switch switchOctave;
    public static final float DEFAULT_VOLUME = 1.0f;
    public static final int DEFAULT_PRIORITY = 1;
    public static final float DEFAULT_RATE = 1.0f;
    public static final int WHOLE_NOTE = 500; //in milliseconds
    private boolean octave;
    private Note[] escale, scale, twinklestar, riverRight, riverLeft;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesizer);
        initializeSoundPool();
        createNoteArrays();
        wireWidgets();
        setListeners();
    }

    private void createNoteArrays() {
        escale = new Note[] {new Note(noteE), new Note(noteFs), new Note(noteG), new Note(noteA2),
                new Note(noteB2), new Note(noteCs2), new Note(noteD2), new Note(noteE2)};
        scale = new Note[] {new Note(noteA), new Note(noteBb), new Note(noteB), new Note(noteC),
                new Note(noteCs), new Note(noteD), new Note(noteDs), new Note(noteE), new Note(noteF),
                new Note(noteFs), new Note(noteG), new Note(noteGs), new Note(noteA2)};
        twinklestar = new Note[] {new Note(noteA),new Note(noteA), new Note(noteE2),
                new Note(noteE2), new Note(noteFs2), new Note(noteFs2), new Note(1000, noteE2),
                new Note(noteD), new Note(noteD), new Note(noteCs), new Note(noteCs), new Note(noteB),
                new Note(noteB), new Note(1000, noteA), new Note(noteE2), new Note(noteE2),
                new Note(noteD), new Note(noteD), new Note(noteCs), new Note(noteCs),
                new Note(1000, noteB), new Note(noteE2), new Note(noteE2), new Note(noteD),
                new Note(noteD), new Note(noteCs), new Note(noteCs), new Note(1000, noteB),
                new Note(noteA),new Note(noteA), new Note(noteE2), new Note(noteE2), new Note(noteFs2),
                new Note(noteFs2), new Note(1000, noteE2), new Note(noteD), new Note(noteD),
                new Note(noteCs), new Note(noteCs), new Note(noteB), new Note(noteB), new Note(1000, noteA)};
        riverRight = new Note[] {new Note(400,noteA3), new Note(400,noteGs2),
                new Note(400,noteA3), new Note(400,noteGs2), new Note(400,noteA3),
                new Note(400, noteE2), new Note(400,noteA3), new Note(2600,noteD2),
                new Note(200,noteA2), new Note(200,noteCs2), new Note(400,noteA3),
                new Note(400,noteGs2), new Note(400,noteA3), new Note(400,noteGs2),
                new Note(400,noteA3), new Note(400,noteE2), new Note(400,noteA3),
                new Note(2600,noteD2), new Note(200,noteA2), new Note(200,noteCs2),
                new Note(400,noteA3), new Note(200,noteGs2), new Note(200,noteA3),
                new Note(200,noteA2), new Note(200,noteGs2), new Note(400,noteA3),
                new Note(200, noteA2), new Note(200,noteE2), new Note(400,noteA3),
                new Note(200,noteA2), new Note(200,noteD2), new Note(200,noteA),
                new Note(50, noteB2), new Note(350, noteCs2), new Note(400, noteD2),
                new Note(0, noteE2), new Note(400, noteA), new Note(400, noteCs2),
                new Note(0, noteGs), new Note(1200, noteB), new Note(200, noteA2),
                new Note(200, noteGs), new Note(1000, noteA2), new Note(200, noteE),
                new Note(200, noteA2), new Note(200, noteB2), new Note(1200, noteCs2),
                new Note(200, noteCs2), new Note(200, noteD2), new Note(1200, noteE2),
                new Note(200, noteD2), new Note(200, noteCs2), new Note(1400, noteB2),
                new Note(100, noteA2), new Note(100, noteCs2), new Note(400, noteA3),
                new Note(200, noteGs2), new Note(400, noteA3), new Note(200, noteA2),
                new Note(200, noteGs2), new Note(400, noteA3), new Note(200, noteA2),
                new Note(200, noteD2), new Note(200, noteA2)};
        riverLeft = new Note[] {new Note(400,noteFs0), new Note(400,noteCs),
                new Note(800,noteFs), new Note(400,noteD0), new Note(400,noteA),
                new Note(3300, noteE), new Note(400,noteFs0), new Note(400,noteCs),
                new Note(800,noteFs), new Note(400,noteD0), new Note(400,noteA),
                new Note(3300, noteE), new Note(400,noteFs0), new Note(400,noteCs),
                new Note(800,noteFs), new Note(400,noteD0),  new Note(400,noteA),
                new Note(400, noteE), new Note(400,noteD), new Note(400, noteA0),
                new Note(400, noteE0), new Note(800, noteCs), new Note(400, noteE0),
                new Note(400, noteB), new Note(800, noteE), new Note(400, noteFs0),
                new Note(400, noteCs), new Note(800, noteFs), new Note(400, noteD0),
                new Note(400, noteA), new Note(800, noteE), new Note(400, noteA0),
                new Note(400, noteE0), new Note(800, noteCs), new Note(400, noteE0),
                new Note(400, noteB), new Note(800, noteGs), new Note(400, noteFs0),
                new Note(400, noteCs), new Note(800, noteFs), //new Note(400, noteD0),
                //new Note(400, noteA), new Note(400, noteE), new Note(400, noteD0)
        };
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
        noteA0= soundPool.load(this, R.raw.scalelowesta, 1);
        noteBb0= soundPool.load(this, R.raw.scalelowesta, 1);
        noteB0 = soundPool.load(this, R.raw.scalelowestb, 1);
        noteC0 = soundPool.load(this, R.raw.scalelowestc, 1);
        noteCs0 = soundPool.load(this, R.raw.scalelowestcs, 1);
        noteD0 = soundPool.load(this, R.raw.scalelowestd, 1);
        noteDs0 = soundPool.load(this, R.raw.scalelowestds, 1);
        noteE0 = soundPool.load(this, R.raw.scaleloweste, 1);
        noteF0 = soundPool.load(this, R.raw.scalelowestf, 1);
        noteFs0 = soundPool.load(this, R.raw.scalelowestfs, 1);
        noteG0 = soundPool.load(this, R.raw.scalelowestg, 1);
        noteGs0 = soundPool.load(this, R.raw.scalelowestgs, 1);
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


    private void scheduleSongs(long startDelay, Note[]... songs) {
        long base = SystemClock.uptimeMillis() + startDelay;
        for (Note[] song: songs) {
            long delay = 0;
            for (final Note note: song) {
                handler.postAtTime(new Runnable() {
                    @Override
                    public void run() {
                        playNote(note);
                    }
                }, base + delay);
                delay+=note.getDelay();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_main_scale:
                scheduleSongs(100, scale);
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
                scheduleSongs(100, escale);
                break;
            case R.id.button_synth_num_pick:
                Intent myIntent = new Intent(SynthesizerActivity.this, PickNoteRepeat.class);
                SynthesizerActivity.this.startActivity(myIntent);
                break;
            case R.id.button_synth_twinkle:
                scheduleSongs(100, twinklestar);
                break;
            case R.id.button_synth_river:
                scheduleSongs(20, riverLeft, riverRight);
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
