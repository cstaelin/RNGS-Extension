ifeq ($(origin JAVA_HOME), undefined)
  JAVA_HOME=/usr
endif

ifneq (,$(findstring CYGWIN,$(shell uname -s)))
  COLON=\;
  JAVA_HOME := `cygpath -up "$(JAVA_HOME)"`
else
  COLON=:
endif

SRCS=$(wildcard src/*.java)

rngs.jar rngs.jar.pack.gz: $(SRCS) colt.jar colt.jar.pack.gz NetLogoLite.jar Makefile manifest.txt
	mkdir -p classes
	$(JAVA_HOME)/bin/javac -g -encoding us-ascii -source 1.5 -target 1.5 -classpath NetLogoLite.jar$(COLON)colt.jar -d classes $(SRCS)
	jar cmf manifest.txt rngs.jar -C classes .
	pack200 --modification-time=latest --effort=9 --strip-debug --no-keep-file-order --unknown-attribute=strip rngs.jar.pack.gz rngs.jar

NetLogoLite.jar:
	curl -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/NetLogoLite.jar' -o NetLogoLite.jar

colt.jar.pack.gz:
	pack200 --modification-time=latest --effort=9 --strip-debug --no-keep-file-order --unknown-attribute=strip colt.jar.pack.gz colt.jar
	
rngs.zip: rngs.jar
	rm -rf rngs
	mkdir rngs
	cp -rp rngs.jar rngs.jar.pack.gz colt.jar colt.jar.pack.gz README.md Makefile src manifest.txt rngs
	zip -rv rngs.zip rngs
	rm -rf rngs
