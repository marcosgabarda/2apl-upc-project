#!/bin/bash

javac -classpath "../apapl.jar:." cardtable/*java && jar cvf cardtable.jar cardtable/*.class cardtable/cards/*.gif
