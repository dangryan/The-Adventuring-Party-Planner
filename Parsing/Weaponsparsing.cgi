#! /usr/bin/env python

print "Content-type: text/html\n"

import MySQLdb, cgi
import xml.etree.ElementTree as ET

root = ET.parse(source='weapons.xml')

weapons = root.findall('item')

#establish DB connection
string = 'caps16_team39' 	#change this to yours
password = 'my+sql=caps16_team39'	#change this to yours
db_con = MySQLdb.connect(host='db.soic.indiana.edu', port = 3306, user=string, passwd=password, db=string)
cursor = db_con.cursor()

for weapon in weapons:
	print '<p>Weapon Added</p>'
	name = weapon.find('name').text
	type = weapon.find('type').text
	value = weapon.find('value').text
	weight = weapon.find('weight').text
	try:
		dmg1 = weapon.find('dmg1').text
	except:
		dmg1 = ''
	try:
		dmg2 = weapon.find('dmg2').text
	except:
		dmg2 = ''
	try:
		dmgType = weapon.find('dmgType').text
	except:
		dmgType = ''
	try:
		property = weapon.find('property').text
	except:
		property = ''
	try:
		range = weapon.find('range').text
	except:
		range = ''
	try:				#Always surround .execute with a try!
		SQL = 'INSERT INTO Weapon (name, type, value, weight, dmg1, dmg2, dmgType, property, dmgrange)'
		SQL += 'VALUES("' + name + '","' + type + '","' + value + '","' + weight + '","' + dmg1 + '","' + dmg2 + '","' + dmgType + '","' + property + '","' + range + '");'   
		cursor.execute(SQL)
		db_con.commit()            
	except Exception, e:		#Here we handle the error
		print "<p>Something went wrong with the SQL!</p>"
		print SQL
		print '\nError:', e
	else:				#This runs if there was no error
		print ('Yay, you did it.')