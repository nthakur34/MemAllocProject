JFLAGS = -cp .:junit-4.12.jar
#JRFLAGS = -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	manage/MemBlockTest.java \
	manage/SequenceTest.java \
	manage/BucketSortTest.java \
	manage/QuickSortTest.java \
	manage/AVLtreeTest.java \
	manage/PriorityQueueTest.java \
	manage/DefragTest.java \
	manage/BestFitTest.java \
	manage/NextFitAllocTest.java \
	manage/WorstFitAllocTest.java \
	manage/BaseManagerTest.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
