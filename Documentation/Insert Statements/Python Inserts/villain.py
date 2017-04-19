import mysql.connector

#establish DB connection 
string = "caps16_team39" 
#change this to yours 
password = "chocolatehamburgers12" 
#change this to yours
cnx = mysql.connector.connect(host="db.soic.indiana.edu", user=string, passwd=password, db=string) 
cursor = cnx.cursor()

unique_trait = """A steep brow casting a shadow over his eyes
A maniacal laugh
A wry grin
A sharpened tooth
A charm with tokens from his favourite victims
A memento of his greatest triumph
A scar on his face
Unusual facial hair
Unusual clothing
A completely bald body
Albinism
A face that resembles a hawk
A face that resembles a wolf
A face that resembles a bear
A face that resembles a fox
A face that resembles a dragon
The voice of angels
The voice of demons
No voice
Scars covering his body
A missing ear
A missing eye
A missing arm
A missing nose
No eyes
Missing fingers
A foerign accent
Extra fingers
A smile no one can resist
Never been seen in his true form
Wrong coloured skin
A legendary weapon
A gaze that can see into your soul
A permanent frown
Exaggerated features
A massive statue
A diminutive stature
A full body of armor
A completely painted face
A face painted with tribal markings
A face painted war paint
A partially hidden face
A face hidden in the shadows
No pupils
No special features. He could be anyone
A magical artefact
A golden broach
A flowing cape
A memento of his last victim
Sigils of his station"""

race = """
    Human
    Goblin
    Dragon
    Orc
    Ogre
    Kobold
    Lich
    Fiend
    Celestial
    Banshee
    Beholder
    Centaur
    Devil
    Doppelganger
    Dracolich
    Dryad
    Duergar
    High-Elf
    Wood-Elf
    Dark-Elf
    Mountain Dwarf
    Hill Dwarf
    Gnome
    God
    Deity
    Ghost
    Giant
    Half-Dragon
    Lamia
    Lizardfolk
    Lycanthrope
    Medusa
    Mind Flayer
    Naga
    Faerie
    Vampire
    Wraith
    Yuan-ti
    Tiefling
    Avatar of a God
    Hobgoblin
    Half-Orc
    Wraith
    Banshee
    Merfolk
    Half-Dragon
    Svirfneblin
    Centaur
    Planar Deity
    Drider"""

leadership_quality = """
    Charisma. His followers love him unquestioningly
    Charisma. He has a silver tongue
    Enchantment. His followers are all under his command
    Legend. Stories of him may be bigger than he is
    Force of will. There is no better alternative
    Terror. Join him or die
    Greed. Join him and be well rewarded
    Eternity. Join him and be rewarded in the afterlife
    Intelligence. He has calculated his every move. To oppose him would be folly
    Omniscience. If you don't follow, he will know.
    Strength. He is the most powerful individual
    Strength. He commands the greatest armies
    Strength. He rules the greatest empire
    Intelligence. His followers don't know they follow him
    Force. He can not be defeated
    Loyalty. His subjects revere his station
    Profits. He pays his minions well
    Damnation. Follow him, or be damned in the afterlife
    Promises of Power.
    Manipulation. Everyone is a pawn of a bigger game"""


reason_turned = """He was betrayed by a close friend
He was betrayed by a lover
He was betrayed by a parent
He was betrayed by the priesthood
He was betrayed by the nobility
He was betrayed by the gods
He was cast out of his family
He was cast out of his village
He was cast out of society
He was cast out by his peers
He was cast out of his chosen profession
He was cast out of a noble house
His family was killed by an intruder
His family was killed by the nobility
His family was killed by an unknown person
He was nearly killed and left for dead
He was the subject of grotesque experiments
He was forced to fight for his survival against all odds.
He discovered an unbearable truth
He was pushed beyond his limits and went mad
He believes he is the rightful heir to the kingdom
He believes he is the rightful owner of an artefact
His true love was taken from him
He believes everyone is out to destroy him
He is overwhelmed with power and driven mad
He got a taste of wealth and become obsessed with gaining more
He got a taste of power and become obsessed with gaining more
He believes he is the only one to save a people at all costs
He believes he must save a people from themselves
He got lost on a mission to prove his love to another
He got a taste of forbidden knowledge, and it drove him mad
He got a taste of forbidden knowledge, and became obsessed with learning more
He learned of a dark secret, and it seduced him
He was seduced by the love of an evil woman
He was seduced by the lust of an evil woman
He was seduced then betrayed by a woman
He was taken in, then cast out by a father/mother figure
He was taken in, then seduced by a personal hero
He is bound by a code to do good, and will achieve the ultimate good even at all costs
He is constantly on the defence from usurpers
He was passed over by a woman that he loved, who never noticed him
He came to believe himself to be an important albeit evil part of balance
He became seduced by the idea that the world was too good, and stagnant. He took on a mantle of opposition to instigate change
It was simply fun
He was driven mad by his own experiment
He was driven mad by continuing to fail at an experiment
He learned of a prophesy that predicted the loss of his power
He learned of a prophesy that predicted the loss of his wealth
He learned of a prophesy that predicted the loss of his spouse/lover
He learned of a prophesy that predicted the loss of his children
He learned of a prophesy that predicted the loss of his parents
He learned of a prophesy that predicted the loss of his children
He learned of a prophesy that predicted his death
He learned of a prophesy that predicted the loss of his station
He learned of a prophesy that predicted the loss of his influence
He learned of his successor
He was promised great power
He was promised great wealth
He was promised a great lover
He is not evil, and never turned. He is just perceived that way
He is not inherently evil, it is just in his nature
He is not inherently evil, that is just the way of his society
He became possessed by an evil entity
He was pushed to the edge by boredom
He was pushed to the edge by the lack of a physical challenge
He was pushed to the edge by the lack of an intellectual challenge
It was prophesied, and he embraced his destiny
It was prophesied, and ironically in fighting his destiny he became what he feared
He did not choose evil, but he is the most skilled in an evil society
He did not choose evil, but he is the most powerful of an evil society
His mom didn't love him enough
He just wants to see the world burn
A special item was stolen from him
His parents were kidnapped
A sibling was kidnapped
A child was kidnapped
He was kidnapped
A close friend was kidnapped
He was caught up in a deep conspiracy
He was groomed from an early age
He was brainwashed
He was tortured to the breaking point
He has seen too many horrors
He was passed over by his parents in favour of his less deserving sibling
He felt the need to prove himself to his parents
He started listening to the voices in his head
He had an overdeveloped sense of personal space
He grew extremely paranoid
He became partially separated from the material world
He visited a a plane of torment
He developed a sense of intense loneliness.
He developed a fear that everyone was going to leave him
He developed a fear that no one would ever love him
He was separated from the world for a very long time
He was secluded from the world since birth
He wanted his name to go down in the ages
He didn't realize he was doing evil, he was just protecting himself
He was going to make someone listen at any cost
He had no choice. He was being compelled
He was out to prove something, and went too far"""


try:
    #Always surround .execute with a try!
    #SQL = "INSERT INTO Villain (Name, Title, Email, Areas)"
    #SQL += "VALUES('" + name + "','" + title + "','" + email + "','" SQL += areas + "');"
    #cursor.execute(SQL)
    #db_con.commit()           
    #SQL = "SELECT * FROM Enemy; "
    #cursor.execute(SQL)
    #results = cursor.fetchall()
    #cnx.commit()
    #cursor.close()
    #cnx.close()
    print ('Good work!')
except Exception, e: 
    #Here we handle the error 
    print ('Something went wrong with the SQL!')
    print ("\nError:", e)
