package com.fn10.pastcrafter.datagen;

import javax.annotation.Nullable;

import com.fn10.pastcrafter.PastCrafer;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

//lets see how well i can make this by myself...
//yay it worked!!!

public class PCSoundDefinitionsP extends SoundDefinitionsProvider {

    //@Deprecated
    public static final SoundEvent PAST_EXTRACTER_START_SOUND = SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter_process_start"));
    //@Deprecated
    public static final SoundEvent PAST_EXTRACTER_TICK_SOUND = SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter_process_tick"));
    //@Deprecated
    public static final SoundEvent PAST_EXTRACTER_STOP_SOUND = SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter_process_finished"));

    protected PCSoundDefinitionsP(PackOutput output, ExistingFileHelper helper) {
        super(output, PastCrafer.MID, helper);
    }

    @Override
    public void registerSounds() {
        this.addSound(PAST_EXTRACTER_START_SOUND, "past_extracter_start", 1f,
                "pastcrafter.subtitle.past_extracter_process_start");
        this.addSound(PAST_EXTRACTER_TICK_SOUND, "past_extracter_tick", .5f,
                "pastcrafter.subtitle.past_extracter_process_tick");
        this.addSound(PAST_EXTRACTER_STOP_SOUND, "past_extracter_done", 1f,
                "pastcrafter.subtitle.past_extracter_process_end");
    }

    protected void addSound(SoundEvent SE, String SoundFileName, Float Volume, String Subtitle) {
        // Subtitle = Subtitle == null ? "" : Subtitle;
        // just add another method with no subtitle for like music
        this.add(SE, definition()
                .with(sound(ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, SoundFileName))
                        .volume(Volume))
                .subtitle(Subtitle));
    }

    protected void addMultaeSound(SoundEvent SE, Float Volume, String Subtitle, String... SoundFileNames) { // random
                                                                                                            // latin for
                                                                                                            // no reason

        // wow
        // thanks vs
        // code
        // formatting
        // thanks
        // for the 8
        // new lines
        // and even
        // more

        SoundDefinition def = definition();
        for (String sound : SoundFileNames) {
            def = def.with(sound(sound).volume(Volume));
        }

        this.add(SE, def
                .subtitle(Subtitle));
    }

    protected void addSound(SoundEvent SE, String SoundFileName, Float Volume, Integer Chance,
            @Nullable String Subtitle) {
        Subtitle = Subtitle == null ? "" : Subtitle;
        this.add(SE, definition()
                .with(sound(ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, SoundFileName))
                        .volume(Volume)
                        .weight(Chance))
                .subtitle(Subtitle));
    }

    protected void addSound(SoundEvent SE, String SoundFileName, Float Volume) {
        // Subtitle = Subtitle == null ? "" : Subtitle;
        // just add another method with no subtitle for like music
        this.add(SE, definition()
                .with(sound(ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, SoundFileName))
                        .volume(Volume)));
    }

}
