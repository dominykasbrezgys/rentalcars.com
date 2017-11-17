# rentalcars.com
Exercise for rentalcars.com grad scheme

IntelliJ IDEA was used to develop this project.
External libraries used:
gson
jersey

Please inspect source code under src/

A function getVehiclesAsArrayListFromFile() in CarsParser.java is provided for testing with json files under local directory.

Otherwise:
A runnable CarsParser.jar, which parses .json file from http://www.rentalcars.com/js/vehicles.json, was created 
for deploying a REST server on http://localhost:9999/CarsParserREST/
Instructions:
1. Checkout CarsParser.jar to your local directory
2. Locate to directory of CarsParser.jar via terminal
3. Use command: java -jar CarsParser.jar
4. Wait until you see message 'Press Enter to stop server.'

Outputs for each task for parsing http://www.rentalcars.com/js/vehicles.json can now be accesed via 
http://localhost:9999/CarsParserREST/getTaskOutput/{Task Number}

For example: http://localhost:9999/CarsParserREST/getTaskOutput/Task4

Task1 will output a list of all the cars, in ascending price order, in the following format:
1.	{Vehicle name} – {Price}
2.	{Vehicle name} – {Price}
.
.
.
.
31.	{Vehicle name} – {Price}

Task2 will output calculation of specification of the vehicles based on their SIPP in format:
1.	{Vehicle name} – {SIPP} – {Car type} – {Car type/doors} – {Transmission} – {Fuel} – {Air con}
2.	{Vehicle name} – {SIPP} – {Car type} – {Car type/doors} – {Transmission} – {Fuel} – {Air con}
.
.
.
31.	{Vehicle name} – {SIPP} – {Car type} – {Car type/doors} – {Transmission} – {Fuel} – {Air con}

Task3 outputs the highest rated supplier per car type, descending order, in the following format:
1.	{Vehicle name} – {Mini} – {Hertz} – {8.9}
2.	{Vehicle name} – {Car type} – {Supplier} – {Rating}
.
.
7.	{Vehicle name} – {Car type} – {Supplier} – {Rating}

Task4 outputs a list of vehicles, ordered by the sum of the scores in descending order, in the following format:
1.	{Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}
2.	{Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}
.
3.	{Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}




