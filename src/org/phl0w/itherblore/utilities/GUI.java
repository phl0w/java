package org.phl0w.itherblore.utilities;

import org.phl0w.itherblore.iTHerblore;
import org.phl0w.itherblore.utilities.enums.Job;
import org.phl0w.itherblore.utilities.user.Variables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * iTHerblore 2
 * GUI.java
 * Purpose: creating and showing the GUI.
 *
 * @author _phl0w
 * @version 1.2
 * @since 17/04/2013
 */
public class GUI extends JFrame {

    public GUI() {
        super("GUI");
        initComponents();
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private static final JButton START_BUTTON = new JButton("Start");
    private static final JLabel ACTION_LABEL = new JLabel("Select action:");
    private static final JRadioButton MIX_POTIONS_BUTTON = new JRadioButton("Mix potions");
    private static final JRadioButton CLEAN_HERBS_BUTTON = new JRadioButton("Clean herbs");
    private static JComboBox<String> itemList = new JComboBox<>();

    private void initComponents() {

        MIX_POTIONS_BUTTON.setSelected(true);

        itemList.setModel(new DefaultComboBoxModel<>(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion", "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Extreme Attack", "Extreme Defence", "Extreme Magic", "Extreme Ranging", "Extreme Strength", "Fishing Potion", "Fletching Potion", "Hunter Potion", "Magic Potion", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Recover Special", "Restore Potion", "Saradomin Brew", "Serum 207", "Strength Potion", "Summoning Potion", "Super Antifire", "Super Antipoison", "Super Attack", "Super Defence", "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew", "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam", "Unfinished Harralander", "Unfinished Irit", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill", "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Toadflax", "Unfinished Torstol", "Unfinished Wergali"}));

        MIX_POTIONS_BUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cleanHerbsButtonAction();
            }
        });

        CLEAN_HERBS_BUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mixPotionButtonAction();
            }
        });

        START_BUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startButtonAction();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CLEAN_HERBS_BUTTON)
                                        .addComponent(ACTION_LABEL)
                                        .addComponent(MIX_POTIONS_BUTTON)
                                        .addComponent(itemList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(START_BUTTON, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ACTION_LABEL)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MIX_POTIONS_BUTTON)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CLEAN_HERBS_BUTTON)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(START_BUTTON)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void mixPotionButtonAction() {
        MIX_POTIONS_BUTTON.setSelected(false);
        CLEAN_HERBS_BUTTON.setSelected(true);
        itemList.setModel(new DefaultComboBoxModel<>(new String[]{"Guam", "Marrentil", "Tarromin", "Harralander", "Ranarr", "Toadflax", "Spirit Weed", "Irit", "Wergali", "Avantoe", "Kwuarm", "Snapdragon", "Cadantine", "Lantadyme", "Dwarf Weed", "Torstol", "Fellstalk"}));
    }

    private void cleanHerbsButtonAction() {
        MIX_POTIONS_BUTTON.setSelected(true);
        CLEAN_HERBS_BUTTON.setSelected(false);
        itemList.setModel(new DefaultComboBoxModel<>(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion", "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Extreme Attack", "Extreme Defence", "Extreme Magic", "Extreme Ranging", "Extreme Strength", "Fishing Potion", "Fletching Potion", "Hunter Potion", "Magic Potion", "Overload", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Recover Special", "Restore Potion", "Saradomin Brew", "Serum 207", "Strength Potion", "Summoning Potion", "Super Antifire", "Super Antipoison", "Super Attack", "Super Defence", "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew", "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam", "Unfinished Harralander", "Unfinished Irit", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill", "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Toadflax", "Unfinished Torstol", "Unfinished Wergali"}));

    }

    private void startButtonAction() {
        Variables.current = (MIX_POTIONS_BUTTON.isSelected() ? Job.MIX : Job.CLEAN);
        iTHerblore.setTree(Variables.current);
        System.out.println(Variables.current.name());
        final String selected = (String) itemList.getSelectedItem();
        switch (Variables.current) {
            case MIX:
                final int[] ingredients = Methods.getIngredients(selected);
                Variables.primary = ingredients[0];
                Variables.secondary = ingredients[1];
                System.out.println("prim: " + Variables.primary + ", secon: " + Variables.secondary);
                break;
            case CLEAN:
                Variables.primary = Methods.getHerb(selected);
                System.out.println("prim: " + Variables.primary);
                break;
        }
        dispose();
        Variables.guiDone = true;
    }
}

