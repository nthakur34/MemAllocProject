JFLAGS = -cp .:junit-4.12.jar
#JRFLAGS = -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	MemBlockTest.java \
	SequenceTest.java \
        BucketSortTest.java \
	QuickSortTest.java \
        AVLtreeTest.java \
	PriorityQueueTest.java \
	DefragTest.java \
        BestFitTest.java \
	NextFitAllocTest.java \
	WorstFitAllocTest.java \
	MemoryManagerTest.java \
        BaseManagerTest.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
