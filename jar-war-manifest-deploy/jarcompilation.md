creating class files
- javac -d out topics/array/problems/*.java (-d out - output directory)
- to refer a class using classpath
>	javac -d out -cp out src/topics/array/problems/*.java (cp is classpath, the other class is expected to be compiled)

creating jars.
- jar cvf <jar-name>.jar -C out <directory-where-classes-are-present>


executing the files with jars
- java -cp topic-array-problem.jar array.problems.SightSeeing
- java -cp problems.jar:trees.jar topics.array.problems.MainClass	(dependency of another jar)



	

	

