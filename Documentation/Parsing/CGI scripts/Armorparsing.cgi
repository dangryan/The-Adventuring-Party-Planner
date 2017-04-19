#! /usr/bin/env python
#!Created by Josh Richardson 3/9/17
print "Content-type: text/html\n"

import MySQLdb, cgi
import xml.etree.ElementTree as ET

root = ET.parse(source='armor.xml')

armors = root.findall('item')

#establish DB connection
string = 'caps16_team39' 	#change this to yours
password = 'my+sql=caps16_team39'	#change this to yours
db_con = MySQLdb.connect(host='db.soic.indiana.edu', port = 3306, user=string, passwd=password, db=string)
cursor = db_con.cursor()

for armor in armors:
	print '<p>Armor Added</p>'
	name = armor.find('name').text
	type = armor.find('type').text
	value = armor.find('value').text
	weight = armor.find('weight').text
	ac = armor.find('ac').text
	try:
		stealth = armor.find('stealth').text
	except:
		stealth = 'NO'
	try:				#Always surround .execute with a try!
		SQL = 'INSERT INTO Armor (name, type, value, weight, ac, stealth)'
		SQL += 'VALUES("' + name + '","' + type + '","' + value + '","' + weight + '","' + ac + '","' + stealth + '");'   
		cursor.execute(SQL)
		db_con.commit()            
	except Exception, e:		#Here we handle the error
		print "<p>Something went wrong with the SQL!</p>"
		print SQL
		print '\nError:', e
	else:				#This runs if there was no error
		print ('Yay, you did it.')
