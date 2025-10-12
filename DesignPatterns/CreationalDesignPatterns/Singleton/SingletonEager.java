package DesignPatterns.CreationalDesignPatterns.Singleton;

class SingletonEager {
    private static final SingletonEager instance = new SingletonEager();
    private SingletonEager() { }
    public static SingletonEager getInstance() {
        return instance;
    }
}

class SingletonLazy {
    private static SingletonLazy instance;
    private SingletonLazy() { }
    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}