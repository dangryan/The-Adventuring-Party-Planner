package theadventuringparty.theadventuringpartyplanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CampaignMotivation extends AppCompatActivity {
    Button generateButton;
    TextView motivationOutput;

    String motivations[] = {"A mountain lord is attacking small towns surrounded by his kingdom in hopes of expanding his army before his assault on the capital.",
            "Undead threaten cities across the nation. Who is responsible?",
            "The queen has been assassinated.",
            "Mysteriously, an entire city has lost its inhabitants overnight.",
            "A lich is raising a massive undead army.",
            "A god's weapon has fallen to earth and now there is a race to obtain it.",
            "Suddenly an entire country turns black.",
            "A previously righteous and good god suddenly calls genocide on all that are not his followers.",
            "A large tribe of Orcs are planning a raid on an Imperial city.",
            "The world is hit by an asteroid that mutates anyone that touches it.",
            "While crossing a sea, the boat is sunk by a monster of some variety. The characters are saved by merfolk but trapped on a deserted island miles from the mainland.",
            "A dragon captures the party and takes them to her mountain lair to feed to her young.",
            "Suspicion arises that the inhabitants of a town are gradually being replaced by impostors.",
            "Ships are disappearing within a five-mile radius of ocean.",
            "A mysterious curse is settling on town after town, rendering almost all forms of magic ineffective - e.g. ice and wind magic - while greatly empowering any who make use of black or demonic magic.",
            "A secret vigilante society is inflicting barbaric punishments on 'evil-doers.'",
            "A Kraken is destroying any ships that try to enter or leave a city's port.",
            "The party was once the elite task force for the King only to find out from a Duke they were sent to kill that the King is working for dark forces, gathering the shards of a crystal which his dark master is trapped in. They must now run as outlaws and try to stop this evil being from rising again.",
            "Each locked in a small cell with a single tiny window, fed the barest amounts of bread and water and deprived of their equipment, the party is held captive.",
            "A sorcerer seeks to uncover a massive ancient construct with world breaking power. The party is sent by the King to stop him. At the end the group must choose whether they should destroy the construction or hand it over to the King.",
            "The chosen Queen has arrived! She has more followers than one might expect for such a newly declared monarch. All her kin gather to serve her and reclaim their rightful place in the world.",
            "While exploring an old rundown castle, the group accidentally releases an ancient evil and, in the process, each member is possessed by a Demon. While trying to find a way to stop the creature they released from destroying all they know, they must each wage their own personal and internal war with the Demon whom possessed them to prevent the loss of their soul.",
            "The party arrives to new land in binds, ready to be delivered as slaves.",
            "The party is kidnapped by a great wyrm and tossed in the deepest layer of its dungeon.",
            "There is a cult in a city that has 100 chickens and the soul of a great hero is trapped in one of the chickens and they don't know which one it is so they worship all of them.",
            "There is an underground group of criminals that is branched into separate groups with the leader of each branch having a significant ring and once all gathered together it was unlocked their hidden magical properties.",
            "There are five shards of a legendary dagger hidden across the land and they are well hidden. If the party gathers them all they get a legendary prize.",
            "A fountain that spouts water which heals all sickness has erupted from the ground. Those who drink from it become dependent and violent and soon start killing anyone who approaches the font.",
            "A civilization which has been cut off for centuries offers to pay you handsomely if you bring them pieces of modern culture and technology.",
            "After a recent string of freak earthquakes, a ravine opens up near a small, remote village, with what appears to be a large fortress made entirely of dark stone at the bottom.",
            "Your party members are champions of the land for different reasons, but they don't remember why, and they all meet at a local tavern and discover each other, so they go questing to find out their past starting at square one, and their champion's gear and their memories are locked away in the final dungeon after each party member completes a trial built around their character",
            "Three hundred dead octopuses wash up on the shore of a lake. Dead marine life increasingly appears wherever the party go, no matter how far they are from water. Then, the dead creatures rise as undead.",
            "The Cloud Whales of ancient myth have unexpectedly returned, pulling the Chariot of Infinite Riches across the sky. They are peaceful, but are unintentionally causing havoc as gold coins fall from their chariot and rain down on greedy villagers. There must be a way to reason with them, but they’re all the way up in the air!",
            "All of the water in the land is draining away into the Elemental Plane of Water. A charismatic noble starts a rumor that one of the party members is the cause of the disaster; what is her ulterior motive?",
            "Late one night, a star falls to a mountaintop just outside town. As the nights pass, more and more stars plummet to the earth, and people fear that the fire elementals emerging from the fallen stars are just the vanguard of a much deadlier force.",
            "A group of dragon turtles has blockaded a port that the PCs desperately need to use. They speak politely, but their voices are mysteriously muted whenever they try to discuss why they have formed the blockade.",
            "The PCs awaken to find themselves seated around an opulent table full of exotic foods and wines. The food is beginning to mold, and insects have gathered. Nobody can recall what they’ve done in the past few days, and nobody knows where they are or who hosted the dinner. But there is the small matter of a well-dressed corpse with a dagger between its shoulder blades. And is that the sound of guards coming this way?"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign_motivation);

        generateButton = (Button) findViewById(R.id.generate_button);
        motivationOutput = (TextView) findViewById(R.id.campaignMotivationTextView);


        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index = random.nextInt(motivations.length);
                motivationOutput.setText(motivations[index]);
            }
        });
    }
}