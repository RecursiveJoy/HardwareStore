# HardwareStore
HardwareStore project for Object Oriented Programming course

Show all existing items records in the database (sorted by ID number). \n
Add new item (or quantity) to the database.\n
Delete an item from a database.\n
Search for an item given its name.\n
Show a list of users in the database.\n
Add new user to the database.\n
Update user info (given their id).\n
Complete a sale transaction.\n
Show completed sale transactions.\n
Exit program.\n

The hardware store has two user types, customer and employee and two types of inventory: hardware and appliances. It also runs and keeps track of sale transactions. 
Customer and Employee classes both inherit from class User and Hardware and Appliance classes inherit from class Item. 
There is a database manager class for each of these superclasses that uses an ArrayList (similar to a vector) to hold entries.
There is a Menu class that runs the program, presenting the user with a list of options and has a method that executes the options.
There is a Transaction class that holds the information for one transaction and uses an ArrayList to hold the customer's order.
There is a Transaction Manager class that runs each transaction and keeps a record of all transactions in an ArrayList.
There is a Date class that holds month day and year and produces a valid date in DD/MM/YYYY format.
