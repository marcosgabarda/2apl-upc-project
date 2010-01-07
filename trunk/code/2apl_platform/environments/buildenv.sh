#!/bin/bash

javac -classpath "../apapl.jar:." cardtable/*java && jar cvfm cardtable.jar cardtable/Manifest.txt -C cardtable/ .
