package org.phl0w.aurora.itmaples;

import ms.aurora.api.event.EventHandler;
import ms.aurora.api.event.MessageEvent;
import ms.aurora.api.event.PaintEvent;
import ms.aurora.api.methods.Skill;
import ms.aurora.api.script.ActionScript;
import ms.aurora.api.script.ScriptManifest;
import ms.aurora.api.util.Timer;
import org.phl0w.aurora.itmaples.actions.Antiban;
import org.phl0w.aurora.itmaples.actions.Banking;
import org.phl0w.aurora.itmaples.actions.Chop;
import org.phl0w.aurora.itmaples.utilities.Variables;

import java.awt.*;
import java.util.logging.Logger;

@ScriptManifest(name = "iTMaples", version = 1.0d, author = "_phl0w", shortDescription = "Chops maples in Seer's village")
public class iTMaples extends ActionScript {

    public static final Logger log = Logger.getLogger("iTMaples");

    @Override
    public void onStart() {
        log.info("Starting iTMaples by _phl0w");
        Variables.status = "Starting up";
        Variables.startTimer = new Timer();
        Variables.startLevel = Skill.WOODCUTTING.getBaseLevel();
        Variables.startXp = Skill.WOODCUTTING.getExperience();
        submit(new Chop());
        submit(new Banking());
        submit(new Antiban());
    }

    @EventHandler
    public void messageReceived(final MessageEvent m) {
        final String msg = m.getMessage();
        if (msg.contains("get some maple logs")) {
            Variables.chopped++;
        }
    }

    @EventHandler
    public void onRepaint(final PaintEvent p) {
        final Graphics2D g = p.getGraphics();
        org.phl0w.aurora.itmaples.utilities.Paint.paint(g);
    }
}
