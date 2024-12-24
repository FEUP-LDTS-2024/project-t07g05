package com.ldts.crystalclash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MusicTest {

    private Clip mockClip;
    private Music music;

    @BeforeEach
    void setUp() {
        mockClip = Mockito.mock(Clip.class);
        FloatControl mockGainControl = Mockito.mock(FloatControl.class);

        music = new Music(mockClip);

        when(mockClip.getControl(FloatControl.Type.MASTER_GAIN)).thenReturn(mockGainControl);
    }

    @Test
    void testSingleton() {
        Music instance1 = Music.getInstance();
        Music instance2 = Music.getInstance();

        assertSame(instance1, instance2, "Singleton should return the same instance");
    }

    @Test
    void testStartMusic() {
        music.startMusic();

        verify(mockClip, times(1)).setMicrosecondPosition(0);
        verify(mockClip, times(1)).start();
        verify(mockClip, times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    void testStopMusic() {
        music.stopMusic();

        verify(mockClip, times(1)).stop();
    }
}
