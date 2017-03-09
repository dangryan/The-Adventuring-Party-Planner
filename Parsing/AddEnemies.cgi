#! /usr/bin/env python

print "Content-type: text/html\n"

import MySQLdb, cgi
import xml.etree.ElementTree as ET

root = ET.parse(source='monsters.xml')

monsters = root.findall('monster')

#establish DB connection
string = 'caps16_team39' 	#change this to yours
password = 'my+sql=caps16_team39'	#change this to yours
db_con = MySQLdb.connect(host='db.soic.indiana.edu', port = 3306, user=string, passwd=password, db=string)
cursor = db_con.cursor()

for elem in monsters:
	print '<p>Monster Added</p>'
	name = elem.find('name').text
	size = elem.find('size').text
	type = elem.find('type').text
	alignment = elem.find('alignment').text
	
	ac = elem.find('ac').text
	
	hp = elem.find('hp').text
	
	speed = elem.find('speed').text
	
	str = elem.find('str').text
	
	dex = elem.find('dex').text
	
	con = elem.find('con').text
	
	int = elem.find('int').text
	
	wis = elem.find('wis').text
	
	cha = elem.find('cha').text
	
	cr = elem.find('cr').text
	
	save = ''
	
	skill = ''
	
	senses = ''
	
	languages = ''
	
	immune = ''
	
	spells = ''
	
	resist = ''
	conditionImmune = ''
	
	try:
		save = elem.find('save').text
	except:
		save = ''
	try:
		skill = elem.find('skill').text
	except:
		skill = ''
	try:
		senses = elem.find('senses').text
	except:
		senses = ''
	try:
		languages = elem.find('languages').text
	except:
		languages = ''
	try:
		immune = elem.find('immune').text
	except:
		immune = ''
	try:
		spells = elem.find('spells').text
	except:
		spells = ''
	try:
		resist = elem.find('resist').text
	except:
		resist = ''
	try:
		conditionImmune = elem.find('conditionImmune').text
	except:
		conditionImmune = ''
	all_traits = elem.findall('trait')
	traits = ''
	for trait in all_traits:
		traits += trait.find('name').text + ', '
	all_actions = elem.findall('action')
	actions = ''
	for action in all_actions:
		actions += action.find('name').text + ', '
	all_legendary = elem.findall('legendary')	
	legendary = ''
	for legend in all_legendary:
		legendary += legend.find('name').text + ', '	
	try:				#Always surround .execute with a try!
		SQL = 'INSERT INTO Enemy (name, size, type, alignment, ac, hp, speed, str, dex, con, intel, wis, cha, save, skill, senses, languages, cr, immune, spells, resist, conditionImmune, traits, actions, legendary)'
		SQL += 'VALUES("' + name + '","' + size + '","' + type + '","' + alignment + '","' + ac + '","' + hp + '","' + speed + '","' + str + '","' + dex + '","' + con + '","' + int + '","' + wis + '","' + cha + '","' + save + '","' + skill + '","' + senses + '","' + languages + '","' + cr + '","' + immune + '","' + spells + '","' + resist + '","' + conditionImmune + '","' + traits + '","' + actions + '","' + legendary + '");'   
		cursor.execute(SQL)
		db_con.commit()            
	except Exception, e:		#Here we handle the error
		print "<p>Something went wrong with the SQL!</p>"
		print SQL
		print '\nError:', e
	else:				#This runs if there was no error
		print ('Yay, you did it.')
