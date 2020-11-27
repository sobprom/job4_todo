#!/bin/bash
cd /opt/tomcat/bin
cp /home/sobprom/projects/study/job4j_todo/target/todo.war /opt/tomcat/webapps/ --force 
sudo ./catalina.sh jpda start
