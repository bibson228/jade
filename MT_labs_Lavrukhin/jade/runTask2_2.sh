java -cp "lib/jade.jar:lib/Commons-codec/commons-codec-1.3.jar:classes" jade.Boot -conf "src/jade/util/leap/Properties.java" -gui -agents \
agent1:examples.algsMultiagent.Task2'(1,agent2,agent10)'\
\;agent2:examples.algsMultiagent.Task2'(2,agent1,agent3,agent10)'\
\;agent3:examples.algsMultiagent.Task2'(3,agent2,agent4,agent10)'\
\;agent4:examples.algsMultiagent.Task2'(4,agent3,agent5,agent10)'\
\;agent5:examples.algsMultiagent.Task2'(5,agent4,agent6,agent10)'\
\;agent6:examples.algsMultiagent.Task2'(6,agent5,agent7,agent10)'\
\;agent7:examples.algsMultiagent.Task2'(7,agent6,agent8,agent10)'\
\;agent8:examples.algsMultiagent.Task2'(8,agent7,agent9,agent10)'\
\;agent9:examples.algsMultiagent.Task2'(9,agent8,agent10)'\
\;agent10:examples.algsMultiagent.Task2'(10,agent9,agent1)'