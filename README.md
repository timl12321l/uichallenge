Steps to running this program.

1. Check if jdk1.8.0_121 or greater is installed by typing 'java -v'
If not please download from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2. Check if Maven is installed. https://maven.apache.org/ for download
3. Check if Chrome Browser is version 70 or greater. https://www.google.com/chrome/ for download.
4. Download the program by typing ' git clone https://github.com/timl12321l/uichallenge.git '
5. Navigate to where the pom.xml file is and type 'mvn test'
6. Results will be displayed at end of run after program test for the following scenarios:

UI testing
NASA Sentry, Sentry is a highly automated collision monitoring system that continually scans the most current asteroid catalog for possibilities of future impact with Earth over the next 100 years.
 • Open this page: https://cneos.jpl.nasa.gov/sentry/.
• On this page, you can display your desired number of items on this page: 25/50/75 in the
dropdown.
 
Task is to ensure the following two functionalities on this page:
1. Drop down should display 4 options, i.e. 10, 25, 50 and 75
2. 25 entries should be selected by default
3. Clicking on any of the entries in this table should open a page similar
to https://cneos.jpl.nasa.gov/sentry/details.html#?des=29075, validate the des value in query paramter to this opened page
