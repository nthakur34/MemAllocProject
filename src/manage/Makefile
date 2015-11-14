JFLAGS =
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(CLASSES)

CLASSES = \
	MemBlock.java \
	Sequence.java \
	SequenceDLL.java \
	Queue.java \
	QueueSeq.java \
        BucketSort.java \
	QuickSort.java \
        AVLtree.java \
	PriorityQueue.java \
	Defrag.java \
        BestFit.java \
	NextFitAlloc.java \
	WorstFitAlloc.java \
	MemoryManager.java \
        BaseManager.java \
	MemSimMain.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
