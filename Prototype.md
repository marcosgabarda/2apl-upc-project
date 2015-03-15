# Compiling instructions #

  * cd code/2apl\_platform/environments
  * javac -classpath "../apapl.jar:." cardtable/`*`.java
  * jar cvf cardtable.jar cardtable/`*`.class

# Running instructions #

  * cd code/2apl\_platform
  * java -jar apapl.jar
  * Load from menu and select briscola\_chiamata/briscola\_chiamata.mas

# Coding conventions #

  * Commit only code that compiles
  * Prefer small changes, many commits