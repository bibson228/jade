java -cp "lib/jade.jar:lib/Commons-codec/commons-codec-1.3.jar:classes" jade.Boot -conf "src/jade/util/leap/Properties.java" -gui -agents \
agent1:examples.algsMultiagent.Task2'(1,agent4,agent5,agent6)'\
\;agent2:examples.algsMultiagent.Task2'(2,agent1,agent3,agent9)'\
\;agent3:examples.algsMultiagent.Task2'(3,agent6,agent7,agent9)'\
\;agent4:examples.algsMultiagent.ErrorTask2'(4,agent1,agent2,agent5)'\
\;agent5:examples.algsMultiagent.Task2'(5,agent4,agent6,agent10)'\
\;agent6:examples.algsMultiagent.Task2'(6,agent1,agent5,agent9)'\
\;agent7:examples.algsMultiagent.Task2'(7,agent2,agent6,agent8)'\
\;agent8:examples.algsMultiagent.Task2'(8,agent3,agent7,agent10)'\
\;agent9:examples.algsMultiagent.Task2'(9,agent3,agent8,agent10)'\
\;agent10:examples.algsMultiagent.Task2'(10,agent5,agent8,agent9)'
