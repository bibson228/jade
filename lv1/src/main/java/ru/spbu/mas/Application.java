package ru.spbu.mas;

class Application {
    public  static  void main(String[] args) {
        var parameters = ParametersReader.getTopologyParameters("parameters.txt");

        MainController mc = new MainController(parameters);
        mc.initAgents();
    }
}
