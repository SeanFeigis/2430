## Author Information

* Name: Sean Shaya Feigis
* Email: sfeigis@uoguelph.ca    
* Student ID: 1096849

## How to operate your program
$mvn compile
    -compiles the program
$mvn assembly:assembly
    -makes the jar file
$mvn exec:java
    -runs the program
$mvn java -jar ./target/2430_A2-1.0-jar-with-dependencies.jar -a <file name>
    -runs from the jar file
$mvn clean
    -removes created files
### Running from the command line (without maven)
java -jar ./target/2430_A2-1.0-jar-with-dependencies.jar <flag> <file name>
Flags:
-a : new save gamee
-l : load save game
### Instructions for using the program
Game Commands:
look : gives a longer description of the current room
look "item name" gives a description of the item
go "direction" : moves the player in that direction, if posssible.
take "item name" takes the item and adds it to your inventory
inventory: view your inventory

### Test Class

In my test class, due to the implementation of getConnectedRoom, I could only test if it returns null.

## Statement of Individual Work

By the action of submitting this work for grading, I certify that this assignment is my own work, based on my personal study.  I also certify that no parts of this assignment have previously been submitted for assessment in any other course, except where specific permission has been granted.  Finally, I certify that I have not copied in part or whole  or otherwise plagiarised the work of other persons.

