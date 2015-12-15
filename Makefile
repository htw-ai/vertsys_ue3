JAVAC=javac
sources = $(wildcard *.java)
sources_client = $(wildcard client/*.java)
sources_server = $(wildcard server/*.java)
classes = $(sources:.java=.class)

all: $(classes)

client: $(sources_client)
	$(JAVAC) Client.java

server: $(sources_server)
	$(JAVAC) Server.java

run: 
	java Server

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<
