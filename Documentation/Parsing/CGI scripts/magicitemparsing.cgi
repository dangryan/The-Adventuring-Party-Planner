#! /usr/bin/env python
#! Created by Josh Richardson 3/9/17
print "Content-type: text/html\n"

import MySQLdb, cgi
import xml.etree.ElementTree as ET

root = ET.parse(source='magicitems.xml')

magicitems = root.findall('item')

#establish DB connection
string = 'caps16_team39' 	#change this to yours
password = 'my+sql=caps16_team39'	#change this to yours
db_con = MySQLdb.connect(host='db.soic.indiana.edu', port = 3306, user=string, passwd=password, db=string)
cursor = db_con.cursor()

for item in magicitems:
	print '<p>Magic Item Added</p>'
	name = item.find('name').text
	type = item.find('type').text
	try:
		weight = item.find('weight').text
	except:
		weight = ''
	try:
		ac = item.find('ac').text
	except:
		ac = ''
	try:
		strength = item.find('strength').text
	except:
		strength = ''
	try:
		stealth = item.find('stealth').text
	except:
		stealth = 'NO'
	try:
		rarity = item.find('rarity').text
	except:
		rarity = ''
	try:
		dmg1 = item.find('dmg1').text
	except:
		dmg1 = ''
	try:
		dmg2 = item.find('dmg2').text
	except:
		dmg2 = ''
	try:
		dmgType = item.find('dmgType').text
	except:
		dmgType = ''
	try:
		property = item.find('property').text
	except:
		property = ''
	try:
		range = item.find('range').text
	except:
		range = ''
	try:				#Always surround .execute with a try!
		SQL = 'INSERT INTO MagicItem (name, type, weight, ac, strength, stealth, rarity, dmg1, dmg2, dmgType, property, dmgrange)'
		SQL += 'VALUES("' + name + '","' + type + '","' + weight + '","' + ac + '","' + strength + '","' + stealth + '","' + rarity + '","' + dmg1 + '","' + dmg2 + '","' + dmgType + '","' + property + '","' + range + '");'   
		cursor.execute(SQL)
		db_con.commit()            
	except Exception, e:		#Here we handle the error
		print "<p>Something went wrong with the SQL!</p>"
		print SQL
		print '\nError:', e
	else:				#This runs if there was no error
		print ('Yay, you did it.')
